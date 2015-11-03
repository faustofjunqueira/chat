package chat.view;

import javax.swing.JButton;

import chat.dominio.entidade.Sala;

public class JSalaButton extends JButton {

	private ClienteController controller;
	private Sala sala;
	
	public JSalaButton(Sala sala, ClienteController controller){
		super(sala.getNome());
		this.sala = sala;
		this.controller = controller;
		iniciaBotao();
	}

	private void acaoBotao(){
		controller.novaSala(sala);
	}
	
	public void iniciaBotao(){
		this.addActionListener((e) -> acaoBotao());
	}
	
	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}
}
