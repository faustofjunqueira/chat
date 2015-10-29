package chat.comum.conexao;

import java.io.IOException;
import java.net.Socket;

public class Cliente {
	private String ip;
	private short porta;
	private ClienteCanal canal;
	
	@SuppressWarnings("unused")
	public Cliente() {
		ip = Servidor.IP_DEFAULT;
		porta = Servidor.PORTA_DEFAULT;
	}

	public Cliente(String ip, short porta) {
		this.ip = ip;
		this.porta = porta;
	}
	
	public void conectar() throws IOException{
		Socket socket = new Socket(getIp(), getPorta());
		canal = new ClienteCanal(socket);
	}

	public Resposta Dispatcher(Requisicao req) throws ClassNotFoundException, IOException{
		getCanal().enviar(req);
		return getCanal().receber();
	}
	
	private String getIp() {
		return ip;
	}

	private short getPorta() {
		return porta;
	}

	private ClienteCanal getCanal() {
		return canal;
	}
	
	@Override
	protected void finalize() throws Throwable {
		canal.fechar();
		super.finalize();
	}
}
