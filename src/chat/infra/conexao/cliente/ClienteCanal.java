package chat.infra.conexao.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import chat.infra.conexao.Requisicao;
import chat.infra.conexao.Resposta;

public class ClienteCanal {
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	ClienteCanal(Socket _socket) {
		socket = _socket;
	}

	void enviar(List<Requisicao<?>> _listaRequisicao) throws IOException {
		getOut().writeObject(_listaRequisicao);
	}

	@SuppressWarnings("unchecked")
	List<Resposta<?>> receber() throws IOException, ClassNotFoundException {
		return (List<Resposta<?>>) getIn().readObject();
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
