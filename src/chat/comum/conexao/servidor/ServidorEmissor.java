package chat.comum.conexao.servidor;

import java.io.IOException;
import java.util.List;

import chat.comum.conexao.ProdutoConsumidor;
import chat.comum.conexao.Resposta;

public class ServidorEmissor extends ProdutoConsumidor<Resposta<?>>{

	private ServidorContexto contexto;
	
	public ServidorEmissor() {}

	public void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
	}

	private void processo() throws IOException, InterruptedException{
		List<Resposta<?>> respostas = extrair();  
		contexto.getCanal().enviar(respostas);
	}

	@Override
	public synchronized void run() {
		//TODO: while true
		while(true){
			try {
				processo();
			} catch (IOException | InterruptedException e) {
				// TODO tratar as exceptions
				e.printStackTrace();
			}
		}
	}

}
