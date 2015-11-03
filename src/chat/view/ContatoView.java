package chat.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import chat.dominio.entidade.Sala;
import chat.dominio.entidade.Usuario;

public class ContatoView {

	private JFrame frame;
	private JPanel painelLista;
	private JPanel painelConversas;
	private ClienteController controller;

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
		List<JCheckBox> caixas = new ArrayList<JCheckBox>();

		// layout
		GridLayout layout = new GridLayout(listaDeSala.size(),2);
		layout.setHgap(5);
		layout.setVgap(5);

		// painel
		painelConversas.setLayout(layout);

		// situacao do for
		int a = listaDeSala.size();
		for(int i = 0; i<a; i++)
		{
			// fiz a checkbox e associei ela ao nome do calango.
			caixas.add(new JCheckBox());

			// add os calangos ao painel
			painelConversas.add(caixas.get(i));
			painelConversas.add(new JLabel(listaDeSala.get(i).getNome()));
		}
		painelConversas.setVisible(true);
	}
	
	public void renderizarContatos(List<Usuario> listaDeUsuario){
		painelLista.setVisible(false);
		painelLista.removeAll();
		// check boxes:
		List<JCheckBox> caixas = new ArrayList<JCheckBox>();

		// layout
		GridLayout layout = new GridLayout(listaDeUsuario.size(),2);
		layout.setHgap(5);
		layout.setVgap(5);

		// painel
		
		painelLista.setLayout(layout);

		// situacao do for
		int a = listaDeUsuario.size();
		for(int i = 0; i<a; i++)
		{
			// fiz a checkbox e associei ela ao nome do calango.
			caixas.add(new JCheckBox());
			// add os calangos ao painel
			painelLista.add(caixas.get(i));
			painelLista.add(new JLabel(listaDeUsuario.get(i).getNome()));
		}
		painelLista.setVisible(true);
	}

	public void render()
	{
		// painel da lista de contatos
		// Botao de entrar:
		JButton botao1 = new JButton("Entrar em Contato");
		//botao1.addActionListener(entrarConversa);

		JPanel listas = new JPanel();
		listas.setPreferredSize(new Dimension(400, 500));
		listas.setLayout(new BoxLayout(listas, BoxLayout.PAGE_AXIS));

		listas.add(new JLabel("Lista de Contatos OnLine"));
		listas.add(painelLista);
		listas.add(botao1);

		// painel de conversas
		// botao de continuar a conversa
		JButton botao2 = new JButton("Voltar a Conversa");
		//botao2.addActionListener(continuarConversa);
		
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
