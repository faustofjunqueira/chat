package chat.comum.conexao.cliente;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: Remover o Dispatcher do Map
class ClienteContexto {

	private static ClienteContexto objetoSingleton;
	public static ClienteContexto Instance() {
		if (objetoSingleton == null) {
			objetoSingleton = new ClienteContexto();
		}
		
		return objetoSingleton;
	}

	private Map<String, Dispatcher<?>> BufferDeEnvio;
	private ClienteCanal canal;
	private Thread EmissorThread;
	private Thread ReceptorThread;

	private ClienteContexto() {
		BufferDeEnvio = new HashMap<>();
	}
	
	void iniciarContexto(){
		ReceptorThread = new ReceptorThread();
		EmissorThread = new EmissorThread();
		ReceptorThread.start();
		EmissorThread.start();
	}

	void setCanal(ClienteCanal canal) {
		this.canal = canal;
	}

	ClienteCanal getCanal() {
		return canal;
	}

	void atualizaStatus(List<Dispatcher<?>> lista, EnumEmissorStatus status) {
		for (Dispatcher<?> d : lista) {
			d.setStatus(status);
		}
	}

	Dispatcher<?> getDispatcherPeloHash(String hash) {
		System.out.println("GetDispatcherHash: buffer size: "+BufferDeEnvio.size());
		return BufferDeEnvio.get(hash);
	}

	synchronized void adicionarDispatcher(Dispatcher<?> dispatcher) {
		BufferDeEnvio.put(dispatcher.getHash(), dispatcher);
		notify();
	}

	synchronized void removeDispatcher(Dispatcher<?> dispatcher) {
		BufferDeEnvio.remove(dispatcher);
	}

	synchronized List<Dispatcher<?>> getListaRequisicaoParaEnviar() throws InterruptedException {
		List<Dispatcher<?>> lista = new ArrayList<>();
		EnumEmissorStatus status = EnumEmissorStatus.ESPERANDO;

		// TODO: Limitar o tamanho da lista talvez
		for (Dispatcher<?> d : BufferDeEnvio.values()) {
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
