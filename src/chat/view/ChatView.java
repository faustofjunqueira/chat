package chat.view;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.sun.glass.events.WindowEvent;

import chat.dominio.entidade.Mensagem;
import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;

public class ChatView {

	private JFrame frame;
	private ClienteController controller;
	private Sala sala;
	private JTextArea areaConversa;
	private JTextArea areaTexto;

	public ChatView(ClienteController controller, Sala sala) {
		this.controller = controller;
		frame = new JFrame("BF Chat - " + sala.getNome());
		this.sala = sala;
		this.areaConversa = new JTextArea("");
	}

	private String montaFalaChat(Mensagem mensagem) {
		String texto = String.format("[%s] %s: %s\n", mensagem.getHoraStr(), mensagem.getUsuario().getNome(),
				mensagem.getTexto());
		return texto;
	}

	public void renderMensagem(Mensagem mensagem) {
		areaConversa.append(montaFalaChat(mensagem));
	}

	private void enviarMensagem(String texto) {
		Mensagem mensagem = new Mensagem(texto, controller.getUsuario(), sala);
		controller.enviaMensagem(mensagem);
	}

	public Sala getSala() {
		return sala;
	}

	private String listaUsuarios() {
		String str = "";

		for (Usuario u : getSala().getUsuarios()) {
			str += u.getNome() + " ";
		}

		return str;
	}

	public void render(List<Mensagem> listaMensagens) {

		// coisas da area de exibicao dos participantes da conversa
		String usuarios = listaUsuarios();
		JLabel listaUsuarios = new JLabel(usuarios);

		// coisas da area de exibicao da conversa
		JPanel conversa = new JPanel();
		conversa.setLayout(new BoxLayout(conversa, BoxLayout.Y_AXIS));

		areaConversa.setEditable(false);
		areaConversa.setLineWrap(true);

		JScrollPane scroll = new JScrollPane(areaConversa, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setPreferredSize(new Dimension(680, 430));

		conversa.add(listaUsuarios);
		conversa.add(scroll);

		// coisas da digitacao do conteudo
		JPanel conteudo = new JPanel();

		areaTexto = new JTextArea("");
		areaTexto.setLineWrap(true);

		JScrollPane scroll2 = new JScrollPane(areaTexto, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setPreferredSize(new Dimension(680, 50));

		JLabel conteudo2 = new JLabel("Digite seu texto:");

		conteudo.add(scroll2);

		// botao de sair
		JButton botao2 = new JButton("Sair da Conversa");
		botao2.addActionListener((e) -> {
			controller.SairDaSala(getSala(), controller.getUsuario());
		});
		
		JButton botao3 = new JButton("Adicionar amigos");
		botao3.addActionListener(null);
		// botao de enviar
		JButton botao1 = new JButton("Enviar");

		botao1.addActionListener((e) -> {
			String texto = areaTexto.getText();
			if (texto.length() > 0) {
				enviarMensagem(texto);
				areaTexto.setText("");
			}
		});

		JPanel botao = new JPanel();
		botao.add(botao1);
		botao.add(botao2);
		botao.add(botao3);

		// painel final
		JPanel painelF = new JPanel();

		painelF.add(conversa);
		painelF.add(conteudo2);
		painelF.add(conteudo);
		painelF.add(botao);

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// aqui vc cria as condicoes do bichinho ao sair
				System.out.println("Saindo");
			}
		});
		frame.setResizable(false);
		frame.add(painelF);
		frame.pack();
		frame.setSize(700, 600);

		if (listaMensagens != null && listaMensagens.size() > 0) {
			for (Mensagem mensagem : listaMensagens) {
				renderMensagem(mensagem);
			}
		}

		frame.setVisible(true);

	}
}
