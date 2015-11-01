package chat.dominio.entidade;

public class Usuario extends Dominio {

	private String nome;

	public Usuario() {
		super();
	}

	public Usuario(String nome) {
		super();
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
