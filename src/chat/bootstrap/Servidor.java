package chat.bootstrap;

import java.io.IOException;
import chat.infra.conexao.servidor.ServidorContainer;

public class Servidor {
	
	private void iniciarServidorContainer(){
		try {
			ServidorContainer.Instance().IniciarServidor();
			ServidorContainer.Instance().run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Servidor servidor = new Servidor();
		servidor.iniciarServidorContainer();
	}
	
}
