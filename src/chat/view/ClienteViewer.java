package chat.view;

import javax.swing.*;
import java.util.*;
import java.util.List;

import java.awt.*;

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

	private static void contatos(List<String> lista)
	{

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Trabalho Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
//		frame.add(painelF);
		frame.pack();
		frame.setSize(400,150);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				inicio();
				//salaChat();
			}
		});
	}



}
