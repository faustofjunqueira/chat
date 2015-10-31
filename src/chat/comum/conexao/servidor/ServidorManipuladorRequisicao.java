package chat.comum.conexao.servidor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;

public class ServidorManipuladorRequisicao extends Thread {

	private ServidorContexto contexto;

	public void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
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
		System.out.println("(ServidorManipuladorRequisicao) tenta pegar recepções");
		List<Requisicao<?>> requisicoes = contexto.getReceptor().extrair();
		System.out.println("(ServidorManipuladorRequisicao) Extraiu");
		System.out.println("Hash Requisição: " + requisicoes.get(0).getHash());
		List<Resposta<?>> respostas = new ArrayList<>();
		for (Requisicao<?> requisicao : requisicoes) {
			respostas.add(resolveRequisicao(requisicao));
		}
		System.out.println("(ServidorManipuladorRequisicao) Resolveu requisições");
		contexto.getEmissor().adicionar(respostas);
		System.out.println("Hash Resposta: " + respostas.get(0).getHash());
		System.out.println("(ServidorManipuladorRequisicao) Delegou responsabilidade");
	}

	@Override
	public void run() {
		System.out.println(String.format("(ServidorManipuladorRequisicao) Thread Id %d", Thread.currentThread().getId()));
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
