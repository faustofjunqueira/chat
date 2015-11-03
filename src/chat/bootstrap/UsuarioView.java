package chat.bootstrap;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import chat.view.ClienteController;
import chat.view.JUsuarioCheckbox;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class UsuarioView {

	private JFrame frame;
	private ClienteController controller;
	private JPanel painelLista;
	private List<JUsuarioCheckbox> checkboxUsuarios;

	public UsuarioView(String titulo, ClienteController controller) {
		frame = new JFrame(titulo);
		painelLista = new JPanel();
		this.controller = controller; 
	}

	private void render(Sala sala) {

		// painel da lista de contatos
		// Botao de entrar:
		JButton botao1 = new JButton("Entrar em Contato");
		botao1.addActionListener((e) -> {
			List<Usuario> usuarios = new ArrayList<>();
			for(JUsuarioCheckbox checkbox : checkboxUsuarios){
				if(checkbox.isSelected()){
					usuarios.add(checkbox.getUsuario());
				}
			}
			controller.criarSala(usuarios);
			//controller.renderChat();
		});

		JPanel listas = new JPanel();
		listas.setPreferredSize(new Dimension(400, 500));
		listas.setLayout(new BoxLayout(listas, BoxLayout.PAGE_AXIS));

		listas.add(new JLabel("Lista de Contatos OnLine"));
		listas.add(painelLista);
		listas.add(botao1);
		
		// painel final
		JPanel painelF = new JPanel();
		painelF.setLayout(new BoxLayout(painelF, BoxLayout.X_AXIS));

		painelF.add(listas);
		
		JScrollPane scroll2 = new JScrollPane(painelF, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setPreferredSize(new Dimension(400, 350));

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.add(scroll2);
		frame.pack();
		frame.setSize(800,500);
		frame.setVisible(true);
	}
}
