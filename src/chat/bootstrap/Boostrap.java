package chat.bootstrap;

import java.io.IOException;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.cliente.Dispatcher;
import chat.comum.conexao.servidor.ServidorContainer;

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
			System.out.println("Servidor Start");
			ServidorContainer.Instance().IniciarServidor();
			ServidorContainer.Instance().run();
			System.out.println("Fim servidor");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		if (args[0].equals("server")) {
			comecarAplicacaoServidor();
		} else {
			comecarAplicacaoCliente();
			
			Teste t = new Teste("oi mundo");
			Dispatcher<Teste> dispatcher = Dispatcher.Invocar(new Requisicao<Teste>("TesteController Junda", t));
			dispatcher.pronto((data) -> System.out.println(data.msg));
		}
	}
}
