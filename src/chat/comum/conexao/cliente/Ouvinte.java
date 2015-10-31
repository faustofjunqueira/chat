package chat.comum.conexao.cliente;

import java.io.Serializable;
import java.util.function.Consumer;

import chat.comum.conexao.Requisicao;

public class Ouvinte<T extends Serializable> {
	
	public static <T extends Serializable> Ouvinte<T> Invocar(Requisicao<T> _requisicao, Consumer<T> funcaoPronto, long TempoDeAtraso){
		Dispatcher<T> dispatcher = Dispatcher.Invocar(_requisicao);
		dispatcher.pronto(funcaoPronto);
		return new Ouvinte<T>(dispatcher,TempoDeAtraso);
	}
	
	private Dispatcher<T> dispatcher;
	private long tempoDeAtraso;
	
	public Ouvinte(Dispatcher<T> dispatcher, long tempoDeAtraso) {
		this.dispatcher = dispatcher;
		this.tempoDeAtraso = tempoDeAtraso;
	}

	public Dispatcher<T> getDispatcher() {
		return dispatcher;
	}

	public long getTempoDeAtraso() {
		return tempoDeAtraso;
	}

	public void executar() throws InterruptedException{
		while(true){ // TODO: while true
			getDispatcher().reInvocar();
			Thread.sleep(getTempoDeAtraso());
		}
	}
	
}
