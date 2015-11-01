package chat.servidor.servico;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.entidade.auxiliar.UsuarioSalaComponente;
import chat.dominio.entidade.nucleo.SalasContainer;
import chat.infra.conexao.Requisicao;

public class SalaServico extends Servico {

	public SalaServico() {
		super();
	}
	
	public boolean criarSala(Requisicao<Sala> requisicao) {
		SalasContainer.Instance().criarSala(requisicao.getDados());
		return true;
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
