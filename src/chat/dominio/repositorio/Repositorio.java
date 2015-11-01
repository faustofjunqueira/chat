package chat.dominio.repositorio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import chat.dominio.entidade.Dominio;
import chat.dominio.filtro.Filtro;

public class Repositorio<D extends Dominio, F extends Filtro<D>> implements IRepositorio<D, F> {

	private ArrayList<D> buffer;

	public Repositorio() {
		buffer = new ArrayList<>();
	}

	public Repositorio(int n) {
		buffer = new ArrayList<>(n);
	}

	@Override
	public void adicionar(D elemento) {
		buffer.add(elemento);
	}

	@Override
	public List<D> buscar(F filtro) {
		List<D> resultado = new LinkedList<>();
		for (D d : buffer) {
			if (filtro.filtrar(d)) {
				resultado.add(d);
			}
		}
		return resultado;
	}

	@Override
	public void remover(Integer id) {
		for (D d : buffer) {
			if (d.getId().equals(id)) {
				buffer.remove(d);
			}
		}
	}

	@Override
	public D getPeloId(Integer id) {
		for (D d : buffer) {
			if (d.getId().equals(id)) {
				return d;
			}
		}
		return null;
	}

	@Override
	public void adicionarTodos(List<D> l) {
		buffer.addAll(l);
	}

	@Override
	public IRepositorio<D, F> limpar() {
		buffer.clear();
		return this;
	}

}
