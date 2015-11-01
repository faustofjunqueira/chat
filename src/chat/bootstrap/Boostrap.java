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
			
// REQUISIÇÃO SIMPLES			
			Teste t = new Teste("oi mundo");
			System.out.println("(Main) Disparando Dispatcher");
			Dispatcher<Teste> dispatcher = Dispatcher.Invocar(new Requisicao<Teste>("chat.bootstrap.TesteController Junda", t));
			System.out.println("(Main) Disparado Dispatcher");
			dispatcher.pronto((data) -> {
				System.out.println("Executado > "+data.msg);
				try {
					Dispatcher.Fechar();
				} catch (Throwable e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
// REQUISIÇÃO CONTINUA
//			Teste t = new Teste("oi mundo");
//			Ouvinte.Invocar(new Requisicao<Teste>("chat.bootstrap.TesteController Junda", t), 
//					(data) -> System.out.println("Executado > "+data.msg));

			
		}
	}
}
