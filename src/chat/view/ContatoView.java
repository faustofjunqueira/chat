package chat.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ContatoView {

	private JFrame frame;
	private JPanel painelLista;
	private JPanel painelConversas;
	private ClienteController controller;
	private List<JUsuarioCheckbox> checkboxUsuarios;
	
	public ContatoView(String nome, ClienteController controller) {
		this.controller = controller;
		frame = new JFrame(nome);
		painelLista = new JPanel();
		painelConversas = new JPanel();
	}

	public void renderizarSalas(List<Sala> listaDeSala){
		painelConversas.setVisible(false);
		painelConversas.removeAll();
		
		// check boxes:
		//List<JCheckBox> caixas = new ArrayList<JCheckBox>();
		List<JSalaButton> salasButton = new ArrayList<>();

		// layout
		GridLayout layout = new GridLayout(listaDeSala.size(),2);
		layout.setHgap(5);
		layout.setVgap(5);

		// painel
		painelConversas.setLayout(layout);

		// situacao do for
		for(Sala s: listaDeSala){
			JSalaButton salabtn = new JSalaButton(s,controller);
			salasButton.add(salabtn);
			painelConversas.add(salabtn);
		}
		painelConversas.setVisible(true);
	}
	
	public void setTituloPeloUsuario() {
		frame.setTitle(frame.getTitle() + " - " + controller.getUsuario().getNome());
	}
	
	public void renderizarContatos(List<Usuario> listaDeUsuario){
		painelLista.setVisible(false);
		painelLista.removeAll();
		// check boxes:
		checkboxUsuarios = new ArrayList<JUsuarioCheckbox>(listaDeUsuario.size());

		// layout
		GridLayout layout = new GridLayout(listaDeUsuario.size(),2);
		layout.setHgap(5);
		layout.setVgap(5);

		// painel
		painelLista.setLayout(layout);

		// situacao do for
		for(Usuario u : listaDeUsuario){
			// fiz a checkbox e associei ela ao nome do calango.
			JUsuarioCheckbox check = new JUsuarioCheckbox(u); 
			checkboxUsuarios.add(check);
			// add os calangos ao painel
			painelLista.add(check);
			painelLista.add(new JLabel(check.getUsuario().getNome()));
		}
		painelLista.setVisible(true);
	}

	public void render()
	{
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

		// painel de conversas
		// botao de continuar a conversa
		JButton botao2 = new JButton("Voltar a Conversa");
		botao2.addActionListener((e) -> {
			//TODO: fazer
			throw new NotImplementedException();
		});
		
		JPanel listas2 = new JPanel();
		listas2.setLayout(new BoxLayout(listas2, BoxLayout.PAGE_AXIS));
		listas2.setPreferredSize(new Dimension(400, 500));
		
		//JPanel painelConversas = geraLista();

		listas2.add(new JLabel("Lista de Conversas"));
		listas2.add(painelConversas);
		listas2.add(botao2);


		// painel final
		JPanel painelF = new JPanel();
		painelF.setLayout(new BoxLayout(painelF, BoxLayout.X_AXIS));

		painelF.add(listas);
		painelF.add(listas2);

		JScrollPane scroll2 = new JScrollPane(painelF, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll2.setPreferredSize(new Dimension(400, 350));

		// coisas da janela
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(scroll2);
		frame.pack();
		frame.setSize(800,500);
		frame.setVisible(true);
	}
	
}
