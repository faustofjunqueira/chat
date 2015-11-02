package chat.dominio.entidade.auxiliar;

import java.io.Serializable;
import java.util.List;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;

public class UsuarioSalaComponente implements Serializable{

	private List<Usuario> usuarios;
	private Sala sala;
	
	public UsuarioSalaComponente() {}

	public UsuarioSalaComponente(List<Usuario> usuarios, Sala sala) {
		super();
		this.usuarios = usuarios;
		this.sala = sala;
	}

	public List<Usuario> getUsuario() {
		return usuarios;
	}

	public void setUsuario(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
	
}
