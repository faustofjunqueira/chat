package chat.servidor.servico;

import java.util.List;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.entidade.auxiliar.UsuarioSalaComponente;
import chat.infra.conexao.Requisicao;
import chat.servidor.SalasContainer;

public class SalaServico extends Servico {

	public SalaServico() {
		super();
	}
	
	public List<Sala> todas(Requisicao<Sala> requisicao){
		return SalasContainer.Instance().todasSalas();
	}
	
	public int criarSala(Requisicao<Sala> requisicao) {
		Sala sala = requisicao.getDados();
		int idSalaCriada = SalasContainer.Instance().criarSala(sala);
		if(sala.getUsuarios() != null){
			for (Usuario u: sala.getUsuarios()) {
				SalasContainer.Instance().entrarNaSala(u, sala);
			}
		}
		return idSalaCriada;
	}
	
	public boolean excluirSala(Requisicao<Sala> requisicao){
		SalasContainer.Instance().removerSala(requisicao.getDados());
		return true;
	}
	
	public boolean entraNaSala(Requisicao<UsuarioSalaComponente> requisicao) {
		UsuarioSalaComponente componente = requisicao.getDados();
		if(componente.getUsuario() != null){
			for(Usuario u : componente.getUsuario()) {
				SalasContainer.Instance().entrarNaSala(u,componente.getSala());
			}
		}
		return true;
	}
	
	public boolean sairDaSala(Requisicao<UsuarioSalaComponente> requisicao) {
		UsuarioSalaComponente componente = requisicao.getDados();
		if(componente.getUsuario() != null){
			for(Usuario u : componente.getUsuario()) {
				SalasContainer.Instance().sairDaSala(u, componente.getSala());
			}
		}
		return true;
	}
}
