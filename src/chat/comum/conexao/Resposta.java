package chat.comum.conexao;

import java.io.Serializable;

public class Resposta<T extends Serializable> implements Serializable {
	public static final short COD_VAZIO = 0;
	public static final short COD_SUCESSO = 1;
	public static final short COD_ERRO_PADRAO = 100;
	
	private short status;
	private boolean isErro;
	T dados;
	
	public Resposta(short status, T dados) {
		this.status = status;
		this.dados = dados;
	}

	public short getStatus() {
		return status;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public boolean isErro() {
		return isErro;
	}

	public void setErro(boolean isErro) {
		this.isErro = isErro;
	}

	public T getDados() {
		return dados;
	}

	public void setDados(T dados) {
		this.dados = dados;
	}
	
	@Override
	public String toString() {
		return "status: "+status+" isErro: "+isErro+" dado: "+(String) dados;
	}
	
}
