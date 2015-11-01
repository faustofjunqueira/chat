package chat.infra.conexao.servidor;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import chat.infra.conexao.Requisicao;
import chat.infra.conexao.Resposta;
import chat.servidor.servico.Servico;

public class ServidorManipuladorRequisicao extends Thread {

	private ServidorContexto contexto;

	public static <T extends Serializable> Resposta<T> MontaResposta(T dado) {
		return new Resposta<T>(Resposta.COD_SUCESSO, dado);
	}

	void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
	}

	@SuppressWarnings({ "rawtypes" })
	private Resposta resolveRequisicao(Requisicao<?> req) {
		Resposta res = null;
		try {
			Class<?> classe = Class.forName(req.getPacote() + "." + req.getClasse());
			Method metodo = classe.getDeclaredMethod(req.getAcao(), Requisicao.class);
			
			Servico servico = (Servico) classe.newInstance();
			servico.setContexto(contexto);
			
			res = MontaResposta((Serializable) metodo.invoke(servico, req));
			res.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			res = new Resposta<Exception>(Resposta.COD_ERRO_PADRAO, e);
			res.setErro(true);
		} finally {
			if (res != null) {
				res.setHash(req.getHash());
			}
		}
		return res;
	}

	private void processo() throws InterruptedException, IOException {
		List<Requisicao<?>> requisicoes = contexto.getReceptor().extrair();
		List<Resposta<?>> respostas = new ArrayList<>();
		for (Requisicao<?> requisicao : requisicoes) {
			respostas.add(resolveRequisicao(requisicao));
		}
		contexto.getEmissor().adicionar(respostas);
	}

	@Override
	public void run() {
		while (true) {
			try {
				processo();
			} catch (InterruptedException | IOException e) {
				break;
			}
		}
		try {
			contexto.fechar();
		} catch (IOException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
	}

}
