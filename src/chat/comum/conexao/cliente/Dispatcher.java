package chat.comum.conexao.cliente;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

import chat.comum.conexao.GeradorSerial;
import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;
import chat.comum.conexao.servidor.ServidorContainer;

public class Dispatcher <T extends Serializable>{
	
	public static String IP_DEFAULT = null;
	public static short PORTA_DEFAULT = 0;
	
	public static void Bootstap(String host, short porta) throws UnknownHostException, IOException {
		if(host == null ) {
			host = ServidorContainer.IP_DEFAULT;
		}
		if(porta == 0) {
			porta = ServidorContainer.PORTA_DEFAULT;
		}
		Socket _socket = new Socket(host, porta);
		ClienteContexto.Instance().setCanal(new ClienteCanal(_socket));
		ClienteContexto.Instance().iniciarContexto();
	}
	
	private Requisicao<T> requisicao;
	private Resposta<T> resposta;
	private EnumEmissorStatus status;
	private Consumer<T> funcao_pronto;	
	
	public Dispatcher(Requisicao<T> _requisicao) {
		requisicao = _requisicao;
		resposta = null;
		status = EnumEmissorStatus.INDEFINIDO;
	}
	
	public static <T extends Serializable> Dispatcher<T> Invocar(Requisicao<T> _requisicao){
		Dispatcher<T> dispatcher = new Dispatcher<T>(_requisicao);
		dispatcher.setStatus(EnumEmissorStatus.ESPERANDO);
		ClienteContexto.Instance().adicionarDispatcher(dispatcher);
		return dispatcher;
	}
	
	private void perfome_pronto(){
		funcao_pronto.accept(resposta.getDados());
	}
	
	public void pronto(Consumer<T> lambda){
		funcao_pronto = lambda;
		if (resposta != null) {
			perfome_pronto();
		}
	}

	public synchronized void respostaObtida(Resposta<T> reposta){
		setResposta(reposta);
		if(funcao_pronto != null){
			perfome_pronto();
		}
	}
	
	public Requisicao<T> getRequisicao() {
		return requisicao;
	}

	public Resposta<T> getResposta() {
		return resposta;
	}

	public void setResposta(Resposta<T> resposta) {
		this.resposta = resposta;
	}

	public EnumEmissorStatus getStatus() {
		return status;
	}

	public String getHash() {
		return getRequisicao().getHash();
	}

	public void setStatus(EnumEmissorStatus status) {
		this.status = status;
	}

	
}
