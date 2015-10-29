package chat.comum.conexao;

import java.io.Serializable;

public class Requisicao<T extends Serializable> implements Serializable {
	private String classe;
	private String acao;
	T dados;

	public Requisicao(String classe, String acao, T dados) {
		this.classe = classe;
		this.acao = acao;
		this.dados = dados;
	}

	public Requisicao(String rota, T dados) {
		String splitado[] = rota.split(" ");
		this.classe = splitado[0];
		this.acao = splitado[1];
		this.dados = dados;
	}

	@Override
	public String toString() {
		return "classe: " + classe + " acao: " + acao + " dados: " + (String) dados;
	}

	public String getClasse() {
		return classe;
	}

	public String getAcao() {
		return acao;
	}

	public T getDados() {
		return dados;
	}	
}
