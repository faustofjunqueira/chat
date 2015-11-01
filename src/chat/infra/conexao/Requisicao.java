package chat.infra.conexao;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Set;

import chat.util.GeradorSerial;

public class Requisicao<T extends Serializable> implements Serializable {

	public class Cabecalho implements Serializable {
		
		private Hashtable<String, String> cabecalho;

		public Cabecalho(Hashtable<String, String> cabecalho) {
			this.cabecalho = cabecalho;
		}

		public Set<String> TodasChaves() {
			return this.cabecalho.keySet();
		}

		public void adicionar(String chave, String valor) {
			if (cabecalho.containsKey(chave)) {
				cabecalho.replace(chave, valor);
			} else {
				cabecalho.put(chave, valor);
			}
		}

		public String buscar(String chave) {
			return cabecalho.getOrDefault(chave, null);
		}

		public void remover(String chave, String valor) {
			if (cabecalho.containsKey(chave)) {
				cabecalho.remove(chave);
			}
		}

	}

	public static Hashtable<String, String> Cabecalho = new Hashtable<String, String>();
	public static String PackagePadrao = "chat.servidor.servico";
	
	private String hash;
	private String pacote;
	private String classe;
	private String acao;
	T dados;
	private Cabecalho cabecalho;

	public Requisicao(String classe, String acao, T dados) {
		this.classe = classe;
		this.acao = acao;
		this.dados = dados;
		this.pacote = PackagePadrao;
		this.hash = GeradorSerial.Criar();
		cabecalho = new Cabecalho(Cabecalho);
	}

	public Requisicao(String rota, T dados) {
		if (rota != null) {
			String splitado[] = rota.split("\\.");
			this.classe = splitado[0];
			this.acao = splitado[1];
		}
		this.dados = dados;
		this.hash = GeradorSerial.Criar();
		this.pacote = PackagePadrao;
		cabecalho = new Cabecalho(Cabecalho);
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

	public Cabecalho getCabecalho() {
		return cabecalho;
	}

	public final String getPacote() {
		return pacote;
	}

	public final void setPacote(String pacote) {
		this.pacote = pacote;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}