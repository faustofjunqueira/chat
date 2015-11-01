package chat.infra.conexao.cliente;

import java.io.Serializable;
import java.util.function.Consumer;

import chat.infra.conexao.Requisicao;

public class Ouvinte<I extends Serializable, O extends Serializable> extends Dispatcher<I,O>{
	
	private Ouvinte(Requisicao<I> _requisicao) {
		super(_requisicao);
	}
	
	Ouvinte(Requisicao<I> _requisicao, Consumer<O> funcaoPronto) {
		super(_requisicao);
		pronto(funcaoPronto);
	}

	public static <U extends Serializable, V extends Serializable> Ouvinte<U,V> Invocar(Requisicao<U> _requisicao, Consumer<V> funcaoPronto){
		Ouvinte<U,V> ouvinte = new Ouvinte<U,V>(_requisicao,funcaoPronto);
		ouvinte.encaminhar();
		return ouvinte;
	}
	
	@Override
	protected void perfome_pronto() {
		getFuncaoPronto().accept(getResposta().getDados());
	}

}
