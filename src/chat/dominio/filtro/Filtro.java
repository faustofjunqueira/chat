package chat.dominio.filtro;


import java.util.function.Predicate;

import chat.dominio.entidade.Dominio;

public class Filtro<D extends Dominio> {
	
	private Predicate<D> func;
	
	public Filtro(Predicate<D> func){
		this.func = func;
	}
	
	public boolean filtrar(D dominio){
		return restricao().test(dominio);
	}
	
	public Predicate<D> restricao(){
		return func;
	}
}
