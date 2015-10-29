package chat.comum.conexao;

import java.io.Serializable;

public class Requisicao implements Serializable {

	public String msg;
	
	public Requisicao(String msg){
		this.msg = msg;
	}

}
