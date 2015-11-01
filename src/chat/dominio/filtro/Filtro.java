package chat.dominio.filtro;


import java.util.function.Predicate;

import chat.dominio.entidade.Dominio;

public abstract class Filtro<D extends Dominio> {
	
	public boolean filtrar(D dominio){
		return restricao().test(dominio);
	}
	
	abstract Predicate<D> restricao();
}
