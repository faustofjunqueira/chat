package chat.comum.conexao.servidor;

import java.io.IOException;
import java.io.Serializable;

import chat.comum.conexao.Resposta;

public class ServidorContexto {

	private ServidorEmissor emissor;
	private ServidorReceptor receptor;
	private ServidorManipuladorRequisicao manipulador;
	private ServidorCanal canal;
	
	ServidorContexto(ServidorEmissor emissor, ServidorReceptor receptor,
			ServidorManipuladorRequisicao manipulador, ServidorCanal canal) {
		super();
		this.emissor = emissor;
		this.receptor = receptor;
		this.manipulador = manipulador;
		this.canal = canal;
	}
	
	ServidorEmissor getEmissor() {
		return emissor;
	}
	void setEmissor(ServidorEmissor emissor) {
		this.emissor = emissor;
	}
	ServidorReceptor getReceptor() {
		return receptor;
	}
	void setReceptor(ServidorReceptor receptor) {
		this.receptor = receptor;
	}
	ServidorManipuladorRequisicao getManipulador() {
		return manipulador;
	}
	void setManipulador(ServidorManipuladorRequisicao manipulador) {
		this.manipulador = manipulador;
	}
	ServidorCanal getCanal() {
		return canal;
	}
	
	void setCanal(ServidorCanal canal) {
		this.canal = canal;
	}
	
	public <T extends Serializable> void enviarRespostaCliente(Resposta<T> resposta) {
		this.getEmissor().adicionar(resposta);
	}
	
	public void fechar() throws IOException {
		this.getManipulador().interrupt();
		this.getEmissor().interrupt();
		this.getReceptor().interrupt();
		this.getCanal().fechar();
	}
	
	void start(){
		this.emissor.setContexto(this);
		this.receptor.setContexto(this);
		this.manipulador.setContexto(this);
		
		this.manipulador.start();
		this.emissor.start();
		this.receptor.start();
	}
	
}
