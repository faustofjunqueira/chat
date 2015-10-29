package chat.comum.conexao.cliente;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;
import chat.comum.conexao.servidor.Servidor;

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

	public void conectar() throws IOException {
		Socket socket = new Socket(getIp(), getPorta());
		canal = new ClienteCanal(socket);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> T Dispatcher(Requisicao<T> req) throws Exception {
		getCanal().enviar(req);
		Resposta<?> resposta = getCanal().receber();

		if (resposta.isErro()) {
			throw ((Resposta<Exception>) resposta).getDados();
		}

		return ((Resposta<T>) resposta).getDados();
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
