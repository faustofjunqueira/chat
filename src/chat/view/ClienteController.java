package chat.view;

import java.util.List;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.dominio.repositorio.RepositorioContainer;
import chat.infra.conexao.Requisicao;
import chat.infra.conexao.cliente.Dispatcher;
import chat.infra.conexao.cliente.Ouvinte;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ClienteController {

	private Usuario usuario;
	private LoginView loginView;
	private ContatoView contatoView;
	private ChatView chatView;
	private AtualizaView atualizaView;
	private Thread atualizaViewThread;
	private Ouvinte<Usuario,Mensagem> ouvinteMensagem;
	
	public ClienteController() {
		long atrasoEmMiliSegundos = 5000;
		this.loginView = new LoginView("BFChat - Login", this);
		this.contatoView = new ContatoView("BFChat - Contatos", this);
		this.chatView = new ChatView("BFChat - Chat", this);
		this.atualizaView = new AtualizaView(this, atrasoEmMiliSegundos);
	}

	public void comecarApp(){
		this.loginView.render();
		atualizaViewThread = new Thread(this.atualizaView);
		atualizaViewThread.start();
	}
	
	private void atualizaChat(Mensagem mensagem) {
		//TODO: implementar
		throw new NotImplementedException();
	}
	
	public void logar(String nome) {
		System.out.println("Logar");
		usuario = new Usuario(nome);
		Dispatcher.Invocar(new Requisicao<Usuario>("UsuarioServico.criarUsuario", usuario))
				.pronto((idUsuario) -> {
					getUsuario().setId((int) idUsuario);
					ouvinteMensagem = Ouvinte.Invocar(new Requisicao<Usuario>("UsuarioServico.entrar", usuario), null);
				});
	}

	public void atualizaContatosESalas(List<Usuario> usuarios, List<Sala> salas){
		System.out.println(usuarios);
		contatoView.renderizarContatos(usuarios);
		contatoView.renderizarSalas(salas);
	}
	
	public void renderContato() {
		contatoView.render();
	}
	
	public void renderChat(){
		
	}
	
	public List<Usuario> getListaUsuariosLogados(){
		return RepositorioContainer.Instance().Usuario().buscar(null);
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	@Override
	protected void finalize() throws Throwable {
		atualizaViewThread.interrupt();
		super.finalize();
	}

}
