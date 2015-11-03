package chat.bootstrap;

import java.io.IOException;
import java.util.Calendar;

import chat.cliente.AtualizaChat;
import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.entidade.auxiliar.UsuarioSalaComponente;
import chat.dominio.repositorio.RepositorioContainer;
import chat.infra.conexao.Requisicao;
import chat.infra.conexao.cliente.Dispatcher;
import chat.infra.conexao.cliente.Ouvinte;
import chat.view.ClienteController;

public class Cliente {

	private long tempoDeAtrasoEmMilisegundos = 3000;
	private Thread popRepositorioThread;
	
	private void iniciaDispatcher() throws IOException {
		try {
			Dispatcher.Bootstap(Dispatcher.IP_DEFAULT, Dispatcher.PORTA_DEFAULT);
		} catch (IOException e) {
			System.err.println("Erro ao iniciar Dispatcher");
			e.printStackTrace();
			throw e;
		}
	}

	private void iniciaPopuladorDoRepositorio(){
		try{
			popRepositorioThread = new AtualizaChat(tempoDeAtrasoEmMilisegundos);
			popRepositorioThread.start();
		}catch(Exception e){
			System.err.println("Erro ao iniciar Dispatcher");
			e.printStackTrace();
			throw e;
		}
	}
	
	private void iniciaController(){
		ClienteController controller = new ClienteController();
		controller.comecarApp();
	}
	
//	private void Usuario1() {
//		Usuario usuario = new Usuario();
//		Sala sala = new Sala();
//
//		sala.setId(2);
//		usuario.setId(3);
//
//		System.out.println("Usuario: " + usuario.getId());
//		System.out.println("Sala: " + sala.getId());
//
//		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.criarSala", sala))
//				.pronto((concluido) -> System.out.println("sala criada"));
//
//		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.todas", null))
//				.pronto((salas) -> System.out.println(salas));
//
//		Ouvinte.Invocar(new Requisicao<Usuario>("UsuarioServico.entrar", usuario), (dado) -> {
//			Mensagem mensagem = (Mensagem) dado;
//			if (mensagem != null) {
//				System.out.println(mensagem.toString());
//			}
//		});
//
//		Dispatcher.Invocar(new Requisicao<UsuarioSalaComponente>("SalaServico.entraNaSala",
//				new UsuarioSalaComponente(usuario, sala))).pronto((concluido) -> {
//					System.out.println("Usuario logado");
//				});

//	}

	
	public static void main(String[] args) throws IOException {
		Cliente cliente = new Cliente();
		cliente.iniciaDispatcher();
		cliente.iniciaPopuladorDoRepositorio();
		cliente.iniciaController();
	}

}
