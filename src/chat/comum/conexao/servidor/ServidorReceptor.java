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
		System.out.println("(ServidorReceptor) Aguarda Recebimento");
		adicionar(contexto.getCanal().receber());
		System.out.println("(ServidorReceptor) Adicionado");
	}

	@Override
	public void run() {
		System.out.println(String.format("(ServidorReceptor) Thread Id %d", Thread.currentThread().getId()));
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
