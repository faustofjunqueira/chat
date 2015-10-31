package chat.comum.conexao.servidor;

public class ServidorContexto {

	private ServidorEmissor emissor;
	private ServidorReceptor receptor;
	private ServidorManipuladorRequisicao manipulador;
	private ServidorCanal canal;
	
	public ServidorContexto(ServidorEmissor emissor, ServidorReceptor receptor,
			ServidorManipuladorRequisicao manipulador, ServidorCanal canal) {
		super();
		this.emissor = emissor;
		this.receptor = receptor;
		this.manipulador = manipulador;
		this.canal = canal;
	}
	
	public ServidorEmissor getEmissor() {
		return emissor;
	}
	public void setEmissor(ServidorEmissor emissor) {
		this.emissor = emissor;
	}
	public ServidorReceptor getReceptor() {
		return receptor;
	}
	public void setReceptor(ServidorReceptor receptor) {
		this.receptor = receptor;
	}
	public ServidorManipuladorRequisicao getManipulador() {
		return manipulador;
	}
	public void setManipulador(ServidorManipuladorRequisicao manipulador) {
		this.manipulador = manipulador;
	}
	public ServidorCanal getCanal() {
		return canal;
	}
	public void setCanal(ServidorCanal canal) {
		this.canal = canal;
	}
	
	public void start(){
		this.emissor.setContexto(this);
		this.receptor.setContexto(this);
		this.manipulador.setContexto(this);
		
		this.manipulador.start();
		this.emissor.start();
		this.receptor.start();
	}
	
}
