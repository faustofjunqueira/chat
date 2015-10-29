package chat.comum.conexao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteCanal {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public ClienteCanal(Socket _socket) throws IOException {
		socket = _socket;
	}

	public void enviar(Requisicao _requisição) throws IOException {
		getOut().writeObject(_requisição);
	}

	public Resposta receber() throws IOException, ClassNotFoundException {
		return (Resposta) getIn().readObject();
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
