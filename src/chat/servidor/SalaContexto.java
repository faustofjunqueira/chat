package chat.servidor;


import java.util.ArrayList;
import java.util.List;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;

class SalaContexto {

	private Sala sala;
	private List<UsuarioConectado> usuarios;

	public SalaContexto(Sala sala) {
		super();
		this.sala = sala;
		this.usuarios = new ArrayList<>();
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
