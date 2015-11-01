package chat.servidor.servico;

import chat.dominio.entidade.Mensagem;
import chat.infra.conexao.Requisicao;

public class MensagemServico extends Servico {

	public MensagemServico() {
		super();
	}

	public Mensagem RandomMensagem(Requisicao<Mensagem> requisicao) {
		return new Mensagem("Oi mundo");
	}
	
}
