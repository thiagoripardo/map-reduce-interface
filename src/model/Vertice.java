package model;

import java.io.Serializable;

/**
 * Classe que respresenta um vertice de um grafo.
 * Modelo principal.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Vertice implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer d, f;
	private String nome, cor;
	private Vertice pi;
	private int x, y;
	FiguraVertice v = null;
	
	/**
	 * Construtor Vertice
	 * @since 1.0 
	 */
	public Vertice(){}

	/**
	 * Construtor Vertice
	 * @param nome String
	 * @since 1.0 
	 */
	public Vertice(String nome){
		this.setNome(nome);
		this.v = new FiguraVertice(getNome(), 30, 30, getD(), getF());
	}
	
	/**
	 * Construtor Vertice
	 * @param nome String
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public Vertice(String nome, int x, int y){
		this.setNome(nome);
		this.setPonto(x, y);
		this.v = new FiguraVertice(getNome(), x, y, getD(), getF());
	}
	
	/**
	 * Construtor Vertice
	 * @param nome String
	 * @param x int
	 * @param y int
	 * @param cor String
	 * @since 1.0 
	 */
	public Vertice(String nome, int x, int y, String cor){
		this.setNome(nome);
		this.setPonto(x, y);
		this.setCor(cor);
		this.v = new FiguraVertice(getNome(), x, y, getD(), getF());
	}

	/**
	 * Construtor Vertice
	 * @param nome String
	 * @param cor String
	 * @since 1.0 
	 */
	public Vertice(String nome, String cor){
		this.setNome(nome);
		this.setCor(cor);
		this.v = new FiguraVertice(getNome(), 30, 30, getD(), getF());
	}
	
	/**
	 * Setar figura pela posicao
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void setFigura(int x, int y){
		this.v = new FiguraVertice(getNome(), x, y, getD(), getF());
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
	public void setPonto(int x, int y) {
        this.setX(x);
        this.setY(y);
    }
	
	/**
	 * Setar nome do vertice
	 * @param nome String
	 * @since 1.0 
	 */
	public void setNome(String nome){
		this.nome = nome;
	}

	/**
	 * Setar cor do vertice
	 * @param cor String
	 * @since 1.0 
	 */
	public void setCor(String cor){
		this.cor = cor;
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
	 * @param pi model.Vertice
	 * @since 1.0 
	 */
	public void setPi(Vertice pi){
		this.pi = pi;
	}
	
	/**
	 * Retornar figura associada ao vertice
	 * @return v model.FiguraVertice
	 * @throws NullPointerException
	 * @since 1.0 
	 */
	public FiguraVertice getFigura() throws NullPointerException{
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
	public String getNome(){
		return this.nome;
	}
	
	/**
	 * Retornar cor do vertice
	 * @return cor String
	 * @since 1.0 
	 */
	public String getCor(){
		return this.cor;
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
	 * @return pi model.Vertice
	 * @throws NullPointerException
	 * @since 1.0 
	 */
	public Vertice getPi() throws NullPointerException{
		if(this.pi == null) throw new NullPointerException("NULO");
		else return this.pi;
	}
	
	/**
	 * Compara dois vertices para saber se sao iguais
	 * @param u model.Vertice
	 * @since 1.0 
	 */
	public boolean equals(Vertice u){
		
		if((this.nome.equals(u.getNome())))	
			return true;
		else
			return false;
	}
}
