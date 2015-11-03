package chat.infra.conexao.cliente;

import java.io.IOException;
import java.util.List;

import chat.infra.conexao.Resposta;

public class ReceptorThread extends Thread {

	private ClienteContexto contexto;
	private int i = 0;
	ReceptorThread() {
		super();
		contexto = ClienteContexto.Instance();
	}

	private void invocarProcessoRecebimento() throws IOException, ClassNotFoundException {
		List<Resposta<?>> listaRecebidos = contexto.getCanal().receber();
		for (Resposta<?> resposta : listaRecebidos) {
			Dispatcher dispatcher = contexto.getDispatcherPeloHash(resposta.getHash());
			dispatcher.setStatus(EnumEmissorStatus.RECEBIDO);
			dispatcher.respostaObtida(resposta);
		}
	}

	public void run() {
		contexto = ClienteContexto.Instance();
		try {
			// TODO: while true
			while (true) {
				invocarProcessoRecebimento();
			}
		} catch (IOException | ClassNotFoundException e) {
			try {
				ClienteContexto.Instance().finalize();
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}