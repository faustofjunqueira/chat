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
		while (true) {
			try {
				ServidorCanal canal = new ServidorCanal(socketBemVindo.accept());
				new Thread(() -> {
					try {
						manipuladorDeCanal(canal);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void manipuladorDeCanal(ServidorCanal canal) throws IOException, ClassNotFoundException {
		boolean loop = true;
		while (loop) {
			Requisicao<String> req = (Requisicao<String>) canal.receber();
			loop = fecharCanal(req);
			Resposta res = resolveRequisicao(req);
			canal.enviar(res);
		}
		canal.fechar();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Resposta resolveRequisicao(Requisicao<?> req) {
		Resposta res;
		try {
			Class<?> classe = Class.forName(req.getClasse());
			Method metodo = classe.getDeclaredMethod(req.getAcao(), Requisicao.class);
			res = new Resposta(Resposta.COD_SUCESSO, (Serializable) metodo.invoke(classe.newInstance(), req));
			res.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			res = new Resposta<Exception>(Resposta.COD_ERRO_PADRAO, e);
			res.setErro(true);
		}
		return res;
	}

	private boolean fecharCanal(Requisicao req) {
		String fecharCanal = req.getCabecalho().buscar(Requisicao.CHAVE_FECHAR_CONEXAO);
		return fecharCanal == null;
	}
	
}
