package chat.comum.conexao;

import java.io.IOException;
import java.net.ServerSocket;

public class Servidor{

	public static final String IP_DEFAULT = "127.0.0.1";
	public static final short PORTA_DEFAULT = 9900;
	
	private ServerSocket socketBemVindo;
	
	public Servidor() throws IOException {
		socketBemVindo = new ServerSocket(PORTA_DEFAULT);
	}
	
	public void executar() throws IOException, ClassNotFoundException{
		ServidorCanal canal = new ServidorCanal(socketBemVindo.accept());
		Requisicao res = canal.receber();
		canal.enviar(new Resposta(res.msg.toUpperCase()));
		canal.fechar();
	}

}
