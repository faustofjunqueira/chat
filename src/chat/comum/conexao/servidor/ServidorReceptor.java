package chat.comum.conexao.servidor;

import java.io.IOException;
import chat.comum.conexao.ProdutoConsumidor;
import chat.comum.conexao.Requisicao;

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
