package chat.bootstrap;

import java.io.IOException;

import chat.comum.conexao.Cliente;
import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;
import chat.comum.conexao.Servidor;

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
				Resposta res = cliente.Dispatcher(new Requisicao("somente um teste"));
				System.out.println(res.msg);
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
			
		}
	}
}
