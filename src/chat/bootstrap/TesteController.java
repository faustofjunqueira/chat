package chat.bootstrap;

import chat.comum.conexao.Requisicao;

public class TesteController {

	public TesteController() {
	}
	
	public Teste Junda(Requisicao<Teste> req){
		Teste t = req.getDados();
		t.msg = t.msg.toUpperCase();
		return t;
	}

}
