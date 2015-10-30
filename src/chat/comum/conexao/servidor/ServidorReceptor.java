package chat.comum.conexao.servidor;

import java.io.IOException;
import chat.comum.conexao.ProdutoConsumidor;
import chat.comum.conexao.Requisicao;

class ServidorReceptor extends ProdutoConsumidor<Requisicao<?>>{

	private ServidorContexto contexto;
	
	public void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
	}

	private void processo() throws ClassNotFoundException, IOException{
		adicionar(contexto.getCanal().receber());
	}

	@Override
	public synchronized void run() {
		//TODO: while true
		while(true){
			try {
				processo();
			} catch (ClassNotFoundException | IOException e) {
				// TODO tratar as exceptions
				e.printStackTrace();
			}
		}
	}

}
