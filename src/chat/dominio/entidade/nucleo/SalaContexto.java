package chat.dominio.entidade.nucleo;

import java.util.Set;
import java.util.TreeSet;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;

class SalaContexto {

	private Sala sala;
	private Set<UsuarioConectado> usuarios;

	public SalaContexto(Sala sala) {
		super();
		this.sala = sala;
		this.usuarios = new TreeSet<>();
	}

	Sala getSala() {
		return sala;
	}
	
	void entrar(UsuarioConectado usuario) {
		usuarios.add(usuario);
	}
	
	void sair(UsuarioConectado usuario) {
		usuarios.remove(usuarios);
	}
	
	void enviarMensagem(Mensagem mensagem) {
		for(UsuarioConectado usuarioConectado : usuarios) {
			usuarioConectado.enviarMensagem(mensagem);
		}
	}

}
