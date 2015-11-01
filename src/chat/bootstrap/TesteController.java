package chat.bootstrap;

import chat.comum.conexao.Requisicao;
import chat.comum.conexao.servidor.ServidorContainer;
import chat.comum.conexao.servidor.ServidorManipuladorRequisicao;

public class TesteController {

	public TesteController() {
	}
	
	public Teste Junda(Requisicao<Teste> req){
		Teste t = req.getDados();
		t.msg = t.msg.toUpperCase();
		
		return t;
	}

}
