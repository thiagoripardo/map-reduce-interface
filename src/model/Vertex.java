package model;

import java.io.Serializable;

/**
 * Classe que respresenta um vertice de um grafo.
 * Modelo principal.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Vertex implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer d, f;
	private String name, color;
	private Vertex pi;
	private int x, y;
	VertexPicture v = null;
	Component comp = null;

	/**
	 * Construtor Vertex
	 * @since 1.0 
	 */
	public Vertex(){}

	/**
	 * Construtor Vertex
	 * @param name String
	 * @since 1.0 
	 */
	public Vertex(String name){
		this.setName(name);
		this.v = new VertexPicture(getName(), 30, 30, getD(), getF());
	}
	
	/**
	 * Construtor Vertex
	 * @param name String
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public Vertex(String name, int x, int y){
		this.setName(name);
		this.setPoint(x, y);
		this.v = new VertexPicture(getName(), x, y, getD(), getF());
	}
	
	/**
	 * Construtor Vertex
	 * @param name String
	 * @param x int
	 * @param y int
	 * @param color String
	 * @since 1.0 
	 */
	public Vertex(String name, int x, int y, String color){
		this.setName(name);
		this.setPoint(x, y);
		this.setColor(color);
		this.v = new VertexPicture(getName(), x, y, getD(), getF());
	}

	/**
	 * Construtor Vertex
	 * @param name String
	 * @param color String
	 * @since 1.0 
	 */
	public Vertex(String name, String color){
		this.setName(name);
		this.setColor(color);
		this.v = new VertexPicture(getName(), 30, 30, getD(), getF());
	}
	
	/**
	 * Setar figura pela posicao
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void setPicture(int x, int y){
		this.v = new VertexPicture(getName(), x, y, getD(), getF());
	}
	
	/**
	 * Setar posicao x
	 * @param x int
	 * @since 1.0 
	 */
	public void setX(int x) {
        this.x = x;
    }
	
	/**
	 * Setar posicao y
	 * @param y int
	 * @since 1.0 
	 */
	public void setY(int y) {
        this.y = y;
    }
	
	/**
	 * Setar posicao x e y
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void setPoint(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
	
	/**
	 * Setar nome do vertice
	 * @param name String
	 * @since 1.0 
	 */
	public void setName(String name){
		this.name = name;
	}

	/**
	 * Setar cor do vertice
	 * @param color String
	 * @since 1.0 
	 */
	public void setColor(String color){
		this.color = color;
	}

	/**
	 * Setar D do vertice
	 * @param d Integer
	 * @since 1.0 
	 */
	public void setD(Integer d){
		this.d = d;
	}
	
	/**
	 * Setar F do vertice
	 * @param f Integer
	 * @since 1.0 
	 */
	public void setF(Integer f){
		this.f = f;
	}
	
	/**
	 * Setar predecessor do vertice
	 * @param pi model.Vertex
	 * @since 1.0 
	 */
	public void setPi(Vertex pi){
		this.pi = pi;
	}
	
	/**
	 * Retornar figura associada ao vertice
	 * @return v model.VertexPicture
	 * @throws NullPointerException
	 * @since 1.0 
	 */
	public VertexPicture getPicture() throws NullPointerException{
		if(this.v == null) throw new NullPointerException("NULO");
		else return this.v;
	}
	
	/**
	 * Retornar posição x vertice
	 * @return x int
	 * @since 1.0 
	 */
	public int getX() {
        return this.x;
    }
	
	/**
	 * Retornar posição y vertice
	 * @return y int
	 * @since 1.0 
	 */
	public int getY() {
        return this.y;
    }
	
	/**
	 * Retornar nome do vertice
	 * @return nome String
	 * @since 1.0 
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Retornar cor do vertice
	 * @return cor String
	 * @since 1.0 
	 */
	public String getColor(){
		return this.color;
	}

	/**
	 * Retornar D do vertice
	 * @return d Integer
	 * @since 1.0 
	 */
	public Integer getD() {
		return this.d;
	}
	
	/**
	 * Retornar F do vertice
	 * @return f Integer
	 * @since 1.0 
	 */
	public Integer getF(){
		return this.f;
	}

	/**
	 * Retornar predecessor do vertice
	 * @return pi model.Vertex
	 * @throws NullPointerException
	 * @since 1.0 
	 */
	public Vertex getPi() throws NullPointerException{
		if(this.pi == null) throw new NullPointerException("NULO");
		else return this.pi;
	}
	
	public Component getComponent() throws NullPointerException{
		if(this.comp == null) throw new NullPointerException("NULO");
		else return this.comp;
	}
	
	public void setComponent(Component comp) {
		this.comp = comp;
	}
	
	/**
	 * Compara dois vertices para saber se sao iguais
	 * @param u model.Vertex
	 * @since 1.0 
	 */
	public boolean equals(Vertex u){
		
		if((this.name.equals(u.getName())))	
			return true;
		else
			return false;
	}
}
