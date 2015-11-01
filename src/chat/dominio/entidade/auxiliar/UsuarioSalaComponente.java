package chat.dominio.entidade.auxiliar;

import java.io.Serializable;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;

public class UsuarioSalaComponente implements Serializable{

	private Usuario usuario;
	private Sala sala;
	
	public UsuarioSalaComponente() {}

	public UsuarioSalaComponente(Usuario usuario, Sala sala) {
		super();
		this.usuario = usuario;
		this.sala = sala;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
}
