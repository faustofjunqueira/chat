package chat.bootstrap;

import java.io.IOException;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;
import chat.comum.conexao.TesteDado;
import chat.comum.conexao.cliente.Cliente;
import chat.comum.conexao.servidor.Servidor;

public class Boostrap {	
	
	public static void main(String[] args) {
		
		if(args[0].equals("server")) {
			try {
				(new Servidor()).executar();
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}

		} else {
			Cliente cliente = new Cliente();
			try {
				cliente.conectar();
				TesteDado t;
				t = cliente.Dispatcher(new Requisicao<TesteDado>("chat.comum.conexao.TesteController junda", new TesteDado("jundinhas")));
				System.out.println(t.msg);
				t = cliente.Dispatcher(new Requisicao<TesteDado>("chat.comum.conexao.TesteController junda2",new TesteDado("oi mundo")));
				System.out.println(t.msg);
			} catch (Exception e) {
				e.printStackTrace();
			} 

		}
	}
}
