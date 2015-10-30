package chat.comum.conexao;

import java.io.Serializable;
import java.util.Hashtable;

public class Requisicao<T extends Serializable> implements Serializable {
	
	public class Cabecalho implements Serializable{
		private Hashtable<String,String> cabecalho; 
		
		public Cabecalho(Hashtable<String, String> cabecalho) {
			this.cabecalho = cabecalho;
		}
		
		public void adicionar(String chave, String valor) {
			if(cabecalho.containsKey(chave)) {
				cabecalho.replace(chave, valor);
			} else {
				cabecalho.put(chave, valor);
			}
		}
		
		public String buscar(String chave) {
			return cabecalho.getOrDefault(chave, null);
		}
		
		public void remover(String chave, String valor) {
			if(cabecalho.containsKey(chave)) {
				cabecalho.remove(chave);
			}
		}
		
	}
	
	public static Hashtable<String, String> Cabecalho = new Hashtable<String,String>();
	public static String CHAVE_FECHAR_CONEXAO = "req_fecharConexao";
	
	private String hash;
	private String classe;
	private String acao;
	T dados;
	private Cabecalho cabecalho;
	

	public Requisicao(String classe, String acao, T dados) {
		this.classe = classe;
		this.acao = acao;
		this.dados = dados;
		cabecalho = new Cabecalho(Cabecalho);
	}

	public Requisicao(String rota, T dados) {
		String splitado[] = rota.split(" ");
		this.classe = splitado[0];
		this.acao = splitado[1];
		this.dados = dados;
		cabecalho = new Cabecalho(Cabecalho);
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
	
	public Cabecalho getCabecalho(){
		return cabecalho;
	}

	
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
	
}
