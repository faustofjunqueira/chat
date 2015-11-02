package chat.dominio.entidade;

import java.util.Calendar;

public class Mensagem extends Dominio {

	private String texto;
	private Usuario usuario;
	private Sala sala;
	private Calendar hora;
	
	public Mensagem() {
		super();
	}

	public Mensagem(String texto, Usuario usuario, Sala sala) {
		super();
		this.texto = texto;
		this.usuario = usuario;
		this.sala = sala;
		this.hora = Calendar.getInstance();
	}
	
	public Mensagem(Usuario usuario, Sala sala) {
		super();
		this.usuario = usuario;
		this.sala = sala;
		this.hora = Calendar.getInstance();
	}
	
	public Mensagem(String texto) {
		super();
		this.texto = texto;
		this.hora = Calendar.getInstance();
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
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
	
	@Override
	public String toString() {
		return super.toString() + " sala: "+sala.toString()+" usuario: "+usuario.toString()+" texto: "+getTexto();
	}
	
}
