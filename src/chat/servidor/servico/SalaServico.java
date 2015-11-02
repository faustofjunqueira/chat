package chat.servidor.servico;

import java.util.List;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.auxiliar.UsuarioSalaComponente;
import chat.dominio.entidade.nucleo.SalasContainer;
import chat.infra.conexao.Requisicao;

public class SalaServico extends Servico {

	public SalaServico() {
		super();
	}
	
	public List<Sala> todas(Requisicao<Sala> requisicao){
		return SalasContainer.Instance().todasSalas();
	}
	
	public int criarSala(Requisicao<Sala> requisicao) {	
		return SalasContainer.Instance().criarSala(requisicao.getDados());
	}
	
	public boolean excluirSala(Requisicao<Sala> requisicao){
		SalasContainer.Instance().removerSala(requisicao.getDados());
		return true;
	}
	
	public boolean entraNaSala(Requisicao<UsuarioSalaComponente> requisicao) {
		SalasContainer.Instance().entrarNaSala(requisicao.getDados().getUsuario(), requisicao.getDados().getSala());
		return true;
	}
	
	public boolean sairDaSala(Requisicao<UsuarioSalaComponente> requisicao) {
		SalasContainer.Instance().sairDaSala(requisicao.getDados().getUsuario(), requisicao.getDados().getSala());
		return true;
	}
}
