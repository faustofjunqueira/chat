package chat.infra.conexao.servidor;

import java.io.IOException;
import java.util.List;

import chat.infra.conexao.Resposta;
import chat.util.ProdutoConsumidor;

public class ServidorEmissor extends ProdutoConsumidor<Resposta<?>> {

	private ServidorContexto contexto;

	ServidorEmissor() {
	}

	void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
	}

	private void processo() throws IOException, InterruptedException {
		List<Resposta<?>> respostas = extrair();
		contexto.getCanal().enviar(respostas);
	}

	@Override
	public void run() {
		// TODO: while true
		while (true) {
			try {
				processo();
			} catch (IOException | InterruptedException e) {
				break;
			}
		}
		
		try {
			contexto.fechar();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
