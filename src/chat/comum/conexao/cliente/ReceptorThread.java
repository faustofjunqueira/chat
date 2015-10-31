package chat.comum.conexao.cliente;

import java.io.IOException;
import java.util.List;
import chat.comum.conexao.Resposta;

public class ReceptorThread extends Thread {

	private ClienteContexto contexto;

	ReceptorThread() {
		super();
		contexto = ClienteContexto.Instance();
	}
	
	private void invocarProcessoRecebimento() throws IOException, ClassNotFoundException {
		System.out.println("(Receptor) Requisitando a lista");
		List<Resposta<?>> listaRecebidos = contexto.getCanal().receber();
		System.out.print("Recebido");
		System.out.println(listaRecebidos.get(0).getHash());
		System.out.println("(Receptor) Recebeu respostas");
		for (Resposta<?> resposta : listaRecebidos) {
			Dispatcher dispatcher = contexto.getDispatcherPeloHash(resposta.getHash());
			System.out.println("Dispatcher Recuperado "+dispatcher);
			dispatcher.setStatus(EnumEmissorStatus.RECEBIDO);
			contexto.removeDispatcher(dispatcher);
			dispatcher.respostaObtida(resposta);
		}
		System.out.println("(Receptor) Tratou as respostas");
	}

	public void run() {
		contexto = ClienteContexto.Instance();
		try {
			//TODO: while true
			while (true) {
				invocarProcessoRecebimento();
			}
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}