package br.com.fiap.posto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.com.fiap.posto.controller.ButtonController;
import br.com.fiap.posto.controller.TableController;
import br.com.fiap.posto.dao.PostoDao;
import br.com.fiap.posto.model.Posto;

public class Application {
	
	private DefaultTableModel tableModel;


	public static void main(String[] args) {
		new Application().init();
	}
	
	public void init() {
		
		//Criação do frame inicial
		JFrame frame = new JFrame("GasReg");
		
		//Criação das abas
		JTabbedPane abas = new JTabbedPane();
		
		abas.setForeground(new Color(250,63,147));
		abas.setBackground(Color.DARK_GRAY);
		abas.setFont(new Font("Century Gothic", Font.BOLD, 14));

		//Criação de paineis
		JPanel painel1 = new JPanel();
		JPanel painel2 = new JPanel();
		JPanel painel3 = new JPanel();
		JPanel mainPainel1 = new JPanel();
		JPanel mainPainel2 = new JPanel();
				
		//Criação de um vetor com nomes de colunas
		Vector<String> colunas = new Vector<String>();
		colunas.add("Id do posto");
		colunas.add("Nome");
		colunas.add("Endereço");
		colunas.add("Estado");
		colunas.add("Avaliação");
		colunas.add("Plugs disponíveis");
		colunas.add("Valor kWh");

		//Criação da tabela
		tableModel = new DefaultTableModel(colunas, 0);
		JTable table = new JTable(tableModel);
		
		TableController tableController = new TableController(this);
		table.addMouseListener(tableController);
		table.setDefaultEditor(Object.class, null);
		table.setForeground(new Color(250,63,147));
		table.setBackground(Color.DARK_GRAY);
		table.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		
		//Ordenação da coluna de estados
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		
		List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		sorter.setSortKeys(sortKeys);
		
		//Criação do painel na aba da lista
		JScrollPane scrollPane = new JScrollPane(table);
		

		//Botão de atualizar a lista
		JButton atualizar = new JButton("Atualizar");
		ButtonController listener = new ButtonController(this);
		atualizar.addActionListener(listener);
		
		//Criação do Label inicial
		JLabel cadastro = new JLabel("BEM-VINDO AO CADASTRO DE POSTOS");
		cadastro.setFont(new Font("Century Gothic", Font.BOLD, 24));
		cadastro.setForeground(new Color(31,244,154));
		cadastro.setHorizontalAlignment(JLabel.CENTER);
		
		//Labels da aba principal
		MainLabelFactory nome = new MainLabelFactory("Nome do Posto");
		MainLabelFactory endereco = new MainLabelFactory("Endereço");
		MainLabelFactory avaliacao = new MainLabelFactory("Avaliação");
		MainLabelFactory plug = new MainLabelFactory("Plugs Disponíveis");
		MainLabelFactory preco = new MainLabelFactory("Preço do kWh");
		
		LabelFactory rua = new LabelFactory("Rua:");
		LabelFactory bairro = new LabelFactory("Bairro:");
		LabelFactory cidade = new LabelFactory("Cidade: ");
		LabelFactory estado = new LabelFactory("Estado :");
		
		//Espaços de texto
		TextFieldFactory campo1 = new TextFieldFactory();
		TextFieldFactory campo2 = new TextFieldFactory();
		TextFieldFactory campo3 = new TextFieldFactory();
		TextFieldFactory campo4 = new TextFieldFactory();
		TextFieldFactory campo5 = new TextFieldFactory();
		TextFieldFactory campo6 = new TextFieldFactory();
		campo6.setText("R$");
		
		//Radio Buttons
		RadioButtonFactory tipo1 = new RadioButtonFactory("Tipo 1");
		RadioButtonFactory tipo2 = new RadioButtonFactory("Tipo 2");
		RadioButtonFactory css2 = new RadioButtonFactory("CSS2");
		RadioButtonFactory chademo = new RadioButtonFactory("CHAdeMO");
		
		//Criação dos botões de Savar ou cancelar
		JButton botao1 = new JButton("Salvar", new ImageIcon(getClass().getResource("/Images/saveIcon.png")));
		botao1.setBackground(Color.DARK_GRAY);
		botao1.setForeground(Color.WHITE);
		botao1.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		JButton botao2 = new JButton("Cancelar", new ImageIcon(getClass().getResource("/Images/cancelIcon.png")));
		botao2.setBackground(Color.DARK_GRAY);
		botao2.setForeground(Color.WHITE);
		botao2.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		//StarRater
		StarRater starRater = new StarRater(10);
		
		//Criação do ActionListener para o salvar
		ActionListener listenerSalvar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e){
				Posto posto = new Posto();
				
				//pegando estrelas 1 até 10
				int estrelas = starRater.getSelection();
				String estrelasStr = Integer.toString(estrelas);
				//pegando botões selecionados
				String selecao = "";
				if (tipo1.isSelected()==true){
					selecao = tipo1.getActionCommand();
				}
				if (tipo2.isSelected()==true){
					selecao = selecao+" "+tipo2.getActionCommand();
				}
				if (css2.isSelected()==true){
					selecao = selecao+" "+css2.getActionCommand();
				}
				if (chademo.isSelected()==true){
					selecao = selecao+" "+chademo.getActionCommand();
				}
				
				
				posto.setNome(campo1.getText());
				posto.setEndereco(campo2.getText()+", "+campo3.getText()+", "+campo4.getText());
				posto.setEstado(campo5.getText());
				posto.setAvaliacao(estrelasStr);
				posto.setPlugsDisponiveis(selecao);
				posto.setValorKiloWatHora(campo6.getText());

				PostoDao dao = new PostoDao();
				dao.cadastrar(posto);
				
				String enderecoGoogle = campo2.getText()+","+campo3.getText()+","+campo4.getText()+","+campo5.getText();
				enderecoGoogle = enderecoGoogle.replaceAll(" ", "");
				
				String google = "https://maps.googleapis.com/maps/api/staticmap?center="+enderecoGoogle+"&zoom=14&size=400x400&markers=color:yellow%7Clabel:P%7C"+enderecoGoogle+"&key=AIzaSyBmFnBeKU2jbmBsdXTed2EYk1ZBglusu7U";
				Image image = null;
		        try {
		            URL url = new URL(google);
		            image = ImageIO.read(url);
		        } catch (IOException f) {
		        	f.printStackTrace();
		        }
		        
		        JLabel imagem = new JLabel(new ImageIcon(image));
		        
		        //Manipulação do painel principal da aba do mapa gerado
		        JPanel component = new JPanel();
				abas.add(component, campo1.getText());
		        component.setLayout(new BorderLayout());
		        JLabel head = new JLabel("Mapa do posto "+campo1.getText());
		        head.setHorizontalAlignment(JLabel.CENTER);
		        head.setBackground(Color.DARK_GRAY);
		        head.setForeground(Color.WHITE);
		        head.setFont(new Font("Century Gothic", Font.BOLD, 14));
				component.add(head, BorderLayout.NORTH);
		        component.add(imagem, BorderLayout.CENTER);
		        component.setBackground(Color.BLACK);
		        JPanel component2 = new JPanel();
		        component.add(component2, BorderLayout.SOUTH);
		        //component2.setLayout(new GridLayout(4,1));
		        JLabel enderecoMapa = new JLabel("Endereço: "+campo2.getText()+", "+campo3.getText()+", "+campo4.getText()+" - "+campo5.getText()+" Preço do kWh: "+campo6.getText());
		        enderecoMapa.setForeground(Color.WHITE);
		        component2.add(enderecoMapa);
				component2.setBackground(Color.DARK_GRAY);
		        
				JOptionPane.showMessageDialog(null, posto.getNome() + " salvo! Cheque a localização na nova aba aberta!");
										
			}};
		
		botao1.addActionListener(listenerSalvar);       
		
		//Manipulação do painel 1
		painel1.setLayout(new GridLayout(12,1));
		painel1.setPreferredSize(new Dimension(250,400));
		painel1.setBackground(Color.DARK_GRAY);
		painel1.add(nome);
		painel1.add(campo1);
		painel1.add(endereco);
		painel1.add(rua);
		painel1.add(campo2);
		painel1.add(bairro);
		painel1.add(campo3);
		painel1.add(cidade);
		painel1.add(campo4);
		painel1.add(estado);
		painel1.add(campo5);
		
		//Manipulação do painel 2
		painel2.setLayout(new GridLayout(12,1));
		painel2.setPreferredSize(new Dimension(250,400));
		painel2.setBackground(Color.DARK_GRAY);
		painel2.add(avaliacao);
		painel2.add(starRater);
		painel2.add(plug);
		painel2.add(tipo1);
		painel2.add(tipo2);
		painel2.add(css2);
		painel2.add(chademo);
		painel2.add(preco);
		painel2.add(campo6);
		
		//Manipulação do painel 3
		painel3.setLayout(new FlowLayout());
		painel3.setBackground(Color.DARK_GRAY);
		painel3.add(botao1);
		painel3.add(botao2);
		
		//Manipulação do frame principal
		frame.add(abas);
		frame.setBackground(Color.DARK_GRAY);
		
		//Manipulação do painel principal da aba do cadastro
		mainPainel1.setLayout(new BorderLayout());
		mainPainel1.setBackground(Color.DARK_GRAY);
		mainPainel1.setBorder(new LineBorder(new Color(0,0,0)));
		mainPainel1.add(cadastro, BorderLayout.PAGE_START);
		mainPainel1.add(painel1, BorderLayout.LINE_START);
		mainPainel1.add(painel2, BorderLayout.LINE_END);
		mainPainel1.add(painel3, BorderLayout.PAGE_END);
		
		//Manipulação do painel principal da aba da lista
		mainPainel2.setLayout(new BorderLayout());
		mainPainel2.add(scrollPane, BorderLayout.CENTER);
		mainPainel2.add(atualizar, BorderLayout.PAGE_END);
		
		//Manipulação das abas
		abas.add(mainPainel1, "Cadastro de Postos");
		abas.add(mainPainel2, "Lista de Postos");
		
		//Manipulação do frame principal
		ImageIcon imagemTituloJanela = new ImageIcon("src/main/java/Images/gasIcon.png"); 
		frame.setIconImage(imagemTituloJanela.getImage());
		frame.setSize(650,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void carregarDados() {
		tableModel.setRowCount(0);
		PostoDao dao = new PostoDao();
		List<Posto> lista = dao.listarTodos();
		lista.forEach(posto -> tableModel.addRow(posto.getData()));
	}

}
