package chat.dominio.entidade;

import java.util.List;

public class Sala extends Dominio {

	private String nome;
	private List<Usuario> usuario;
	
	public Sala() {
		super();
	}

	public Sala(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Usuario> getUsuarios(){
		return usuario;
	}
}
