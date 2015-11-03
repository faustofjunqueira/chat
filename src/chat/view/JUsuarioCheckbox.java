package chat.view;

import javax.swing.JCheckBox;

import chat.dominio.entidade.Usuario;

public class JUsuarioCheckbox extends JCheckBox{

	private Usuario usuario;
	
	public JUsuarioCheckbox(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
}
