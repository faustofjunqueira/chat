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
	
	public void setUsuarios(List<Usuario> usuario){
		this.usuario = usuario;
	}
	
	public boolean usuarioEstaNaSala(Usuario usuario){
		for (Usuario u : this.usuario) {
			if(usuario.getId().equals(u.getId())){
				return true;
			}
		}
		return false;
	}
	
	public String gerarNomeSala(){
		String nome = "";
		for(Usuario u : usuario) {
			nome += String.valueOf(u.getNome().charAt(0));
		}
		return nome;
	}
}
