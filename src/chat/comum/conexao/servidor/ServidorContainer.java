package chat.comum.conexao.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import chat.comum.conexao.GeradorSerial;

public class ServidorContainer implements Runnable{

	private static ServidorContainer objectSingleton;
	public static ServidorContainer Instance(){
		if(objectSingleton == null){
			objectSingleton = new ServidorContainer();
		}
		return objectSingleton;
	}
	
	private ServidorContainer(){}
	
	public static final String IP_DEFAULT = "127.0.0.1";
	public static final short PORTA_DEFAULT = 9900;

	private ServerSocket socketBemVindo;
	private Map<String, ServidorContexto> listaClientesConectados;
	
	public void IniciarServidor() throws IOException{
		listaClientesConectados = new HashMap<>();
		socketBemVindo = new ServerSocket(PORTA_DEFAULT);
	}
	
	public void aceitarClientes(Socket socket_aceito) throws IOException{
		ServidorCanal canal = new ServidorCanal(socket_aceito);
		ServidorEmissor emissor = new ServidorEmissor();
		ServidorReceptor receptor = new ServidorReceptor();
		ServidorManipuladorRequisicao manipulador = new ServidorManipuladorRequisicao(emissor, receptor);
		ServidorContexto contexto = new ServidorContexto(emissor, receptor, manipulador, canal);
		listaClientesConectados.put(GeradorSerial.Criar(), contexto);
	}
	
	public void run() {
		//TODO: while true
		while(true){
			try {
				System.out.println("Esperando cliente");
				aceitarClientes(socketBemVindo.accept());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
