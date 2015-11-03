package chat.view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ChatView {

	private JFrame frame;
	private ClienteController controller;
	
	public ChatView(String nome, ClienteController controller) {
		this.controller = controller;
		frame = new JFrame(nome);
	}

	private void render() {

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
		//botao1.addActionListener(enviar);


		// painel final
		JPanel painelF = new JPanel();

		painelF.add(conversa);
		painelF.add(conteudo2);
		painelF.add(conteudo);
		painelF.add(botao);

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(painelF);
		frame.pack();
		frame.setSize(700,600);
		frame.setVisible(true);

	}
	
}
