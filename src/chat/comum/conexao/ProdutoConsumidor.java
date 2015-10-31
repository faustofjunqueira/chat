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
		System.out.println(String.format("(%s) (%d) Notify ", super.hashCode() ,Thread.currentThread().getId()));
		notify();
	}
	
	public synchronized void remove(T e) {
		buffer.remove(e);
	}
	
	@SuppressWarnings("unchecked")
	public synchronized List<T> extrair() throws InterruptedException {
		System.out.println(String.format("(%s) (%d) Tenta Pegar buffer size %d", super.hashCode(), Thread.currentThread().getId(), buffer.size()));
		if( buffer.size() == 0 ) {
			System.out.println(String.format("(%s) (%d) Extrair em wait", super.hashCode(), Thread.currentThread().getId()));
			wait();
		}
		System.out.println(String.format("(%s) (%d) Conseguiu extrair", super.hashCode(), Thread.currentThread().getId()));
		List<T> e = (List<T>) buffer.clone();
		buffer.clear();
		System.out.println(String.format("(%s) (%d) saiu extrair", super.hashCode(), Thread.currentThread().getId()));
		return e;
	}
	
	protected List<T> getBuffer(){
		return buffer;
	}
}