package chat.comum.conexao.servidor;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.ServerSocket;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;

public class Servidor {

	public static final String IP_DEFAULT = "127.0.0.1";
	public static final short PORTA_DEFAULT = 9900;

	private ServerSocket socketBemVindo;

	public Servidor() throws IOException {
		socketBemVindo = new ServerSocket(PORTA_DEFAULT);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void executar() throws IOException, ClassNotFoundException {
		ServidorCanal canal = new ServidorCanal(socketBemVindo.accept());
		while (true) {
			Requisicao<String> req = (Requisicao<String>) canal.receber();
			Resposta res;
			try{
				Class<?> classe = Class.forName(req.getClasse());
				Method metodo = classe.getDeclaredMethod(req.getAcao(), Requisicao.class);
				res = new Resposta(Resposta.COD_SUCESSO,(Serializable) metodo.invoke(classe.newInstance(),req));
				res.setErro(false);
			}catch(Exception e){
				e.printStackTrace();
				res = new Resposta<Exception>(Resposta.COD_ERRO_PADRAO, e);
				res.setErro(true);
			}
			canal.enviar(res);
		}
	}

}
