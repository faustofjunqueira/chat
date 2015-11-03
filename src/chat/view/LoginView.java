package chat.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class LoginView {

	private JFrame frame;
	private ClienteController controller;
	private JTextArea loginTextArea;
	
	public LoginView(String nome, ClienteController controller) {
		this.controller = controller;
		frame = new JFrame(nome);
		loginTextArea = new JTextArea(1,20);
	}

	public void render()
	{
		// nick
		JLabel nick4 = new JLabel("Bem Vindo ao Chat !");
		JLabel nick3 = new JLabel("Digite seu Nick:");

		loginTextArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JPanel nick = new JPanel();

		nick.add(nick3);
		nick.add(loginTextArea);

		loginTextArea.setVisible(true);
		nick.setVisible(true);
		nick4.setVisible(true);

		// botao
		JButton botao1 = new JButton("Entrar");
		JPanel botao = new JPanel();

		botao.add(botao1);
		botao1.addActionListener((e) -> {
			controller.logar(loginTextArea.getText());
			frame.setVisible(false);
			controller.renderContato();
		});

		// painel final

		JPanel painelF = new JPanel();

		painelF.add(nick4);
		painelF.add(nick);
		painelF.add(botao);

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(painelF);
		frame.pack();
		frame.setSize(400,150);
		frame.setVisible(true);
	}
	
}
