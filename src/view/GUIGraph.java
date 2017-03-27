package view;

import javax.swing.*;

import model.Graph;
import readerwriter.GraphReader;
import readerwriter.GraphWriter;
import controller.*;
import handlers.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Esta eh a janela principal do programa, onde estao as principais acoes. 
 * Como criar um novo grafo, inserir vertices e arestas, gerar e muitas outras (Listeners).
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */

public final class GUIGraph extends GUI {

	private static final long serialVersionUID = 1L;
	//private GUI frameDeControle;
	private Graph G = new Graph(true);
	private GControl gc = new GControl(this, G);
	private final GraphPane dropPane = new GraphPane(G);
	
	/**
	 * Construtor da classe GUIGraph
	 */
	
	public GUIGraph(){

		super("MapReduceInterface");
		iniciar();
	}

	/**
	 * Inicia os objetos como: paineis, menus, menuitens, barras de menu, etc.
	 */
	
	public void iniciar(){

		// Icone da GUIGraph(frame)
		Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("images/graph.png"));  
		this.setIconImage(imagemTitulo);

		// Instanciar e inserir icones dos JMenus
		JMenu menuArquivo = new JMenu("Arquivo");
		ImageIcon arq = new ImageIcon(this.getClass().getResource("images/arquivo/folder.png"));
		menuArquivo.setIcon(arq);
		JMenu menuEditar = new JMenu("Editar");
		ImageIcon edit = new ImageIcon(this.getClass().getResource("images/editar/application_edit.png"));
		menuEditar.setIcon(edit);
		JMenu menuInserir = new JMenu("Inserir");
		ImageIcon ins = new ImageIcon(this.getClass().getResource("images/editar/application_add.png"));
		menuInserir.setIcon(ins);
		JMenu menuRemover = new JMenu("Remover");
		ImageIcon rem = new ImageIcon(this.getClass().getResource("images/editar/application_delete.png"));
		menuRemover.setIcon(rem);
		JMenu menuExecutar = new JMenu("Executar");
		ImageIcon exc = new ImageIcon(this.getClass().getResource("images/executar/application.png"));
		menuExecutar.setIcon(exc);
		JMenu menuInfo = new JMenu("Informacoes");
		ImageIcon info = new ImageIcon(this.getClass().getResource("images/informacoes/information.png"));
		menuInfo.setIcon(info);
		JMenu menuAjuda = new JMenu("Ajuda");
		ImageIcon aju = new ImageIcon(this.getClass().getResource("images/ajuda/help.png"));
		menuAjuda.setIcon(aju);

		// Instanciar e inserir icones dos JMenuItens e JCheckBoxMenuItem
		ImageIcon novo = new ImageIcon(this.getClass().getResource("images/arquivo/new.png"));
		JMenuItem itemNovo = new JMenuItem("Novo", novo);

		ImageIcon abrir = new ImageIcon(this.getClass().getResource("images/arquivo/folder_explore.png"));
		JMenuItem itemAbrir = new JMenuItem("Abrir", abrir);

		ImageIcon salvar = new ImageIcon(this.getClass().getResource("images/arquivo/page_save.png"));
		JMenuItem itemSalvar = new JMenuItem("Salvar", salvar);

		ImageIcon sair = new ImageIcon(this.getClass().getResource("images/arquivo/cross.png"));
		JMenuItem itemSair = new JMenuItem("Sair", sair);
		final JCheckBoxMenuItem itemDirecionado = new JCheckBoxMenuItem("Direcionado");

		ImageIcon iAjustar = new ImageIcon(this.getClass().getResource("images/editar/application_edit.png"));
		JMenuItem itemAjustar = new JMenuItem("Ajustar Componente", iAjustar);

		ImageIcon iAgrupar = new ImageIcon(this.getClass().getResource("images/editar/application_edit.png"));
		JMenuItem itemAgrupar = new JMenuItem("Agrupar Componentes", iAgrupar);

		ImageIcon vi = new ImageIcon(this.getClass().getResource("images/editar/add.png"));
		JMenuItem itemVerticeI = new JMenuItem("Componente", vi);

		ImageIcon ai = new ImageIcon(this.getClass().getResource("images/editar/add.png"));
		JMenuItem itemArestaI = new JMenuItem("Ligacao", ai);

		ImageIcon vr = new ImageIcon(this.getClass().getResource("images/editar/delete.png"));
		JMenuItem itemVerticeR = new JMenuItem("Componente", vr);

		ImageIcon ar = new ImageIcon(this.getClass().getResource("images/editar/delete.png"));
		JMenuItem itemArestaR = new JMenuItem("Ligacao", ar);	

		ImageIcon reset = new ImageIcon(this.getClass().getResource("images/executar/application_go.png"));
		JMenuItem itemResetar = new JMenuItem("Resetar(Estado Original)", reset);

		ImageIcon gale = new ImageIcon(this.getClass().getResource("images/executar/script_go.png"));
		JMenuItem itemGerarAle = new JMenuItem("Gerar Aleatorio", gale);

		ImageIcon bl = new ImageIcon(this.getClass().getResource("images/executar/zoom.png"));
		JMenuItem itemBuscaL = new JMenuItem("Busca em Largura", bl);

		ImageIcon bp = new ImageIcon(this.getClass().getResource("images/executar/zoom.png"));
		JMenuItem itemBuscaP = new JMenuItem("Busca em Profundidade", bp);

		//JMenuItem itemBF = new JMenuItem("Busca em Profundidade", bp);

		ImageIcon geral = new ImageIcon(this.getClass().getResource("images/informacoes/information.png"));
		JMenuItem itemGeral = new JMenuItem("Geral", geral);

		ImageIcon vesp = new ImageIcon(this.getClass().getResource("images/informacoes/zoom.png"));
		JMenuItem itemVEsp = new JMenuItem("Componente (Especifico)", vesp);

		ImageIcon aesp = new ImageIcon(this.getClass().getResource("images/informacoes/zoom.png"));
		JMenuItem itemAEsp = new JMenuItem("Ligacao (Especifica)", aesp);

		ImageIcon icv = new ImageIcon(this.getClass().getResource("images/informacoes/information.png"));
		JMenuItem itemCV = new JMenuItem("Conjunto de Componentes", icv);

		ImageIcon ica = new ImageIcon(this.getClass().getResource("images/informacoes/information.png"));
		JMenuItem itemCA = new JMenuItem("Conjunto de Ligacoes", ica);

		ImageIcon vatual = new ImageIcon(this.getClass().getResource("images/ajuda/world.png"));
		JMenuItem itemVAtual = new JMenuItem("Verificar atualizacoes", vatual);

		ImageIcon sobre = new ImageIcon(this.getClass().getResource("images/ajuda/information.png"));
		JMenuItem itemSobre = new JMenuItem("Sobre", sobre);

		// Instanciar JMenuBar
		JMenuBar menuBar = new JMenuBar();

		// Barra de ferramentas
		JToolBar toolBar = new JToolBar("Barra de Ferramentas");
		JLabel vert = new JLabel("Componente:");
		JLabel are = new JLabel("Ligacao:");

		// Botao adicionar vertice
		JButton addV = new JButton();
		addV.setIcon(vi);
		addV.setOpaque(false);
		addV.addActionListener(new HandlerAddVertice());

		// Botao remover vertice
		JButton remV = new JButton();
		remV.setIcon(vr);
		remV.setOpaque(false);
		remV.addActionListener(new HandlerRemVertice());

		// Botao procurar vertice
		JButton proV = new JButton();
		proV.setIcon(vesp);
		proV.setOpaque(false);
		proV.addActionListener(new HandlerVerticeEsp());

		// Botao adicionar aresta
		JButton addA = new JButton();
		addA.setIcon(ai);
		addA.setOpaque(false);
		addA.addActionListener(new HandlerAddAresta());

		// Botao remover aresta
		JButton remA = new JButton();
		remA.setIcon(ar);
		remA.setOpaque(false);
		remA.addActionListener(new HandlerRemAresta());

		// Botao procurar aresta
		JButton proA = new JButton();
		proA.setIcon(aesp);
		proA.setOpaque(false);
		proA.addActionListener(new HandlerArestaEsp());

		toolBar.add(vert);
		toolBar.add(addV);
		toolBar.add(remV);
		toolBar.add(proV);
		toolBar.addSeparator();
		toolBar.add(are);
		toolBar.add(addA);
		toolBar.add(remA);
		toolBar.add(proA);

		// Secao de adicionar aos Menus: MenuItens ou outros Menus. Tambem tratamos eventos aqui.
		// Menu Arquivo
		//menuArquivo.setMnemonic(KeyEvent.VK_W);

		// Novo
		itemNovo.addActionListener(new HandlerNovo());
		menuArquivo.add(itemNovo);

		// Abrir
		itemAbrir.addActionListener(new HandlerAbrir());
		menuArquivo.add(itemAbrir);

		// Salvar
		itemSalvar.addActionListener(new HandlerSalvar());
		menuArquivo.add(itemSalvar);

		// Sair
		//itemSair.setMnemonic(KeyEvent.VK_C);
		itemSair.addActionListener(new HandlerSair());
		menuArquivo.add(itemSair);

		// Direcionado
		//itemDirecionado.setMnemonic(KeyEvent.VK_C);
		itemDirecionado.setSelected(true);
		itemDirecionado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(itemDirecionado.isSelected()){
					gc.addDir(true);
					JOptionPane.showMessageDialog(null,"O Graph agora é direcionado.","Direcao do Graph",JOptionPane.INFORMATION_MESSAGE);
				}
				else{
					gc.addDir(false);
					JOptionPane.showMessageDialog(null,"O Graph agora não é direcionado.","Direcaoo do Graph",JOptionPane.INFORMATION_MESSAGE);
				}
				repaint();
			}
		});

		// Ajustar
		itemAjustar.addActionListener(new HandlerAjustar());

		// Agrupar
		itemAgrupar.addActionListener(new HandlerAgrupar());

		// Inserir Vertex
		//itemVerticeI.setMnemonic(KeyEvent.VK_C);
		itemVerticeI.addActionListener(new HandlerAddVertice());
		menuInserir.add(itemVerticeI);

		// Inserir Edge
		itemArestaI.addActionListener(new HandlerAddAresta());

		menuInserir.add(itemArestaI);

		// Remover Vertex
		itemVerticeR.setMnemonic(KeyEvent.VK_W);
		itemVerticeR.addActionListener(new HandlerRemVertice());

		menuRemover.add(itemVerticeR);

		// Remover Edge
		//itemArestaR.setMnemonic(KeyEvent.VK_W);
		itemArestaR.addActionListener(new HandlerRemAresta());

		menuRemover.add(itemArestaR);

		//menuEditar.setMnemonic(KeyEvent.VK_W);
		menuEditar.add(itemDirecionado);
		menuEditar.addSeparator();
		menuEditar.add(itemAjustar);
		menuEditar.add(itemAgrupar);
		menuEditar.addSeparator();
		menuEditar.add(menuInserir);
		menuEditar.add(menuRemover);

		// Menu Executar
		//menuExecutar.setMnemonic(KeyEvent.VK_W);

		itemResetar.addActionListener(new HandlerResetar());
		menuExecutar.add(itemResetar);

		// Gerar Aleatorio
		itemGerarAle.addActionListener(new HandlerGerarAleatorio());
		menuExecutar.add(itemGerarAle);
		menuExecutar.addSeparator();

		// Busca em Largura
		itemBuscaL.addActionListener(new HandlerBuscaEmLargura());
		menuExecutar.add(itemBuscaL);

		// Busca em Profundidade
		itemBuscaP.addActionListener(new HandlerBuscaEmProfundidade());
		menuExecutar.add(itemBuscaP);

		// Menu Geral
		//itemGeral.setMnemonic(KeyEvent.VK_C);
		itemGeral.addActionListener(new HandlerGeral());
		menuInfo.add(itemGeral);

		menuInfo.addSeparator();

		// Vertex Especifico
		//itemVEsp.setMnemonic(KeyEvent.VK_C);
		itemVEsp.addActionListener(new HandlerVerticeEsp());
		menuInfo.add(itemVEsp);

		// Edge Especifica
		//itemAEsp.setMnemonic(KeyEvent.VK_C);
		itemAEsp.addActionListener(new HandlerAddAresta());
		menuInfo.add(itemAEsp);
		menuInfo.addSeparator();

		// Conjunto de Vertices
		//itemCV.setMnemonic(KeyEvent.VK_C);
		itemCV.addActionListener(new HandlerCV());
		menuInfo.add(itemCV);

		// Conjunto de Arestas
		//itemCA.setMnemonic(KeyEvent.VK_C);
		itemCA.addActionListener(new HandlerCA());
		menuInfo.add(itemCA);

		// Menu Ajuda
		//menuAjuda.setMnemonic(KeyEvent.VK_W);

		// Verificar Atualizacoes
		//itemVAtual.setMnemonic(KeyEvent.VK_C);
		itemVAtual.addActionListener(new HandlerVAtual());
		menuAjuda.add(itemVAtual);

		menuAjuda.addSeparator();

		// Sobre
		//itemSobre.setMnemonic(KeyEvent.VK_C);
		itemSobre.addActionListener(new HandlerSobre());
		menuAjuda.add(itemSobre);

		menuBar.add(menuArquivo);
		menuBar.add(menuEditar);
		menuBar.add(menuExecutar);
		menuBar.add(menuInfo);
		menuBar.add(menuAjuda);

		// Setar objetos e configurações
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Timer timer = dropPane.getTimer();
				timer.stop();
			}
		});
		setJMenuBar(menuBar);
		toolBar.setBackground(new Color(220,220,220));
		toolBar.setBorder(BorderFactory.createLineBorder(Color.gray, 1));
		
		JScrollPane dragPane = new JScrollPane();
		//dragPane.setTransferHandler(new FromTransferHandler());
		
        dragPane.setBorder(BorderFactory.createTitledBorder(
				"Arraste daqui"));
        
		dropPane.setBackground(new Color(220,220,220));
		dropPane.setBorder(BorderFactory.createTitledBorder(
				"Solte aqui"));
		dropPane.setToolTipText("teste");
		
		JPanel geralPane = new JPanel(new BorderLayout());
		
		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                dragPane, dropPane);
		pane.setOneTouchExpandable(true);
        pane.setDividerLocation(200);
        
		geralPane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		geralPane.add(toolBar, BorderLayout.PAGE_START);
		geralPane.add(pane, BorderLayout.CENTER);
		
		setContentPane(geralPane);
//		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
//		setResizable(true);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}
	
	/**
	 * Seta qual model.Graph ira ser usado pela classe
	 * @param G model.Graph
	 * @since 1.0 
	 */
	public void setGrafo(Graph G){
		this.G = G;
	}
	
	
	/**
	 * Classe privada que trata eventos do JMenuItem novo.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerNovo implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] message = {"Esta ação apagará todo o conteudo do Graph.\nDeseja salvá-lo antes de cotinuar?"};
			int option = JOptionPane.showOptionDialog(null, message, "Novo Graph", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não","Cancelar"}, "Sim");
			if(option == JOptionPane.YES_OPTION){
				(new GraphWriter(G)).iniciar();
				setGrafo(new Graph());
				gc.setGrafo(G);
				dropPane.setGrafo(G);
				repaint();
			}
			if(option == JOptionPane.NO_OPTION){
				setGrafo(new Graph());
				gc.setGrafo(G);
				dropPane.setGrafo(G);
				repaint();
			}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Abrir.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerAbrir implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				GraphReader ag = new GraphReader();
				setGrafo(ag.iniciar());
				gc.setGrafo(G);
				dropPane.setGrafo(G);
				repaint();
			}
			catch(NullPointerException ex){}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Salvar.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerSalvar implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			(new GraphWriter(G)).iniciar();
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Sair.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerSair implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] message = {"Deseja fechar o programa?\nVoce tem certeza disso?"};
			int option = JOptionPane.showOptionDialog(null, message, "Sair", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Nao"}, "Nao");
			if(option == JOptionPane.YES_OPTION){
				setVisible(false);
				dispose();
			}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Agrupar.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerAgrupar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();

			Object[] message = {
					"Posicao X:", nome1,
					"Posicao Y:", nome2
			};		
			int option = JOptionPane.showConfirmDialog(null, message, "Agrupar Componentes", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try{
					gc.agrupar(Integer.parseInt(nome1.getText()), Integer.parseInt(nome2.getText()));
					repaint();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"As posicoes estao incorretas. Tente novamente com posicoes validas","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	/**
	 * Classe privada que trata eventos do JMenuItem Ajustar.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerAjustar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JTextField nome = new JTextField();
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();

			Object[] message = {
					"Nome:", nome,
					"Posicao X:", nome1,
					"Posicao Y:", nome2
			};		
			int option = JOptionPane.showConfirmDialog(null, message, "Ajustar Posição de Componente", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try{
					gc.ajustar(nome.getText(), Integer.parseInt(nome1.getText()), Integer.parseInt(nome2.getText()));
					repaint();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"As posicoes estao incorretas. Tente novamente com posicoes validas","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}
		}

	}

	/**
	 * Classe privada que trata eventos do JMenuItem AddVertice.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerAddVertice implements ActionListener{

		@Override
		/*public void actionPerformed(ActionEvent e) {

			String inputValue = JOptionPane.showInputDialog(null,"Entre com o nome do vertice:","Inserir Vertex", JOptionPane.QUESTION_MESSAGE);
			try{
				while(inputValue.equals(""))
					inputValue = JOptionPane.showInputDialog(null,"\nUm vertice nao pode ser nulo, certo?\n\nInsira (novamente) o nome do vertice:","Que feio! :/",JOptionPane.QUESTION_MESSAGE);
				int x=0 , y=0;
				gc.addVertice(inputValue, x, y);
				repaint();
			}
			catch(NullPointerException ex){}
		}*/
		

		public void actionPerformed(ActionEvent e) {
			JTextField nome = new JTextField();
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();
			String[] titles = new String[] {"Source", "Mapper", "Splitter",
                    "Shuffler", "Reducer", "Result"};

			JComboBox<String> types = new JComboBox<>(titles);
			String t = "0";
			Object[] message = {
					"Entre com o nome do componente:", nome,
					"Posicao X (Opcional):", nome1,
					"Posicao Y (Opcional):", nome2,
					"Tipo:", types
			};		

			
			int option = JOptionPane.showConfirmDialog(null, message, "Inserir Componentes", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try{
					String selectedType = (String) types.getSelectedItem();
					if(nome1.getText().equals("")||nome2.getText().equals("")){
						nome1.setText(t);
						nome2.setText(t);
					}
					while(nome.getText().equals("")){

						option = JOptionPane.showConfirmDialog(null, message,"Insira novamente! :/", JOptionPane.OK_CANCEL_OPTION);
						if(nome1.getText().equals("")||nome2.getText().equals("")){
							nome1.setText(t);
							nome2.setText(t);
						}
						if(option == JOptionPane.CANCEL_OPTION){
							break;
						}	
					}
					if(option == JOptionPane.OK_OPTION){
						gc.addVertice(nome.getText(),Integer.parseInt(nome1.getText()),Integer.parseInt(nome2.getText()), selectedType);
						if(nome1.getText().equals("0")||nome2.getText().equals("0")){
							nome1.setText(t);
							nome2.setText(t);
						}
					}
					

					repaint();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null,"As posicoes estao incorretas. Tente novamente com posicoes validas","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}

		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem RemVertice.
	 * @since 1.0 
	 */
	private class HandlerRemVertice implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String inputValue = JOptionPane.showInputDialog(null,"Entre com o nome do componente:","Remover Componente", JOptionPane.QUESTION_MESSAGE);
			try{
				while(inputValue.equals(""))
					inputValue = JOptionPane.showInputDialog(null,"\nUm componente nao pode ser nulo, certo?\n\nInsira o nome do vertice:","Que feio! :/",JOptionPane.QUESTION_MESSAGE);
				gc.removerVertice(inputValue);
				repaint();
			}
			catch(NullPointerException ex){}

		}

	}

	/**
	 * Classe privada que trata eventos do JMenuItem AddAresta.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerAddAresta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();
			//JTextField nome3 = new JTextField();

			Object[] message = {
					"Primeiro componente:", nome1,
					"Segundo componente:", nome2
			};		
			int option = JOptionPane.showConfirmDialog(null, message, "Inserir Ligacao", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				gc.addAresta(nome1.getText(),nome2.getText(),0);
				repaint();
			}


			/*JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();
			JTextField peso = new JTextField();
			String t = "0";
			Object[] message = {
					"Primeiro vertice:", nome1,
					"Segundo vertice:", nome2,
					"Entre com o peso (Bellman-Ford, Djisktra)-(Opcional):", peso
			};		

			int option = JOptionPane.showConfirmDialog(frameDeControle, message, "Inserir Edge", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try{
					if(peso.getText().equals("")){
						peso.setText(t);
					}

					while(peso.getText().equals("")){

						option = JOptionPane.showConfirmDialog(frameDeControle, message,"Insira novamente! :/", JOptionPane.OK_CANCEL_OPTION);
						if(peso.getText().equals("")){
							peso.setText(t);
						}
						if(option == JOptionPane.CANCEL_OPTION){
							break;
						}	
					}
					if(option == JOptionPane.OK_OPTION){
						gc.addAresta(nome1.getText(),nome2.getText(),Integer.parseInt(peso.getText()));
					}

					repaint();
				}
				catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(frameDeControle, "O peso está incorreto. Tente novamente com pesos válidos","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}*/
		}

	}

	/**
	 * Classe privada que trata eventos do JMenuItem RemAresta.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerRemAresta implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();

			Object[] message = {
					"Primeiro componente:", nome1,
					"Segundo componente:", nome2
			};		
			int option = JOptionPane.showConfirmDialog(null, message, "Remover Ligacao", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				//gc.addAresta(nome1.getText(),nome2.getText());
				try {
					while((nome1.getText().equals("")||nome2.getText().equals(""))&&(option == JOptionPane.OK_OPTION)){
						option = JOptionPane.showConfirmDialog(null, message, "Remover Ligacao", JOptionPane.OK_CANCEL_OPTION);
					}
					if(option == JOptionPane.OK_OPTION)
						gc.removerAresta(nome1.getText(), nome2.getText());
					repaint();
				}
				catch(NullPointerException ex){

					//JOptionPane.showMessageDialog(frameDeControle,"A Edge inserida nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Resetar.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerResetar implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			gc.resetarEstado();
			JOptionPane.showMessageDialog(null, "Resetado com sucesso. O grafo voltou ao estado original","Resetar Graph", JOptionPane.INFORMATION_MESSAGE);
			repaint();
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem GerarAleatorio.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerGerarAleatorio implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] message = {"Esta ação apagará todo o conteudo do Graph atual e gerará um novo grafo aleatoriamente.\nVoce tem certeza disso?"};
			int option = JOptionPane.showOptionDialog(null, message, "Graph Aleatório", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Nao"}, "Nao");
			if(option == JOptionPane.YES_OPTION){
				setGrafo(new Graph());
				gc.setGrafo(G);
				dropPane.setGrafo(G);
				repaint();
				gc.gerarAleatorio();
				repaint();
			}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem BuscaEmLargura.
	 * @version 1.0
	 * @since 1.0 
	 */
	private final class HandlerBuscaEmLargura implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			/*JTextField nome = new JTextField();
			JTextField nome2 = new JTextField();

			Object[] message = {
					"Entre com o nome do vertice fonte:", nome,
					"Entre com o tempo desejado das iterações (Em Segundos):", nome2
			};

			int option = JOptionPane.showConfirmDialog(frameDeControle, message, "Busca em Largura", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				//gc.BuscaEmLargura(nome.getText(), Integer.parseInt(nome2.getText()));
				try{
					//int i = Integer.parseInt(nome2.getText());
					//gc.BuscaEmLargura(nome.getText());
				}
				catch(IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(frameDeControle,"O tempo está incorreto, tente novamente com um tempo válido.\n(SEGUNDO - INTEIRO)","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}*/

			String inputValue = JOptionPane.showInputDialog(null,"Entre com o nome do vertice fonte:","Busca em Largura", JOptionPane.QUESTION_MESSAGE);
			try{
				while(inputValue.equals(""))
					inputValue = JOptionPane.showInputDialog(null,"\nUm vertice nao pode ser nulo, certo?\n\nInsira o nome do vertice fonte:","Busca em Largura",JOptionPane.QUESTION_MESSAGE);
				gc.buscaEmLargura(inputValue);
				repaint();
			}
			catch(NullPointerException ex){}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem BuscaEmProfundidade.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerBuscaEmProfundidade implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			/*int option = JOptionPane.showConfirmDialog(frameDeControle, "Deseja executar a Busca em Profundidade?", "Busca em Largura", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				gc.BuscaEmProfundidade();
				repaint();
			}*/


			String inputValue = JOptionPane.showInputDialog(null,"Entre com o nome do vertice fonte:","Busca em Profundidade", JOptionPane.QUESTION_MESSAGE);
			try{
				while(inputValue.equals(""))
					inputValue = JOptionPane.showInputDialog(null,"\nUm vertice nao pode ser nulo, certo?\n\nInsira o nome do vertice fonte:","Busca em Profundidade",JOptionPane.QUESTION_MESSAGE);
				gc.buscaEmProfundidade(inputValue);
				repaint();
			}
			catch(NullPointerException ex){}
		}
	}

	/*private class HandlerBellman_Ford implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String inputValue = JOptionPane.showInputDialog(null,"Entre com o nome do vertice fonte:","Busca em Largura", JOptionPane.QUESTION_MESSAGE);
			boolean a;
			try{
				while(inputValue.equals(""))
					inputValue = JOptionPane.showInputDialog(null,"\nUm vertice nao pode ser nulo, certo?\n\nInsira o nome do vertice fonte:","Busca em Largura",JOptionPane.QUESTION_MESSAGE);
				a = gc.bellman_ford(inputValue);
				JOptionPane.showMessageDialog(frameDeControle, a,"Geral", JOptionPane.INFORMATION_MESSAGE);
				repaint();
			}
			catch(NullPointerException ex){}
		}
	}*/

	/**
	 * Classe privada que trata eventos do JMenuItem Geral.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerGeral implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String nome = null;
			if(G.getDir())
				nome = "Direcionado.";
			else
				nome = "Não direcionado.";
			JOptionPane.showMessageDialog(null, "Componentes:"+ G.getV().size()+ "\n Ligacoes:" +G.getE().size()+ "\n" +nome,"Geral", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem VerticeEspecifico.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerVerticeEsp implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//String inputValue = JOptionPane.showInputDialog(frameDeControle,"Entre com o nome do vertice","Vertex (Especifico)", JOptionPane.DEFAULT_OPTION);
			JTextField nome1 = new JTextField();

			Object[] message = {"Entre com o nome do componente", nome1};

			int option = JOptionPane.showConfirmDialog(null, message, "Componente (Especifico)", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try {
					String value = G.getVertice(nome1.getText()).getPi().getNome();
					JOptionPane.showMessageDialog(null, "Nome: "+G.getVertice(nome1.getText()).getNome()+"\nCor: "+G.getVertice(nome1.getText()).getCor()+"\nPosicao: x="+G.getVertice(nome1.getText()).getFigura().getX()+", y="+G.getVertice(nome1.getText()).getFigura().getY()+"\nD: "+G.getVertice(nome1.getText()).getD()+"\nF: "+G.getVertice(nome1.getText()).getF()+"\nPredecessor: "+value+"\n","Componente (Especifico)", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(NullPointerException ex){
					try {
						JOptionPane.showMessageDialog(null, "Nome: "+G.getVertice(nome1.getText()).getNome()+"\nCor: "+G.getVertice(nome1.getText()).getCor()+"\nPosicao: x="+G.getVertice(nome1.getText()).getFigura().getX()+", y="+G.getVertice(nome1.getText()).getFigura().getY()+"\nD: "+G.getVertice(nome1.getText()).getD()+"\nF: "+G.getVertice(nome1.getText()).getF()+"\nPredecessor: "+"null"+"\n","Componente (Especifico)", JOptionPane.INFORMATION_MESSAGE);
					}
					catch(NullPointerException ex2) {
						JOptionPane.showMessageDialog(null,"O componente inserido nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem ArestaEspecifica.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerArestaEsp implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();

			Object[] message = {
					"Primeiro componente:", nome1,
					"Segundo componente:", nome2
			};		
			int option = JOptionPane.showConfirmDialog(null, message, "Ligacao (Especifica)", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try {
					JOptionPane.showMessageDialog(null, "Primeiro Componente: "+G.getVertice(nome1.getText()).getNome()+"\nSegundo Componente: "+G.getVertice(nome2.getText()).getNome()+"\nPeso: "+G.getAresta(nome1.getText(),nome2.getText()).getPeso(), "Ligacao Especifica", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(NullPointerException ex){

					JOptionPane.showMessageDialog(null,"A Ligacao inserida nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Conjunto de Vertices.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerCV implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JOptionPane.showMessageDialog(null, "Componentes: "+G.getInfoVertices()+ "\nQuantidade: "+ G.getV().size() ,"Conjunto de Componente", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Conjunto de Arestas.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerCA implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {

			JOptionPane.showMessageDialog(null,"Ligacoes: " + G.getInfoArestas() + "\nQuantidade: " + G.getE().size()  ,"Conjunto de Ligacoes", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Verificar atualizacoes.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerVAtual implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Nao foi possivel verificar novas atualizacoes.","Ops! :(", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Sobre.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerSobre implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Desenvolvido por: \n\nThiago Mendes Ripardo Aguiar.\n\n","Sobre", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}


