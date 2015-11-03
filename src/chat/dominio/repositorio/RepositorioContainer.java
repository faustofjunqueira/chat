package chat.dominio.repositorio;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;

public class RepositorioContainer {

	private static RepositorioContainer object;

	public static RepositorioContainer Instance() {
		if (object == null) {
			object = new RepositorioContainer();
		}
		return object;
	}

	private Repositorio<Mensagem> mensagemRepositorio;
	private Repositorio<Sala> salaRepositorio;
	private Repositorio<Usuario> usuarioRepositorio;

	private RepositorioContainer() {
	}

	public final Repositorio<Mensagem> Messagem() {
		if (mensagemRepositorio == null) {
			mensagemRepositorio = new Repositorio<>();
		}
		return mensagemRepositorio;
	}

	public final Repositorio<Sala> Sala() {
		if (salaRepositorio == null) {
			salaRepositorio = new Repositorio<>();
		}
		return salaRepositorio;
	}

	public final Repositorio<Usuario> Usuario() {
		if (usuarioRepositorio == null) {
			usuarioRepositorio = new Repositorio<>();
		}
		return usuarioRepositorio;
	}

}
