package view;

import javax.swing.*;

import model.Graph;
import readerwriter.GraphReader;
import readerwriter.GraphWriter;
import controller.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * Esta eh a janela principal do programa, onde estao as principais acoes. 
 * Como criar um novo grafo, inserir vertices e arestas, gerar e muitas outras (Listeners).
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */

public final class GUIGraph extends GUI {

	private static final long serialVersionUID = 1L;
	private Graph g = new Graph(true);
	private GControl gc = new GControl(g);
	private final GraphPane dropPane = new GraphPane(gc);
	
	/**
	 * Construtor da classe GUIGraph
	 */
	
	public GUIGraph(){

		super("MapReduceInterface");
		init();
	}

	/**
	 * Inicia os objetos como: paineis, menus, menuitens, barras de menu, etc.
	 */
	
	public void init(){

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
		
		ImageIcon exec = new ImageIcon(this.getClass().getResource("images/executar/play.png"));
		JMenuItem itemExec = new JMenuItem("Executar", exec);

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
		addV.addActionListener(new HandlerAddVertex());

		// Botao remover vertice
		JButton remV = new JButton();
		remV.setIcon(vr);
		remV.setOpaque(false);
		remV.addActionListener(new HandlerRmVertex());

		// Botao procurar vertice
		JButton proV = new JButton();
		proV.setIcon(vesp);
		proV.setOpaque(false);
		proV.addActionListener(new HandlerSpecificVertex());

		// Botao adicionar aresta
		JButton addA = new JButton();
		addA.setIcon(ai);
		addA.setOpaque(false);
		addA.addActionListener(new HandlerAddEdge());

		// Botao remover aresta
		JButton remA = new JButton();
		remA.setIcon(ar);
		remA.setOpaque(false);
		remA.addActionListener(new HandlerRmEdge());

		// Botao procurar aresta
		JButton proA = new JButton();
		proA.setIcon(aesp);
		proA.setOpaque(false);
		proA.addActionListener(new HandlerSpecificEdge());
		
		// Botao procurar aresta
		JButton execMP = new JButton();
		execMP.setIcon(exec);
		execMP.setOpaque(false);
		execMP.addActionListener(new HandlerOpentext());

		toolBar.add(vert);
		toolBar.add(addV);
		toolBar.add(remV);
		toolBar.add(proV);
		toolBar.addSeparator();
		toolBar.add(are);
		toolBar.add(addA);
		toolBar.add(remA);
		toolBar.add(proA);
		toolBar.addSeparator();
		toolBar.add(execMP);

		// Secao de adicionar aos Menus: MenuItens ou outros Menus. Tambem tratamos eventos aqui.
		// Menu Arquivo
		//menuArquivo.setMnemonic(KeyEvent.VK_W);

		// Novo
		itemNovo.addActionListener(new HandlerNew());
		menuArquivo.add(itemNovo);

		// Abrir
		itemAbrir.addActionListener(new HandlerOpen());
		menuArquivo.add(itemAbrir);

		// Salvar
		itemSalvar.addActionListener(new HandlerSave());
		menuArquivo.add(itemSalvar);

		// Sair
		//itemSair.setMnemonic(KeyEvent.VK_C);
		itemSair.addActionListener(new HandlerExit());
		menuArquivo.add(itemSair);

		// Direcionado
		//itemDirecionado.setMnemonic(KeyEvent.VK_C);
//		itemDirecionado.setSelected(true);
//		itemDirecionado.addItemListener(new ItemListener() {
//			public void itemStateChanged(ItemEvent e) {
//
//				if(itemDirecionado.isSelected()){
//					gc.addDir(true);
//					JOptionPane.showMessageDialog(null,"O Graph agora é direcionado.","Direcao do Graph",JOptionPane.INFORMATION_MESSAGE);
//				}
//				else{
//					gc.addDir(false);
//					JOptionPane.showMessageDialog(null,"O Graph agora não é direcionado.","Direcaoo do Graph",JOptionPane.INFORMATION_MESSAGE);
//				}
//				repaint();
//			}
//		});

		// Ajustar
		itemAjustar.addActionListener(new HandlerAdjust());

		// Agrupar
		itemAgrupar.addActionListener(new HandlerAgroup());

		// Inserir Vertex
		//itemVerticeI.setMnemonic(KeyEvent.VK_C);
		itemVerticeI.addActionListener(new HandlerAddVertex());
		menuInserir.add(itemVerticeI);

		// Inserir Edge
		itemArestaI.addActionListener(new HandlerAddEdge());

		menuInserir.add(itemArestaI);

		// Remover Vertex
		itemVerticeR.setMnemonic(KeyEvent.VK_W);
		itemVerticeR.addActionListener(new HandlerRmVertex());

		menuRemover.add(itemVerticeR);

		// Remover Edge
		//itemArestaR.setMnemonic(KeyEvent.VK_W);
		itemArestaR.addActionListener(new HandlerRmEdge());

		menuRemover.add(itemArestaR);

		//menuEditar.setMnemonic(KeyEvent.VK_W);
		//menuEditar.add(itemDirecionado);
		menuEditar.addSeparator();
		menuEditar.add(itemAjustar);
		menuEditar.add(itemAgrupar);
		menuEditar.addSeparator();
		menuEditar.add(menuInserir);
		menuEditar.add(menuRemover);

		// Menu Executar
		
		// Executar
		menuExecutar.add(itemExec);
		menuExecutar.addSeparator();
		
		// Gerar Aleatorio
		itemGerarAle.addActionListener(new HandlerRandomGenerate());
		menuExecutar.add(itemGerarAle);
		menuExecutar.addSeparator();

		// Menu Geral
		//itemGeral.setMnemonic(KeyEvent.VK_C);
		itemGeral.addActionListener(new HandlerGeneral());
		menuInfo.add(itemGeral);

		menuInfo.addSeparator();

		// Vertex Especifico
		//itemVEsp.setMnemonic(KeyEvent.VK_C);
		itemVEsp.addActionListener(new HandlerSpecificVertex());
		menuInfo.add(itemVEsp);

		// Edge Especifica
		//itemAEsp.setMnemonic(KeyEvent.VK_C);
		itemAEsp.addActionListener(new HandlerAddEdge());
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
		itemVAtual.addActionListener(new HandlerVActual());
		menuAjuda.add(itemVAtual);

		menuAjuda.addSeparator();

		// Sobre
		//itemSobre.setMnemonic(KeyEvent.VK_C);
		itemSobre.addActionListener(new HandlerAbout());
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
				"Clique e arraste para mover componentes e ligações"));
		dropPane.setToolTipText("teste");
		
		JPanel geralPane = new JPanel(new BorderLayout());
		
//		JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, dragPane, dropPane);
//		pane.setOneTouchExpandable(true);
//        pane.setDividerLocation(200);
        
		geralPane.setBorder(BorderFactory.createLineBorder(Color.black, 2));
		geralPane.add(toolBar, BorderLayout.PAGE_START);
		geralPane.add(dropPane, BorderLayout.CENTER);
		
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
	public void setGraph(Graph G){
		this.g = G;
	}
	
	
	/**
	 * Classe privada que trata eventos do JMenuItem novo.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerNew implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] message = {"Esta ação apagará todo o conteudo do Graph.\nDeseja salvá-lo antes de cotinuar?"};
			int option = JOptionPane.showOptionDialog(null, message, "Novo Graph", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Não","Cancelar"}, "Sim");
			if(option == JOptionPane.YES_OPTION){
				(new GraphWriter(g)).init();
				setGraph(new Graph());
				gc.setGrafo(g);
				dropPane.setGrafo(gc);
				repaint();
			}
			if(option == JOptionPane.NO_OPTION){
				setGraph(new Graph());
				gc.setGrafo(g);
				dropPane.setGrafo(gc);
				repaint();
			}
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Abrir.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerOpen implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				GraphReader ag = new GraphReader();
				setGraph(ag.init());
				gc.setGrafo(g);
				dropPane.setGrafo(gc);
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
	private class HandlerSave implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			
			(new GraphWriter(g)).init();
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Sair.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerExit implements ActionListener{
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
	private class HandlerAgroup implements ActionListener{

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
					gc.agroup(Integer.parseInt(nome1.getText()), Integer.parseInt(nome2.getText()));
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
	private class HandlerAdjust implements ActionListener{

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
					gc.adjust(nome.getText(), Integer.parseInt(nome1.getText()), Integer.parseInt(nome2.getText()));
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
	private class HandlerAddVertex implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nome = new JTextField();
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();
			String[] titles = new String[] {"DataSource", "Mapper", "Splitter",
                    "Shuffler", "Reducer", "DataSink", "sp2"};

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
						gc.addVertex(nome.getText(),Integer.parseInt(nome1.getText()),Integer.parseInt(nome2.getText()), selectedType);
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
	private class HandlerRmVertex implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String inputValue = JOptionPane.showInputDialog(null,"Entre com o nome do componente:","Remover Componente", JOptionPane.QUESTION_MESSAGE);
			try{
				while(inputValue.equals(""))
					inputValue = JOptionPane.showInputDialog(null,"\nUm componente nao pode ser nulo, certo?\n\nInsira o nome do componente:","Que feio! :/",JOptionPane.QUESTION_MESSAGE);
				gc.rmVertex(inputValue);
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
	private class HandlerAddEdge implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField nome1 = new JTextField();
			JTextField nome2 = new JTextField();
			
			Object[] message = {
					"Primeiro componente:", nome1,
					"Segundo componente:", nome2
			};		
			int option = JOptionPane.showConfirmDialog(null, message, "Inserir Ligacao", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				gc.addEdge(nome1.getText(),nome2.getText(),0);
				repaint();
			}

		}

	}

	/**
	 * Classe privada que trata eventos do JMenuItem RemAresta.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerRmEdge implements ActionListener{

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
						gc.rmEdge(nome1.getText(), nome2.getText());
					repaint();
				}
				catch(NullPointerException ex){

					//JOptionPane.showMessageDialog(frameDeControle,"A Edge inserida nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}

			}

		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem GerarAleatorio.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerRandomGenerate implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Object[] message = {"Esta ação apagará todo o conteudo do Graph atual e gerará um novo grafo aleatoriamente.\nVoce tem certeza disso?"};
			int option = JOptionPane.showOptionDialog(null, message, "Graph Aleatório", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sim", "Nao"}, "Nao");
			if(option == JOptionPane.YES_OPTION){
				setGraph(new Graph());
				gc.setGrafo(g);
				dropPane.setGrafo(gc);
				repaint();
				gc.randomGenerate();
				repaint();
			}
		}
	}
	
	private class HandlerOpentext implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			BufferedReader br;
			String total = "";
			try {
				br = new BufferedReader(new FileReader("/home/thiagoripardo/workspace/map-reduce-interface/src/dictionary.txt"));
				String aLineFromFile = null;
			    while ((aLineFromFile = br.readLine()) != null){
			    	total = total +"\n"+ aLineFromFile;
			    	
			    }        
			    //JOptionPane.showMessageDialog(null, total);
			    JOptionPane.showMessageDialog(null, total,"Resultado", JOptionPane.INFORMATION_MESSAGE);
			    br.close();
			    return;
			} catch (HeadlessException | IOException e1) {
				e1.printStackTrace();
			}
		   
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Geral.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerGeneral implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String nome = null;
			if(g.getDir())
				nome = "Direcionado.";
			else
				nome = "Não direcionado.";
			JOptionPane.showMessageDialog(null, "Componentes:"+ g.getV().size()+ "\n Ligacoes:" +g.getE().size(),"Geral", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem VerticeEspecifico.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerSpecificVertex implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			//String inputValue = JOptionPane.showInputDialog(frameDeControle,"Entre com o nome do vertice","Vertex (Especifico)", JOptionPane.DEFAULT_OPTION);
			JTextField nome1 = new JTextField();

			Object[] message = {"Entre com o nome do componente", nome1};

			int option = JOptionPane.showConfirmDialog(null, message, "Componente (Especifico)", JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				try {
					String value = g.getVertex(nome1.getText()).getPi().getName();
					JOptionPane.showMessageDialog(null, "Nome: "+g.getVertex(nome1.getText()).getName()+"\nCor: "+g.getVertex(nome1.getText()).getColor()+"\nPosicao: x="+g.getVertex(nome1.getText()).getPicture().getX()+", y="+g.getVertex(nome1.getText()).getPicture().getY()+"\nD: "+g.getVertex(nome1.getText()).getD()+"\nF: "+g.getVertex(nome1.getText()).getF()+"\nPredecessor: "+value+"\n","Componente (Especifico)", JOptionPane.INFORMATION_MESSAGE);
				}
				catch(NullPointerException ex){
					try {
						JOptionPane.showMessageDialog(null, "Nome: "+g.getVertex(nome1.getText()).getName()+"\nCor: "+g.getVertex(nome1.getText()).getColor()+"\nPosicao: x="+g.getVertex(nome1.getText()).getPicture().getX()+", y="+g.getVertex(nome1.getText()).getPicture().getY()+"\nD: "+g.getVertex(nome1.getText()).getD()+"\nF: "+g.getVertex(nome1.getText()).getF()+"\nPredecessor: "+"null"+"\n","Componente (Especifico)", JOptionPane.INFORMATION_MESSAGE);
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
	private class HandlerSpecificEdge implements ActionListener{

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
					JOptionPane.showMessageDialog(null, "Primeiro Componente: "+g.getVertex(nome1.getText()).getName()+"\nSegundo Componente: "+g.getVertex(nome2.getText()).getName()+"\nPeso: "+g.getEdge(nome1.getText(),nome2.getText()).getWeight(), "Ligacao Especifica", JOptionPane.INFORMATION_MESSAGE);
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

			JOptionPane.showMessageDialog(null, "Componentes: "+g.getInfoOfVertexSet()+ "\nQuantidade: "+ g.getV().size() ,"Conjunto de Componente", JOptionPane.INFORMATION_MESSAGE);
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

			JOptionPane.showMessageDialog(null,"Ligacoes: " + g.getInfoOfEdgeSet() + "\nQuantidade: " + g.getE().size()  ,"Conjunto de Ligacoes", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Classe privada que trata eventos do JMenuItem Verificar atualizacoes.
	 * @version 1.0
	 * @since 1.0 
	 */
	private class HandlerVActual implements ActionListener{

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
	private class HandlerAbout implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, "Desenvolvido por: \n\nThiago Mendes Ripardo Aguiar.\n\n","Sobre", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}


