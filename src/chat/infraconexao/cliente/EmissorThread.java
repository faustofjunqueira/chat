package chat.infraconexao.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import chat.infra.conexao.Requisicao;


public class EmissorThread extends Thread {

  EmissorThread() {
    super();
  }
  
  private ClienteContexto contexto = ClienteContexto.Instance();

  private void invocarProcessoEnviados() throws IOException, InterruptedException {
    List<Dispatcher<?>> listaParaEnviar = contexto.getListaRequisicaoParaEnviar();
    List<Requisicao<?>> listaRequisicao = new ArrayList<>(listaParaEnviar.size());
    for (Dispatcher<?> d : listaParaEnviar) {
      listaRequisicao.add(d.getRequisicao());
    }
    contexto.getCanal().enviar(listaRequisicao);
    contexto.atualizaStatus(listaParaEnviar, EnumEmissorStatus.ENVIADO);
  }

  public void run() {
    contexto = ClienteContexto.Instance();
    try {
      // TODO: while true;
      while (true) {
        invocarProcessoEnviados();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch(InterruptedException e) {}
  }
}
