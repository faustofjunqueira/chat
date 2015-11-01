package chat.comum.conexao;

import java.util.ArrayList;
import java.util.List;

public class ProdutoConsumidor <T> extends Thread{

  private ArrayList<T> buffer; // Fixado em ArrayList pq precisa-se do clone
  
  public ProdutoConsumidor() {
    buffer = new ArrayList<>();
  }
  
  public synchronized void adicionar(T e) {
    buffer.add(e);
    notify();
  }
  
  public synchronized void adicionar(List<T> e) {
    buffer.addAll(e);
    notify();
  }
  
  public synchronized void remove(T e) {
    buffer.remove(e);
  }
  
  @SuppressWarnings("unchecked")
  public synchronized List<T> extrair() throws InterruptedException {
    if( buffer.size() == 0 ) {
      wait();
    }
    List<T> e = (List<T>) buffer.clone();
    buffer.clear();
    return e;
  }
  
  protected List<T> getBuffer(){
    return buffer;
  }
}