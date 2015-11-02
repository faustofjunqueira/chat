package chat.view;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteViewer{

	/* Pensamentos sobre como colocar o output das conversas na tela.
	 * 
	 * Ha duas opcoes:
	 * 1) O conteudo das conversas esta sendo guardado por outros objetos em outros locais e td vez que for necessario
	 *    ou qnd chegar uma msg nova, o texto existente eh substituido pelo novo. Q eh exatamente igual, acrescido da
	 *    ultima mensagem.
	 *    
	 * 2) O conteudo das conversas nao eh gravado em local nenhum, ele existe soh na tela. Nesse caso, é preciso, td vez
	 *    que uma msg nova chegar, transforma-la em string (caso ainda n seja) + add essa string a string que esta sendo 
	 *    printada na tela + retirar a string que esta na tela atualmente + colocar a juncao das duas.
	 * 
	 *  #anotaenaoesquece #biacriativa
	 */


	// janela ok, feliz tbm.
	private static void salaChat() {

		// coisas da area de exibicao da conversa
		JPanel conversa = new JPanel();

		JTextArea conversa1 = new JTextArea("");
		conversa1.setEditable(false);
		conversa1.setLineWrap(true);

		JScrollPane scroll = new JScrollPane(conversa1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(680, 430));

		conversa.add(scroll);

		// coisas da digitacao do conteudo
		JPanel conteudo = new JPanel();

		JTextArea conteudo1 = new JTextArea("");
		conteudo1.setLineWrap(true);

		JScrollPane scroll2 = new JScrollPane(conteudo1, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setPreferredSize(new Dimension(680, 50));

		JLabel conteudo2 = new JLabel("Digite seu texto:");

		conteudo.add(scroll2);

		// botao
		JButton botao1 = new JButton("Enviar");
		JPanel botao = new JPanel();

		botao.add(botao1);
		botao1.addActionListener(enviar);


		// painel final
		JPanel painelF = new JPanel();

		painelF.add(conversa);
		painelF.add(conteudo2);
		painelF.add(conteudo);
		painelF.add(botao);

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Chat 1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(painelF);
		frame.pack();
		frame.setSize(700,600);
		frame.setVisible(true);



	}

	// janela ok, feliz
	private static void inicio()
	{
		// nick
		JLabel nick4 = new JLabel("Bem Vindo ao Chat !");
		JLabel nick3 = new JLabel("Digite seu Nick:");

		JTextArea nick2 = new JTextArea(1, 20);
		nick2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JPanel nick = new JPanel();

		nick.add(nick3);
		nick.add(nick2);

		nick2.setVisible(true);
		nick.setVisible(true);
		nick4.setVisible(true);

		// botao
		JButton botao1 = new JButton("Entrar");
		JPanel botao = new JPanel();

		botao.add(botao1);
		botao1.addActionListener(entrarChat);

		// painel final

		JPanel painelF = new JPanel();

		painelF.add(nick4);
		painelF.add(nick);
		painelF.add(botao);


		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);

		JFrame frame = new JFrame("Trabalho Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(painelF);
		frame.pack();
		frame.setSize(400,150);
		frame.setVisible(true);
	}

	private static JPanel geraLista()
	{
		// lista e suas coisas
		List<String> lista = new ArrayList<String>();

		lista.add("Maria do ceu");
		lista.add("Joao bobo");
		lista.add("Juracema s2 Paulinho");
		lista.add("Bia");
		lista.add("Fausto");
		lista.add("Mainha");
		lista.add("Mário");
		lista.add("Zezim");

		// check boxes:
		List<JCheckBox> caixas = new ArrayList<JCheckBox>();

		// layout
		GridLayout layout = new GridLayout(lista.size(),2);
		layout.setHgap(5);
		layout.setVgap(5);

		// painel
		JPanel painel = new JPanel();
		painel.setLayout(layout);

		// situacao do for
		int a = lista.size();
		for(int i = 0; i<a; i++)
		{

			// fiz a checkbox e associei ela ao nome do calango.
			caixas.add(new JCheckBox());

			// add os calangos ao painel
			painel.add(caixas.get(i));
			painel.add(new JLabel(lista.get(i)));

		}


		return painel;
	}

	private static void contatos()
	{
		// Layout da Pagina principal:
		//		BoxLayout layout = new BoxLayout(BoxLayout.Y_AXIS);






		// painel da lista de contatos
		// Botao de entrar:
		JButton botao1 = new JButton("Entrar em Contato");
		botao1.addActionListener(entrarConversa);

		JPanel listas = new JPanel();
		listas.setLayout(new BoxLayout(listas, BoxLayout.PAGE_AXIS));

		JPanel painelLista = geraLista();

		listas.add(new JLabel("Lista de Contatos OnLine"));
		listas.add(painelLista);
		listas.add(botao1);

		// painel de conversas
		// botao de continuar a conversa
		JButton botao2 = new JButton("Voltar a Conversa");
		botao2.addActionListener(continuarConversa);
		JPanel painelConversas = geraLista();



		// painel final
		JPanel painelF = new JPanel();
		painelF.setLayout(new BoxLayout(painelF, BoxLayout.X_AXIS));
		painelF.setSize(400, 350);

		//		painelF.add(new JLabel("Lista de Contatos OnLine"));
		painelF.add(listas);
		//		painelF.add(botao1);
		//		painelF.add(new JLabel("Conversas Ativas:"));
		//		painelF.add(painelConversas);
		//		painelF.add(botao2);

		JScrollPane scroll2 = new JScrollPane(painelF, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setPreferredSize(new Dimension(400, 350));


		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Trabalho Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(scroll2);
		frame.pack();
		frame.setSize(400,700);
		frame.setVisible(true);
	}

	// ################################################ listeners dos botoes!

	// listener para entrar no chat
	private static ActionListener entrarChat = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("OK");
		}

	};

	// listener para criar conversa
	private static	ActionListener entrarConversa = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("OK");
		}
	};

	// listener para entrar numa conversa existente
	private static ActionListener continuarConversa = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("OK");
		}

	};

	// listener para enviar mensagem
	private static ActionListener enviar = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("OK");
		}

	};


	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				contatos();
			}
		});
	}



}
