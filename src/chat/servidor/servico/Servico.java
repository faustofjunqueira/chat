package chat.servidor.servico;

import chat.infra.conexao.servidor.ServidorContexto;

public abstract class Servico {

	private ServidorContexto contexto;
	
	public final ServidorContexto getContexto() {
		return contexto;
	}

	public final void setContexto(ServidorContexto contexto) {
		this.contexto = contexto;
	}
	
}
