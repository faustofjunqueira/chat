package chat.comum.conexao;

import java.io.Serializable;

public class Resposta implements Serializable {

	public String msg;
	
	public Resposta(String msg) {
		this.msg = msg;
	}

}
