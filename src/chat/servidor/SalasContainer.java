package chat.servidor;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.util.GeradorSerial;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class SalasContainer{
	
	private static SalasContainer container;
	public static SalasContainer Instance() {
		if (container == null ) {
			container = new SalasContainer();
		}
		return container;
	}
	
	private List<SalaContexto> salasAbertas;
	private List<UsuarioConectado> usuariosConectados;
	
	public SalasContainer() {
		salasAbertas = new LinkedList<>();
		usuariosConectados = new LinkedList<>();
	}
	
	public synchronized void entrarUsuario(UsuarioConectado usuario){
		usuariosConectados.add(usuario);
	}
	
	public synchronized void sairUsuario(Usuario usuario){
		UsuarioConectado usuarioConectado = encontrarUsuarioConectado(usuario);
		usuariosConectados.remove(usuarioConectado);
	}
	
	public synchronized int criarSala(Sala sala) {
		sala.setId(GeradorSerial.AutoIncremento());
		salasAbertas.add(new SalaContexto(sala));
		return sala.getId();
	}
	
	public synchronized void removerSala(Sala sala) {
		SalaContexto salaContexto = encontrarSalaContexto(sala);
		salasAbertas.remove(salaContexto);
	}
	
	public synchronized void entrarNaSala(Usuario usuario, Sala sala){
		SalaContexto salaContexto = encontrarSalaContexto(sala);
		UsuarioConectado usuarioConectado = encontrarUsuarioConectado(usuario);
		salaContexto.entrar(usuarioConectado);
	}
	
	public synchronized void sairDaSala(Usuario usuario, Sala sala) {
		SalaContexto salaContexto = encontrarSalaContexto(sala);
		UsuarioConectado usuarioConectado = encontrarUsuarioConectado(usuario);
		salaContexto.sair(usuarioConectado);
	}
	
	public List<Usuario> todosUsuariosConectados() {
		List<Usuario> resultado = new ArrayList<>();
		for (UsuarioConectado usuarioConectado : usuariosConectados) {
			resultado.add(usuarioConectado.getUsuario());
		}
		return resultado;
	}
	
	public List<Sala> todasSalas() {
		List<Sala> resultado = new ArrayList<>();
		for (SalaContexto sala : salasAbertas) {
			resultado.add(sala.getSala());
		}
		return resultado;
	}
	
	public void enviarMensagem(Mensagem mensagem) {
		SalaContexto sala = encontrarSalaContexto(mensagem.getSala());
		sala.enviarMensagem(mensagem);
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
