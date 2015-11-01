package chat.comum.conexao.cliente;

import java.io.Serializable;
import java.util.function.Consumer;

import chat.comum.conexao.Requisicao;

public class Ouvinte<T extends Serializable> extends Dispatcher<T>{
	
	private Ouvinte(Requisicao<T> _requisicao) {
		super(_requisicao);
	}
	
	Ouvinte(Requisicao<T> _requisicao, Consumer<T> funcaoPronto) {
		super(_requisicao);
		pronto(funcaoPronto);
	}

	public static <T extends Serializable> Ouvinte<T> Invocar(Requisicao<T> _requisicao, Consumer<T> funcaoPronto){
		Ouvinte<T> ouvinte = new Ouvinte<T>(_requisicao,funcaoPronto);
		ouvinte.encaminhar();
		return ouvinte;
	}
	
	@Override
	protected void perfome_pronto() {
		getFuncaoPronto().accept(getResposta().getDados());
	}

}
