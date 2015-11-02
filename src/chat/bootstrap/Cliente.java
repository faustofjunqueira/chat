package chat.bootstrap;

import java.io.IOException;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.entidade.auxiliar.UsuarioSalaComponente;
import chat.dominio.repositorio.PopulaRepositorio;
import chat.dominio.repositorio.RepositorioContainer;
import chat.infra.conexao.Requisicao;
import chat.infra.conexao.cliente.Dispatcher;
import chat.infra.conexao.cliente.Ouvinte;

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
			popRepositorioThread = new Thread(new PopulaRepositorio(tempoDeAtrasoEmMilisegundos));
			popRepositorioThread.start();
		}catch(Exception e){
			System.err.println("Erro ao iniciar Dispatcher");
			e.printStackTrace();
			throw e;
		}
	}
	
	private void Usuario1() {
		Usuario usuario = new Usuario();
		Sala sala = new Sala();

		sala.setId(2);
		usuario.setId(3);

		System.out.println("Usuario: " + usuario.getId());
		System.out.println("Sala: " + sala.getId());

		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.criarSala", sala))
				.pronto((concluido) -> System.out.println("sala criada"));

		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.todas", null))
				.pronto((salas) -> System.out.println(salas));

		Ouvinte.Invocar(new Requisicao<Usuario>("UsuarioServico.entrar", usuario), (dado) -> {
			Mensagem mensagem = (Mensagem) dado;
			if (mensagem != null) {
				System.out.println(mensagem.toString());
			}
		});

		Dispatcher.Invocar(new Requisicao<UsuarioSalaComponente>("SalaServico.entraNaSala",
				new UsuarioSalaComponente(usuario, sala))).pronto((concluido) -> {
					System.out.println("Usuario logado");
				});

	}

	private void Usuario2() {
		Usuario usuario = new Usuario();
		Sala sala = new Sala();

		sala.setId(2);
		usuario.setId(3);

		System.out.println("Usuario: " + usuario.getId());
		System.out.println("Sala: " + sala.getId());

		Dispatcher
				.Invocar(new Requisicao<Mensagem>("MensagemServico.enviar",
						new Mensagem("Um simples teste", usuario, sala)))
				.pronto((ok) -> System.out.println("Enviado: " + ok));

	}

	private void Usuario3() {
		Usuario usuario = new Usuario();
		Sala sala = new Sala();

		sala.setId(2);
		usuario.setId(4);

		System.out.println("Usuario: " + usuario.getId());
		System.out.println("Sala: " + sala.getId());

		Ouvinte.Invocar(new Requisicao<Usuario>("UsuarioServico.entrar", usuario), (dado) -> {
			Mensagem mensagem = (Mensagem) dado;
			if (mensagem != null) {
				System.out.println(mensagem.toString());
			}
		});

		Dispatcher.Invocar(new Requisicao<UsuarioSalaComponente>("SalaServico.entraNaSala",
				new UsuarioSalaComponente(usuario, sala))).pronto((concluido) -> {
					System.out.println("Entrou na sala");
				});

	}
	
	private void Usuario4() {
		Usuario usuario = new Usuario();
		Sala sala = new Sala();

		sala.setId(5);
		usuario.setId(6);

		System.out.println("Usuario: " + usuario.getId());
		System.out.println("Sala: " + sala.getId());

		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.criarSala", sala))
				.pronto((concluido) -> System.out.println("sala criada"));

		Ouvinte.Invocar(new Requisicao<Usuario>("UsuarioServico.entrar", usuario), (dado) -> {
			Mensagem mensagem = (Mensagem) dado;
			if (mensagem != null) {
				System.out.println(mensagem.toString());
			}
		});

		Dispatcher.Invocar(new Requisicao<UsuarioSalaComponente>("SalaServico.entraNaSala",
				new UsuarioSalaComponente(usuario, sala))).pronto((concluido) -> {
					System.out.println("Usuario logado");
				});

	}

	private void Usuario5(){
		Usuario usuario = new Usuario();
		Sala sala = new Sala();

		System.out.println("Usuario: " + usuario.getId());
		System.out.println("Sala: " + sala.getId());

		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.criarSala", sala))
				.pronto((concluido) -> System.out.println("sala criada"));

		try {
			Thread.sleep(10550);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(RepositorioContainer.Instance().Sala().buscar(null).size());

	}
	
	public static void main(String[] args) throws IOException {
		Cliente cliente = new Cliente();
		cliente.iniciaDispatcher();
		cliente.iniciaPopuladorDoRepositorio();
		
		if (args[0].equals("1")) {
			cliente.Usuario1();
		}else if (args[0].equals("2")) {
			cliente.Usuario2();
		}else if (args[0].equals("3")) {
			cliente.Usuario3();
		} else if (args[0].equals("4")){
			cliente.Usuario4();
		} else {
			cliente.Usuario5();
		}
	}

}
