package chat.view;

import chat.dominio.repositorio.RepositorioContainer;


import chat.dominio.entidade.*;
import chat.dominio.filtro.Filtro;

public class AtualizaView implements Runnable {

	private ClienteController controller;
	private long atrasoEmMiliSegundos;

	public AtualizaView(ClienteController controller, long atrasoEmMiliSegundos) {
		this.controller = controller;
		this.atrasoEmMiliSegundos = atrasoEmMiliSegundos;
	}

	@Override
	public void run() {
		while (true) {
			Filtro<Sala> filtroSala = new Filtro<Sala>( (sala) -> sala.usuarioEstaNaSala(controller.getUsuario()));
			controller.atualizaContatosESalas(RepositorioContainer.Instance().Usuario().buscar(null), 
					RepositorioContainer.Instance().Sala().buscar(filtroSala) );
						
			try {
				Thread.sleep(atrasoEmMiliSegundos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
