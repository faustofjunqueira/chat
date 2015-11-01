package chat.infraconexao.cliente;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.function.Consumer;

import chat.infra.conexao.Requisicao;
import chat.infra.conexao.Resposta;
import chat.infra.conexao.servidor.ServidorContainer;

public class Dispatcher<T extends Serializable> {

	public static String IP_DEFAULT = null;
	public static short PORTA_DEFAULT = 0;

	public static void Bootstap(String host, short porta) throws UnknownHostException, IOException {
		if (host == null) {
			host = ServidorContainer.IP_DEFAULT;
		}
		if (porta == 0) {
			porta = ServidorContainer.PORTA_DEFAULT;
		}
		Socket _socket = new Socket(host, porta);
		ClienteContexto.Instance().setCanal(new ClienteCanal(_socket));
		ClienteContexto.Instance().iniciarContexto();
	}

	public static void Fechar() throws Throwable {
		try {
			ClienteContexto.Instance().finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private Requisicao<T> requisicao;
	private Resposta<T> resposta;
	private EnumEmissorStatus status;
	private Consumer<T> funcao_pronto;

	Dispatcher(Requisicao<T> _requisicao) {
		requisicao = _requisicao;
		resposta = null;
		status = EnumEmissorStatus.INDEFINIDO;
	}

	public static <T extends Serializable> Dispatcher<T> Invocar(Requisicao<T> _requisicao) {
		Dispatcher<T> dispatcher = new Dispatcher<T>(_requisicao);
		dispatcher.encaminhar();
		return dispatcher;
	}

	public void reInvocar() {
		encaminhar();
	}

	protected void encaminhar() {
		setStatus(EnumEmissorStatus.ESPERANDO);
		ClienteContexto.Instance().adicionarDispatcher(this);
	}

	protected void perfome_pronto() {
		ClienteContexto.Instance().removeDispatcher(this);
		funcao_pronto.accept(resposta.getDados());
	}

	public void pronto(Consumer<T> lambda) {
		funcao_pronto = lambda;
		if (resposta != null) {
			perfome_pronto();
		}
	}

	synchronized void respostaObtida(Resposta<T> reposta) {
		setResposta(reposta);
		if (funcao_pronto != null) {
			perfome_pronto();
		}
	}

	public Requisicao<T> getRequisicao() {
		return requisicao;
	}

	Resposta<T> getResposta() {
		return resposta;
	}

	void setResposta(Resposta<T> resposta) {
		this.resposta = resposta;
	}

	public EnumEmissorStatus getStatus() {
		return status;
	}

	public String getHash() {
		return getRequisicao().getHash();
	}

	protected Consumer<T> getFuncaoPronto() {
		return funcao_pronto;
	}

	void setStatus(EnumEmissorStatus status) {
		this.status = status;
	}
}
