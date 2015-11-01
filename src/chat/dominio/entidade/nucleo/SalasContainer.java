package chat.dominio.entidade.nucleo;

import java.util.Set;
import java.util.TreeSet;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;


public class SalasContainer{
	
	private static SalasContainer container;
	public static SalasContainer Instance() {
		if (container == null ) {
			container = new SalasContainer();
		}
		return container;
	}
	
	private Set<SalaContexto> salasAbertas;
	private Set<UsuarioConectado> usuariosConectados;
	
	public SalasContainer() {
		salasAbertas = new TreeSet<>();
		usuariosConectados = new TreeSet<>();
	}
	
	public void entrarUsuario(UsuarioConectado usuario){
		usuariosConectados.add(usuario);
	}
	
	public void sairUsuario(Usuario usuario){
		UsuarioConectado usuarioConectado = encontrarUsuarioConectado(usuario);
		usuariosConectados.remove(usuarioConectado);
	}
	
	public void criarSala(Sala sala) {
		salasAbertas.add(new SalaContexto(sala));
	}
	
	public void removerSala(Sala sala) {
		SalaContexto salaContexto = encontrarSalaContexto(sala);
		salasAbertas.remove(salaContexto);
	}
	
	public void entrarNaSala(Usuario usuario, Sala sala){
		SalaContexto salaContexto = encontrarSalaContexto(sala);
		UsuarioConectado usuarioConectado = encontrarUsuarioConectado(usuario);
		salaContexto.entrar(usuarioConectado);
	}
	
	public void sairDaSala(Usuario usuario, Sala sala) {
		SalaContexto salaContexto = encontrarSalaContexto(sala);
		UsuarioConectado usuarioConectado = encontrarUsuarioConectado(usuario);
		salaContexto.sair(usuarioConectado);
	}
	
	public void enviarMensagem(Mensagem mensagem) {
		// TODO: definir oq eh uma mensagem
		// TODO: escrever o metodo
	}
	
	private SalaContexto encontrarSalaContexto(Sala sala){
		for (SalaContexto salaContexto: salasAbertas) {
			if(salaContexto.getSala().getId().equals(sala.getId())){
				return salaContexto;
			}
		}
		return null;

	}
	
	private UsuarioConectado encontrarUsuarioConectado(Usuario usuario) {
		for (UsuarioConectado usuarioConectado : usuariosConectados) {
			if(usuarioConectado.getUsuario().getId().equals(usuario.getId())){
				return usuarioConectado;
			}
		}
		return null;
	}
	
}
