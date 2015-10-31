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
		System.out.println("(ServidorEmissor) Tenta Extrair");
		List<Resposta<?>> respostas = extrair();
		System.out.println("(ServidorEmissor) Enviando Resposta");
		contexto.getCanal().enviar(respostas);
		System.out.println("(ServidorEmissor) Resposta Resposta");
	}

	@Override
	public void run() {
		System.out.println(String.format("(ServidorEmissor) Thread Id %d", Thread.currentThread().getId()));
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
