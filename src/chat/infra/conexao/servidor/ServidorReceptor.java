package chat.infra.conexao.servidor;

import java.io.IOException;

import chat.infra.conexao.Requisicao;
import chat.util.ProdutoConsumidor;

class ServidorReceptor extends ProdutoConsumidor<Requisicao<?>> {

	private ServidorContexto contexto;

	void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
	}

	private void processo() throws ClassNotFoundException, IOException {
		adicionar(contexto.getCanal().receber());
	}

	@Override
	public void run() {
		// TODO: while true
		while (true) {
			try {
				processo();
			} catch (ClassNotFoundException | IOException e) {
				break;
			}
		}
		try {
			contexto.fechar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
