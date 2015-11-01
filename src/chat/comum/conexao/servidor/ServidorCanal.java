package chat.comum.conexao.servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;

public class ServidorCanal {

	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	ServidorCanal(Socket _socket) throws IOException {
		socket = _socket;
	}

	void enviar(List<Resposta<?>> _resposta) throws IOException {
		getOut().writeObject( _resposta);
	}

	List<Requisicao<?>> receber() throws IOException, ClassNotFoundException {
		return (List<Requisicao<?>>) getIn().readObject();
	}

	void fechar() throws IOException {
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
