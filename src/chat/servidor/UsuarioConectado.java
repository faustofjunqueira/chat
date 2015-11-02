package chat.servidor;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Usuario;
import chat.infra.conexao.Resposta;
import chat.infra.conexao.servidor.ServidorContexto;

public class UsuarioConectado {

	private Usuario usuario;
	private ServidorContexto contexto;
	private String HashRequisicao;
	
	public UsuarioConectado(Usuario usuario, ServidorContexto contexto, String hashRequisicao) {
		super();
		this.usuario = usuario;
		this.contexto = contexto;
		HashRequisicao = hashRequisicao;
	}

	void enviarMensagem(Mensagem mensagem) {
		Resposta<Mensagem> resposta = new Resposta<Mensagem>(Resposta.COD_SUCESSO, mensagem);
		resposta.setHash(HashRequisicao);
		contexto.enviarRespostaCliente(resposta);
	}

	Usuario getUsuario() {
		return usuario;
	}
	
}
