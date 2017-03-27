package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;

/**
 * Classe que representa o desenho de um vertice e seus respectivos movimentos.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class VertexPicture implements Serializable {

	/**
	 * Atributos
	 * Posicao x e y da imagem
	 * Posicao dx e dy (tempo real) da imagem
	 * Area do vertice areaVertice
	 * @since 1.0
	 */
	private static final long serialVersionUID = 1L;
	int x, y;
	int dx,dy; 
	Integer nomeD, nomeF;
	String nomeVertice, cor="dgray";
	public boolean areaVertice;

	/**
	 * Construtor VertexPicture
	 * @since 1.0
	 */
	public VertexPicture(){

		this.dx = this.x = 30;
		this.dy = this.y = 30;

	}

	/**
	 * Construtor VertexPicture
	 * @param nome String
	 * @param x int
	 * @param y int
	 * @param nomeD Integer
	 * @param nomeF Integer
	 * @since 1.0
	 */
	public VertexPicture(String nome ,int x, int y, Integer nomeD, Integer nomeF){

		
		this.nomeVertice = nome;
		this.nomeD = nomeD;
		this.nomeF = nomeF;
		
		//inicia variavel X e Y com a posição inicial
		if((x==0)||(y==0)){
			this.dx = this.x = x+100;
			this.dy = this.y = y+100;
		}
		
		
		else{
			this.dx = this.x = x;
			this.dy = this.y = y;
		}
	}
	
	/**
	 * Metodo para movimentar a imagem quando clicada e arrastada
	 * E atribuir o valor atual da posicao X e Y da imagem as variveis X e Y
	 * @since 1.0
	 */
	public void move(){

		x = dx;
		y = dy;

	}

	/**
	 * Metodo para desenhar circulo que representa um vertice
	 * @param g Graphics
	 * @since 1.0
	 */
	public void desenhandoComponente(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		
		if(getCor().equals("dgray"))
        	g2d.setPaint(Color.DARK_GRAY);
		if(getCor().equals("Branco"))
        	g2d.setPaint(Color.white);
		if(getCor().equals("Cinza"))
        	g2d.setPaint(Color.gray);
		if(getCor().equals("Preto"))
        	g2d.setPaint(Color.black);
		if(getCor().equals("Source"))
        	g2d.setPaint(Color.red);
		if(getCor().equals("Mapper"))
        	g2d.setPaint(Color.blue);
		if(getCor().equals("Reducer"))
        	g2d.setPaint(Color.gray);
		if(getCor().equals("Splitter"))
        	g2d.setPaint(Color.yellow);
		if(getCor().equals("Shuffler"))
        	g2d.setPaint(Color.black);
		if(getCor().equals("Result"))
        	g2d.setPaint(Color.green);
		
		
		
		//cria a figura
		//g2d.fillOval(getX(),getY(),50,50);
		g2d.fillRect(getX(), getY(), 75, 50);
		g2d.setPaint(Color.black);
		Ellipse2D.Double circle = new Ellipse2D.Double(getX(),getY(),50,50);
		//g2d.fillOval(getX(),getY(),50,50);
		
		g2d.draw(new Arc2D.Double(getX() -35, getY() +12.5, 25, 25, 90, -180, Arc2D.OPEN));
		g.drawLine(getX() -10, getY() +25, getX(), getY() +25);
		g.drawLine(getX() +75, getY() +25, getX() +85, getY() +25);
		g2d.fillOval(getX() +85, getY() +15, 20, 20);
		g2d.drawString(this.nomeVertice, getX()+16, getY()-5);
		
//		if(getCor().equals("Preto"))
//			g2d.setPaint(Color.white);
//		if ((this.nomeD != null)||(this.nomeF != null)){
//			if((this.nomeD != null)&&(this.nomeF == null))
//				g2d.drawString(Integer.toString(this.nomeD), getX()+22,getY()+28);
//			if((this.nomeD != null)&&(this.nomeF != null))
//				g2d.drawString(Integer.toString(this.nomeD)+"/"+Integer.toString(this.nomeF), getX()+13,getY()+28);
//		}
		Toolkit.getDefaultToolkit().sync();
    }
	
	/**
	 * Metodo que cria um MouseMotionListener para clicar e arrastar imagem
	 * Pega a posicao X e Y do cursor do mouse e acrescenta o valor na variavel dx e dy. 
	 * Subtrai 25 dos mesmos para o cursor ficar no centro da figura
	 * @param e MouseEvent
	 * @since 1.0
	 */
	public void mouseDragged(MouseEvent e){
		
		dx = e.getX() -25;
		dy = e.getY() -25;
	}
	
	/**
	 * Metodo que cria um MouseMotionListener para capturar o soltar do botao do mouse
	 * @param e MouseEvent
	 * @since 1.0
	 */
	public void mouseReleased(MouseEvent e){
		
		if((x<-25)||(x<-25)||(dy<-25)||(y<-25)){
			dx = 200;
			dy = 200;
		}
		else{
			dx = x;
			dy = y;
		}
		
	}
		
	/**
	 * Metodo que cria um MouseListener para capturar o pressionamento do botao do mouse.
	 * Verifica se foi clicado dentro da area da figura.
	 * @param e MouseEvent
	 * @since 1.0
	 */
	public void mousePressed(MouseEvent e){

		if(e.getX()>= x && e.getY() >= y && e.getX() <= (x+75) && e.getY() <= (y+50)){
			areaVertice = true;
		}
		else{
			areaVertice = false;
		}

	}
	
	/**
	 * Metodo para setar a posicao x
	 * @param x int
	 * @since 1.0
	 */
	public void setX(int x){
		this.dx = this.x = x;
	}
	
	/**
	 * Metodo para setar a posicao y
	 * @param y int
	 * @since 1.0
	 */
	public void setY(int y){
		this.dy = this.y = y;
	}
	
	/**
	 * Metodo para setar cor da figura
	 * @param cor String
	 * @since 1.0
	 */
	public void setCor(String cor){
		this.cor = cor;
	}
	
	/**
	 * Metodo para setar D da figura
	 * @param d Integer
	 * @since 1.0
	 */
	public void setD(Integer d){
		this.nomeD = d;
	}
	
	/**
	 * Metodo para setar F da figura
	 * @param f Integer
	 * @since 1.0
	 */
	public void setF(Integer f){
		this.nomeF = f;
	}
	
	/**
	 * Metodo para retornar posição x da figura
	 * @return x int
	 * @since 1.0
	 */
	public int getX() {
		return x;
	}

	/**
	 * Metodo para retornar posição y da figura
	 * @return y int
	 * @since 1.0
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Metodo para retornar cor da figura
	 * @return cor String
	 * @since 1.0
	 */
	public String getCor(){
		return this.cor;
	}
	
	/**
	 * Metodo para retornar D da figura
	 * @return D Integer
	 * @since 1.0
	 */
	public Integer getD(){
		return this.nomeD;
	}
	
	/**
	 * Metodo para retornar F da figura
	 * @return f Integer
	 * @since 1.0
	 */
	public Integer getF(){
		return this.nomeF;
	}
	
}
