package chat.comum.conexao.servidor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;

public class ServidorManipuladorRequisicao extends Thread {

	private ServidorEmissor emissor;
	private ServidorReceptor receptor;

	public ServidorManipuladorRequisicao(ServidorEmissor emissor, ServidorReceptor receptor) {
		super();
		this.emissor = emissor;
		this.receptor = receptor;
	}

	public ServidorEmissor getEmissor() {
		return emissor;
	}

	public void setEmissor(ServidorEmissor emissor) {
		this.emissor = emissor;
	}

	public ServidorReceptor getReceptor() {
		return receptor;
	}

	public void setReceptor(ServidorReceptor receptor) {
		this.receptor = receptor;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Resposta resolveRequisicao(Requisicao<?> req) {
		Resposta res = null;
		try {
			Class<?> classe = Class.forName(req.getClasse());
			Method metodo = classe.getDeclaredMethod(req.getAcao(), Requisicao.class);
			res = new Resposta(Resposta.COD_SUCESSO, (Serializable) metodo.invoke(classe.newInstance(), req));
			res.setErro(false);
		} catch (Exception e) {
			e.printStackTrace();
			res = new Resposta<Exception>(Resposta.COD_ERRO_PADRAO, e);
			res.setErro(true);
		} finally {
			if(res != null) {				
				res.setHash(req.getHash());
			}
		}
		return res;
	}

	private void processo() throws InterruptedException {
		List<Requisicao<?>> requisicoes = receptor.extrair();
		List<Resposta<?>> respostas = new ArrayList<>();
		for (Requisicao<?> requisicao : requisicoes) {
			respostas.add(resolveRequisicao(requisicao));
		}
		emissor.adicionar(respostas);
	}

	@Override
	public synchronized void run() {
		while (true) {
			try {
				processo();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
