package chat.servidor.servico;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.nucleo.SalasContainer;
import chat.infra.conexao.Requisicao;

public class MensagemServico extends Servico {

	public MensagemServico() {
		super();
	}

	public boolean enviar(Requisicao<Mensagem> requisicao) {
		SalasContainer.Instance().enviarMensagem(requisicao.getDados());
		return true;
	}
}
