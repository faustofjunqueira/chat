package chat.comum.conexao.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import chat.comum.conexao.Requisicao;


public class EmissorThread extends Thread {

	EmissorThread() {
		super();
	}
	
	private ClienteContexto contexto = ClienteContexto.Instance();

	private void invocarProcessoEnviados() throws IOException, InterruptedException {
		System.out.println("(Emissor) Requisitando a lista");
		List<Dispatcher> listaParaEnviar = contexto.getListaRequisicaoParaEnviar();
		System.out.println("(Emissor) Pegou a lista");
		List<Requisicao<?>> listaRequisicao = new ArrayList<>(listaParaEnviar.size());
		for (Dispatcher d : listaParaEnviar) {
			listaRequisicao.add(d.getRequisicao());
		}
		System.out.println("(Emissor) Enviando a lista");
		System.out.println("Hash Requisição: " + listaRequisicao.get(0).getHash());
		contexto.getCanal().enviar(listaRequisicao);
		System.out.println("(Emissor) Enviado");
		contexto.atualizaStatus(listaParaEnviar, EnumEmissorStatus.ENVIADO);
		System.out.println("(Emissor) Status Atualizado");
	}

	public void run() {
		System.out.println("(Emissor) Run");
		contexto = ClienteContexto.Instance();
		try {
			// TODO: while true;
			while (true) {
				invocarProcessoEnviados();
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
