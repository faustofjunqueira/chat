package chat.comum.conexao.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chat.comum.conexao.ProdutoConsumidor;
import chat.comum.conexao.Requisicao;
import chat.comum.conexao.Resposta;

//TODO: Remover o Dispatcher do Map
class ClienteContexto {

	private class ReceptorThread extends Thread {

		private ClienteContexto contexto;

		ReceptorThread() {
			super();
			contexto = ClienteContexto.Instance();
		}
		
		private void invocarProcessoRecebimento() throws IOException, ClassNotFoundException {
			List<Resposta<?>> listaRecebidos = contexto.getCanal().receber();
			for (Resposta<?> resposta : listaRecebidos) {
				Dispatcher dispatcher = contexto.getDispatcherPeloHash(resposta.getHash());
				dispatcher.setStatus(EnumEmissorStatus.RECEBIDO);
				contexto.removeDispatcher(dispatcher);
				dispatcher.respostaObtida(resposta);
			}
		}

		public void run() {
			contexto = ClienteContexto.Instance();
			try {
				//TODO: while true
				while (true) {
					invocarProcessoRecebimento();
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	private class EmissorThread extends Thread {

		EmissorThread() {
			super();
		}
		
		private ClienteContexto contexto = ClienteContexto.Instance();

		private void invocarProcessoEnviados() throws IOException, InterruptedException {
			List<Dispatcher> listaParaEnviar = contexto.getListaRequisicaoParaEnviar();
			List<Requisicao<?>> listaRequisicao = new ArrayList<>(listaParaEnviar.size());
			for (Dispatcher d : listaParaEnviar) {
				listaRequisicao.add(d.getRequisicao());
			}
			contexto.getCanal().enviar(listaRequisicao);
			contexto.atualizaStatus(listaParaEnviar, EnumEmissorStatus.ENVIADO);
		}

		public void run() {
			contexto = ClienteContexto.Instance();
			try {
				// TODO: while true;
				while (true) {
					invocarProcessoEnviados();
				}
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private static ClienteContexto objetoSingleton;

	public static ClienteContexto Instance() {
		if (objetoSingleton == null) {
			objetoSingleton = new ClienteContexto();
		}
		
		return objetoSingleton;
	}

	private Map<String, Dispatcher> BufferDeEnvio;
	private ClienteCanal canal;
	private Thread EmissorThread;
	private Thread ReceptorThread;

	private ClienteContexto() {
		BufferDeEnvio = new HashMap<>();
	}
	
	public void iniciarContexto(){
		ReceptorThread = new ReceptorThread();
		EmissorThread = new EmissorThread();
		ReceptorThread.start();
		EmissorThread.start();
	}

	public void setCanal(ClienteCanal canal) {
		this.canal = canal;
	}

	public ClienteCanal getCanal() {
		return canal;
	}

	public void atualizaStatus(List<Dispatcher> lista, EnumEmissorStatus status) {
		for (Dispatcher d : lista) {
			d.setStatus(status);
		}
	}

	public Dispatcher getDispatcherPeloHash(String hash) {
		return BufferDeEnvio.get(hash);
	}

	public void adicionarDispatcher(Dispatcher dispatcher) {
		BufferDeEnvio.put(dispatcher.getHash(), dispatcher);
		notify();
	}

	public synchronized void removeDispatcher(Dispatcher dispatcher) {
		BufferDeEnvio.remove(dispatcher);
	}

	public synchronized List<Dispatcher> getListaRequisicaoParaEnviar() throws InterruptedException {
		List<Dispatcher> lista = new ArrayList<>();
		EnumEmissorStatus status = EnumEmissorStatus.ESPERANDO;

		// TODO: Limitar o tamanho da lista talvez
		for (Dispatcher d : BufferDeEnvio.values()) {
			if (d.getStatus() == status) {
				lista.add(d);
			}
		}

		if (lista.size() == 0) {
			wait();
			return getListaRequisicaoParaEnviar();
		} else {
			return lista;
		}
	}

	@Override
	protected void finalize() throws Throwable {
		canal.fechar();
		ReceptorThread.interrupt();
		EmissorThread.interrupt();
		super.finalize();
	}
}
