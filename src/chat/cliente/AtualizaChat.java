package chat.cliente;

import java.util.List;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.repositorio.RepositorioContainer;
import chat.infra.conexao.Requisicao;
import chat.infra.conexao.cliente.Dispatcher;

public class AtualizaChat implements Runnable{

	private long tempoDeAtraso;
	
	public AtualizaChat(long tempoDeAtrasoEmMilisegundos) {}

	@SuppressWarnings("unchecked")
	private void buscaSalas(){
		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.todas", null))
		.pronto((salas) -> RepositorioContainer.Instance().Sala().limpar().adicionarTodos((List<Sala>) salas));
	}

	@SuppressWarnings("unchecked")
	private void buscaUsuariosLogados(){
		Dispatcher.Invocar(new Requisicao<Sala>("UsuarioServico.todos", null))
		.pronto((usuarios) -> RepositorioContainer.Instance().Usuario().limpar().adicionarTodos((List<Usuario>) usuarios));
	}
	
	@Override
	public void run() {
		// TODO: while true
		while(true) {
			buscaSalas();
			buscaUsuariosLogados();
			try {
				Thread.sleep(tempoDeAtraso);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
