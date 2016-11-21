package view;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import javax.swing.Timer;
import model.Aresta;
import model.Grafo;
import model.Vertice;

/**
 * Classe Quadro, onde sao feitos os desenhos e onde sao capturados os movimentos do mouse.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Quadro extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final int DELAY = 5;
	private Timer timer;
	private Grafo G = null;

	/**
	 * Construtor da classe Quadro
	 * 
	 * @param G Grafo
	 */
	
	public Quadro(Grafo G) {

		super(new BorderLayout());
		this.G = G;

		addMouseMotionListener(new MMAdapter());
		addMouseListener(new MAdapter());

		setFocusable(true);
		setDoubleBuffered(true);

		initTimer();    
	}

	/**
	 * Metodo de iniciar o tempo de Delay(de quanto em quanto tempo a tela eh atualizada).
	 * @since 1.0
	 */
	
	private void initTimer() {

		timer = new Timer(DELAY, this);
		timer.start();
	}

	/**
	 * Retornar o tempo
	 * 
	 * @return timer Tempo
	 * @since 1.0
	 */
	
	public Timer getTimer() {

		return timer;
	}

	public void setGrafo(Grafo G){
		this.G = G;
	}
	
	/**
	 * Metodo que pinta os componentes
	 * 
	 * @param g Graphics
	 * @see model.FiguraAresta
	 * @see model.FiguraVertice
	 * @since 1.0
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		if(G.getDir()){
			Iterator<Aresta> iter = G.getE().iterator();
			Aresta e = null;
			while (iter.hasNext()){
				e = iter.next();
				if(e.getU().equals(e.getV()))
					e.getFigura().desenhandoElipse(g);
				else
					e.getFigura().desenhandoSeta(g);
			}
		}
		else{
			Iterator<Aresta> iter = G.getE().iterator();
			Aresta e = null;
			while (iter.hasNext()){
				e = iter.next();
				if(e.getU().equals(e.getV()))
					e.getFigura().desenhandoElipse(g);
				else
					e.getFigura().desenhandoLinha(g);
			}
		}

		Iterator<Vertice> iter2 = G.getV().iterator();
		while (iter2.hasNext())
			iter2.next().getFigura().desenhandoCirculo(g);
	}
	
	/**
	 * Implementacao de actionPerformed. 
	 * Verifica se a area clicada corresponde a uma FiguraVertice, se sim, ela se move e a tela eh repintada.
	 * @param e ActionEvent
	 * @see model.FiguraVertice
	 * @since 1.0
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Iterator<Vertice> iter2 = G.getV().iterator();
		Vertice v = null;
		while (iter2.hasNext()){
			v = iter2.next();
			if(v.getFigura().areaVertice){
				v.getFigura().move();
				repaint();
				break;
			}
		}
	}
	
	/**
	 * Implementacao de MouseMotionAdapter. 
	 * 
	 * @see model.FiguraVertice
	 * @version 1.0
	 * @since 1.0
	 */
	private class MMAdapter extends MouseMotionAdapter{
		
		/**
		 * Captura o clique e arrastar do mouse 
		 * 
		 * @see model.FiguraVertice
		 * @since 1.0
		 */
		public void mouseDragged(MouseEvent e){

			Iterator<Vertice> iter2 = G.getV().iterator();

			while (iter2.hasNext()){
				iter2.next().getFigura().mouseDragged(e);
			}
		}

	}
	
	/**
	 * Implementacao de MouseAdapter. 
	 * 
	 * @see model.FiguraVertice
	 * @version 1.0
	 * @since 1.0
	 */
	private class MAdapter extends MouseAdapter{
		
		/**
		 * Verifica se o botao do mouse foi solto 
		 * 
		 * @see model.FiguraVertice
		 * @since 1.0
		 */
		public void mouseReleased(MouseEvent e){
			Iterator<Vertice> iter2 = G.getV().iterator();

			while (iter2.hasNext()){
				iter2.next().getFigura().mouseReleased(e);
			}
		}
		/**
		 * Verifica se o botao do mouse foi pressionado
		 * 
		 * @see model.FiguraVertice
		 * @since 1.0
		 */
		public void mousePressed(MouseEvent e){
			Iterator<Vertice> iter2 = G.getV().iterator();

			while (iter2.hasNext()){
				iter2.next().getFigura().mousePressed(e);
			}
		}

	}
}
