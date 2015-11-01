package chat.servidor.servico;

import java.util.List;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Usuario;
import chat.dominio.entidade.nucleo.SalasContainer;
import chat.dominio.entidade.nucleo.UsuarioConectado;
import chat.infra.conexao.Requisicao;

public class UsuarioServico extends Servico{

	public UsuarioServico() {
		super();
	}

	public Mensagem entrar(Requisicao<Usuario> requisicao) {
		UsuarioConectado usuarioConectado = new UsuarioConectado(requisicao.getDados(), getContexto(), requisicao.getHash());
		SalasContainer.Instance().entrarUsuario(usuarioConectado);
		return null;
	}
	
	public boolean sair(Requisicao<Usuario> requisicao) {
		SalasContainer.Instance().sairUsuario(requisicao.getDados());
		return true;
	}

	public List<Usuario> todos(Requisicao<Usuario> requisicao){
		return SalasContainer.Instance().todosUsuariosConectados();
	}
	
}
