package chat.bootstrap;

import java.io.IOException;

import chat.infra.conexao.servidor.ServidorContainer;
import chat.infraconexao.cliente.Dispatcher;

public class Boostrap {

	private static void comecarAplicacaoCliente() {
		try {
			Dispatcher.Bootstap(Dispatcher.IP_DEFAULT, Dispatcher.PORTA_DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void comecarAplicacaoServidor() {
		try {
			ServidorContainer.Instance().IniciarServidor();
			ServidorContainer.Instance().run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		if (args[0].equals("server")) {
			comecarAplicacaoServidor();
		} else {
			comecarAplicacaoCliente();
		}
	}
}
