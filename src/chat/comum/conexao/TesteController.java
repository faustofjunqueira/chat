package chat.comum.conexao;

public class TesteController {

	public TesteController() {}
	
	public TesteDado junda(Requisicao<TesteDado> request) {
		TesteDado t = request.getDados();
		System.out.println(t.msg);
		t.msg = "segura resposta";
		return t;
	}
	
	public TesteDado junda2(Requisicao<TesteDado> request) {
		TesteDado t = request.getDados();
		System.out.println(t.msg.toUpperCase());
		t.msg = "toma essa";
		return t;
	}

}
