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
		System.out.println("GetDispatcherHash: buffer size: "+BufferDeEnvio.size());
		return BufferDeEnvio.get(hash);
	}

	public synchronized void adicionarDispatcher(Dispatcher dispatcher) {
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
