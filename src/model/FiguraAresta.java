package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.lang.reflect.Array;

/**
 * Classe que representa o desenho de uma aresta entre dois vertices.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class FiguraAresta implements Serializable {

	private static final long serialVersionUID = 1L;
	FiguraVertice img1=null, img2=null;
	int peso;

	/**
	 * Construtor FiguraAresta
	 * @since 1.0 
	 */
	public FiguraAresta(){}

	/**
	 * Construtor FiguraAresta
	 * @param img1 model.FiguraVertice
	 * @param img2 model.FiguraVertice
	 * @param peso int
	 * @since 1.0 
	 */
	public FiguraAresta(FiguraVertice img1, FiguraVertice img2, int peso){

		this.img1 = img1;
		this.img1 = img1;
		this.img2 = img2;
		this.img2 = img2;
		this.peso = peso;
	}

	/**
	 * Metodo para movimentar a imagem quando clicada e arrastada
	 * @param g Graphics
	 * @since 1.0 
	 */
	public void desenhandoLinha(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		g2d.setPaint(Color.black);
		g2d.drawLine(img1.getX()+22, img1.getY()+28, img2.getX()+22, img2.getY()+28);
	}

	/**
	 * Metodo desenhar Seta da aresta do grafo direcionado
	 * @param g Graphics
	 * @since 1.0 
	 */
	public void desenhandoSeta(Graphics g) {

		double deltaX = (img2.getX()-img1.getX());
		double deltaY = (img2.getY()-img1.getY());
		if(deltaX==0){
			deltaX = 0.00001;
		}
		if(deltaY==0){
			deltaY = 0.00001;
		}
		double theta  = Math.atan((deltaY)/(deltaX));
		double theta2, sen, cos;

		if (deltaX < 0.0){
			theta2 = theta+Math.PI;
		}

		else{
			theta2 = theta;
		}

		cos = (28)*Math.cos(theta2);
		sen = (52)*Math.sin(theta2);

		if(sen>0){
			sen = sen-50;

		}

		if(theta2<(7*Math.PI/6)&&theta2>(5*Math.PI/6)){
			sen = cos;
		}

		if(theta2>(-(Math.PI/6))&&theta2<(Math.PI/6)){
			sen = -cos;
		}

		int[] PontosX = {img1.getX()+22,(int) ((img2.getX())+25-cos)};
		int[] PontosY = {img1.getY()+28,(int) ((img2.getY())-sen)};
		desenharLinhaSeta(g, PontosX, PontosY,10,5);
		Toolkit.getDefaultToolkit().sync();
	}

	/**
	 * Metodo desenhar aresta para um mesmo vertice
	 * @param g Graphics
	 * @since 1.0 
	 */
	public void desenhandoElipse(Graphics g) {

		Graphics2D g2d = (Graphics2D) g.create();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		
		g2d.setPaint(Color.black);
		g2d.draw (new Ellipse2D.Double(img1.getX()+22, img1.getY()+28, 30, 30));
	}

	/**
	 * Metodo desenhar linha com seta
	 * @param g Graphics
	 * @param PontosX int
	 * @param PontosY int
	 * @param setaComp int
	 * @param setaLarg int
	 * @since 1.0 
	 */
	private void desenharLinhaSeta(Graphics g, int[] PontosX, int[] PontosY, int setaComp, int setaLarg){

		double theta1;

		//Calcular o tamanho da linha - Converter de Object para Integer e depois para int.
		Object tamOX1 = ((Array.get(PontosX, ((PontosX.length)-2))) );
		Object tamOX2 = ((Array.get(PontosX, ((PontosX.length)-1))) );
		Integer tamIX1 = (Integer)tamOX1;
		int x1 = tamIX1.intValue();
		Integer tamIX2 = (Integer)tamOX2;
		int x2 = tamIX2.intValue();

		Graphics2D g2d = (Graphics2D) g.create();
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(rh);
		g2d.setPaint(Color.black);

		Object tamOY1 = ((Array.get(PontosY, ((PontosY.length)-2))) );
		Object tamOY2 = ((Array.get(PontosY, ((PontosY.length)-1))) );
		Integer tamIY1 = (Integer)tamOY1;
		int y1 = tamIY1.intValue();
		Integer tamIY2 = (Integer)tamOY2;
		int y2 = tamIY2.intValue();

		int deltaX = (x2-x1);
		int deltaY = (y2-y1);


		double theta  = Math.atan((double)(deltaY)/(double)(deltaX));

		if (deltaX < 0.0){
			theta1 = theta+Math.PI;
		}
		else{
			theta1 = theta;
		}

		int compdeltaX =- (int)(Math.cos(theta1)*setaComp);
		int compdeltaY =- (int)(Math.sin(theta1)*setaComp);
		int largdeltaX  =  (int)(Math.sin(theta1)*setaLarg);
		int largdeltaY  =  (int)(Math.cos(theta1)*setaLarg);

		g2d.drawLine(PontosX[0], PontosY[0], PontosX[1], PontosY[1]);
		g2d.drawLine(x2, y2, x2+compdeltaX+largdeltaX, y2+compdeltaY-largdeltaY);
		g2d.drawLine(x2, y2, x2+compdeltaX-largdeltaX, y2+compdeltaY+largdeltaY);

	}
}