package chat.dominio.repositorio;

import java.util.List;

import chat.dominio.entidade.Dominio;
import chat.dominio.filtro.Filtro;

public interface IRepositorio<D extends Dominio> {
	/**
	 * adicionar
	 * remover
	 * buscar(filtro)
	 * listar
	 */
	
	void adicionar(D elemento);
	void adicionarTodos(List<D> l);
	List<D>  buscar(Filtro<D> filtro);
	void remover(Integer id);
	D getPeloId(Integer id);
	IRepositorio<D> limpar();
}
