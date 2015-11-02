package chat.servidor.servico;

import chat.dominio.entidade.Mensagem;
import chat.infra.conexao.Requisicao;
import chat.servidor.SalasContainer;
import chat.util.GeradorSerial;

public class MensagemServico extends Servico {

	public MensagemServico() {
		super();
	}

	public boolean enviar(Requisicao<Mensagem> requisicao) {
		Mensagem mensagem = requisicao.getDados();
		mensagem.setId(GeradorSerial.AutoIncremento());
		SalasContainer.Instance().enviarMensagem(mensagem);
		return true;
	}
}
