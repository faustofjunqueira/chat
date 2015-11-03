package chat.view;

import java.util.LinkedList;
import java.util.List;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.filtro.Filtro;
import chat.dominio.repositorio.RepositorioContainer;
import chat.infra.conexao.Requisicao;
import chat.infra.conexao.cliente.Dispatcher;
import chat.infra.conexao.cliente.Ouvinte;

public class ClienteController {

	private Usuario usuario;
	private LoginView loginView;
	private ContatoView contatoView;
	private AtualizaView atualizaView;
	private List<ChatView> salasAbertas;
	private Thread atualizaViewThread;
	private Ouvinte<Usuario, Mensagem> ouvinteMensagem;

	public ClienteController() {
		long atrasoEmMiliSegundos = 5000;
		this.loginView = new LoginView("BFChat - Login", this);
		this.contatoView = new ContatoView("BFChat - Contatos", this);
		this.atualizaView = new AtualizaView(this, atrasoEmMiliSegundos);
		this.salasAbertas = new LinkedList<>();
	}

	public void comecarApp() {
		this.loginView.render();
		atualizaViewThread = new Thread(this.atualizaView);
		atualizaViewThread.start();
	}

	private void atualizaChat(Mensagem mensagem) {
		for (ChatView chatView : salasAbertas) {
			if (chatView.getSala().getId().equals(mensagem.getSala().getId())) {
				chatView.renderMensagem(mensagem);
				break;
			}
		}
	}

	public void enviaMensagem(Mensagem mensagem) {
		Dispatcher.Invocar(new Requisicao<Mensagem>("MensagemServico.enviar", mensagem)).pronto(null);
	}

	public void logar(String nome) {
		usuario = new Usuario(nome);
		Dispatcher.Invocar(new Requisicao<Usuario>("UsuarioServico.criarUsuario", usuario)).pronto((idUsuario) -> {
			getUsuario().setId((int) idUsuario);
			contatoView.setTituloPeloUsuario();
			ouvinteMensagem = Ouvinte.Invocar(new Requisicao<Usuario>("UsuarioServico.entrar", getUsuario()),
					(dado) -> {
				Mensagem mensagem = (Mensagem) dado;
				if (mensagem != null) {
					RepositorioContainer.Instance().Messagem().adicionar(mensagem);
					atualizaChat(mensagem);
				}
			});
		});

	}

	public void atualizaContatosESalas(List<Usuario> usuarios, List<Sala> salas) {
		contatoView.renderizarContatos(usuarios);
		contatoView.renderizarSalas(salas);
	}

	public void renderContato() {
		contatoView.render();
	}

	private void renderChat(ChatView salaView) {
		List<Mensagem> listaMensagens = RepositorioContainer.Instance().Messagem().buscar(new Filtro<Mensagem>(
				(mensagem) -> ((Mensagem) mensagem).getSala().getId().equals(salaView.getSala().getId())));
		salaView.render(listaMensagens);
	}

	private ChatView salaEstaAberta(Sala sala) {
		for (ChatView chatView : salasAbertas) {
			if (chatView.getSala().getId().equals(chatView.getSala().getId())) {
				return chatView;
			}
		}
		return null;
	}

	public void novaSala(Sala sala) {
		ChatView salaView = null;
		if ( (salaView = salaEstaAberta(sala)) == null) {
			salaView = new ChatView(this, sala);
			salasAbertas.add(salaView);
			renderChat(salaView);
		}
	}

	public void criarSala(List<Usuario> usuarios) {
		usuarios.add(getUsuario());
		Sala sala = new Sala();
		sala.setUsuarios(usuarios);
		sala.setNome(sala.gerarNomeSala());
		Dispatcher.Invocar(new Requisicao<Sala>("SalaServico.criarSala", sala)).pronto((id) -> sala.setId((int) id));
		novaSala(sala);
	}

	public List<Usuario> getListaUsuariosLogados() {
		return RepositorioContainer.Instance().Usuario().buscar(null);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println("Finalize COntroller");
		Dispatcher.Fechar();
		atualizaViewThread.interrupt();
		super.finalize();
	}

}
