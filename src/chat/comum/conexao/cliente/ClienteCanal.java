package chat.comum.conexao.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;

public class ClienteCanal {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ClienteCanal(Socket _socket) throws IOException {
		socket = _socket;
	}

	public <T extends Serializable> void enviar(Requisicao<T> _requisição) throws IOException {
		getOut().writeObject(_requisição);
	}

	@SuppressWarnings("unchecked")
	public <T extends Serializable> Resposta<T> receber() throws IOException, ClassNotFoundException {
		return (Resposta<T>) getIn().readObject();
	}

	public void fechar() throws IOException {
		socket.close();
	}

	private Socket getSocket() {
		return socket;
	}

	private ObjectInputStream getIn() throws IOException {
		if(in == null ) {
			in = new ObjectInputStream(getSocket().getInputStream());
		}
		return in;
	}

	private ObjectOutputStream getOut() throws IOException {
		if(out == null ) {
			out = new ObjectOutputStream(getSocket().getOutputStream());
		}
		return out;
	}

}
