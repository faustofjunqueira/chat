package chat.servidor.servico;

import chat.dominio.entidade.Usuario;
import chat.dominio.entidade.nucleo.SalasContainer;
import chat.dominio.entidade.nucleo.UsuarioConectado;
import chat.infra.conexao.Requisicao;

public class UsuarioServico extends Servico{

	public UsuarioServico() {
		super();
	}

	public boolean entrar(Requisicao<Usuario> requisicao) {
		UsuarioConectado usuarioConectado = new UsuarioConectado(requisicao.getDados(), getContexto(), requisicao.getHash());
		SalasContainer.Instance().entrarUsuario(usuarioConectado);
		return true;
	}
	
	public boolean sair(Requisicao<Usuario> requisicao) {
		SalasContainer.Instance().sairUsuario(requisicao.getDados());
		return true;
	}
	
}
