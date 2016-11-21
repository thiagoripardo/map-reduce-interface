package model;

import java.io.Serializable;

/**
 * Classe que representa a Aresta de um grafo.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Aresta implements Serializable {

	private static final long serialVersionUID = 1L;
	private int peso;
	private String nome;
	private Vertice u, v;
	private FiguraAresta a = null;
	
	/**
	 * Construtor Aresta
	 * @since 1.0 
	 */
	public Aresta(){}

	/**
	 * Construtor Aresta
	 * @param u model.Vertice
	 * @param v model.Vertice
	 * @since 1.0 
	 */
	public Aresta(Vertice u, Vertice v){
		setVerticeU(u);
		setVerticeV(v);
		this.a = new FiguraAresta(u.getFigura(),v.getFigura(), 0);
	}
	
	/**
	 * Construtor Aresta
	 * @param u model.Vertice
	 * @param v model.Vertice
	 * @param peso int
	 * @since 1.0 
	 */
	public Aresta(Vertice u, Vertice v, int peso){
		setVerticeU(u);
		setVerticeV(v);
		setPeso(peso);
		this.a = new FiguraAresta(u.getFigura(),v.getFigura(), peso);
	}

	/**
	 * Construtor Aresta
	 * @param u model.Vertice
	 * @param v model.Vertice
	 * @param nome String
	 * @since 1.0 
	 */
	public Aresta(Vertice u, Vertice v, String nome){
		setNome(nome);
		setVerticeU(u);
		setVerticeV(v);
		this.a = new FiguraAresta(u.getFigura(),v.getFigura(), 0);
	}

	/**
	 * Construtor Aresta
	 * @param u model.Vertice
	 * @param v model.Vertice
	 * @param nome String
	 * @param peso int
	 * @since 1.0 
	 */
	public Aresta(Vertice u, Vertice v, String nome, int peso){
		setNome(nome);
		setVerticeU(u);
		setVerticeV(v);
		setPeso(peso);
		this.a = new FiguraAresta(u.getFigura(),v.getFigura(), peso);
	}

	/**
	 * Metodo para setar nome da aresta
	 * @deprecated
	 * @since 1.0 
	 */
	public void setNome(String nome){
		this.nome = nome;
	}
	
	/**
	 * Metodo para setar vertice u
	 * @since 1.0 
	 */
	public void setVerticeU(Vertice u){
		this.u = u;
	}

	/**
	 * Metodo para setar vertice v
	 * @since 1.0 
	 */
	public void setVerticeV(Vertice v){
		this.v = v;
	}

	/**
	 * Metodo para setar peso
	 * @since 1.0 
	 */
	public void setPeso(int peso){
		this.peso = peso;
	}

	/**
	 * Metodo para retornar vertice u
	 * @return Vertice u
	 * @since 1.0 
	 */
	public Vertice getU(){
		return this.u;
	}

	/**
	 * Metodo para retornar vertice u
	 * @return Vertice v
	 * @since 1.0 
	 */
	public Vertice getV(){
		return this.v;
	}

	/**
	 * Metodo para retornar nome da aresta
	 * @deprecated
	 * @return nome String
	 * @since 1.0 
	 */
	public String getNome(){
		return this.nome;
	}

	/**
	 * Metodo para retornar peso da aresta
	 * @return peso int
	 * @since 1.0 
	 */
	public int getPeso(){
		return this.peso;
	}

	/**
	 * Metodo para retornar Figura da aresta
	 * @return a model.FiguraAresta
	 * @since 1.0 
	 */
	public FiguraAresta getFigura() throws NullPointerException{
		if(this.v == null) throw new NullPointerException("NULO");
		else return this.a;
	}

	/**
	 * Compara duas arestas para ver se sao iguais
	 * @return boolean
	 * @since 1.0 
	 */
	public boolean equals(Aresta e){
		if((this.u.equals(e.getU()))&&(this.v.equals(e.getV())))
			return true;
		else
			return false;
	}

	/**
	 * Compara duas arestas para ver se sao de direcao opostas
	 * @return boolean
	 * @since 1.0 
	 */
	public boolean equalsOposta(Aresta e){
		if((this.u.equals(e.getV()))&&(this.v.equals(e.getU())))
			return true;
		else
			return false;
	}

	/**
	 * Verifica se contem determidado vertice na posicao v
	 * @return boolean
	 * @since 1.0 
	 */
	public boolean containsV(Vertice v) {
		if(this.v.equals(v))
			return true;
		else
			return false;
	}

	/**
	 * Verifica se contem determidado vertice na posicao u
	 * @return boolean
	 * @since 1.0 
	 */
	public boolean containsU(Vertice u) {
		if(this.u.equals(u))
			return true;
		else
			return false;
	}

	/**
	 * Verifica se contem determidado vertice na posicao u e vertice na posicao v
	 * @return boolean
	 * @since 1.0 
	 */
	public boolean containsUV(Vertice u, Vertice v) {
		if((this.u.equals(u))&&(this.v.equals(v)))
			return true;
		else
			return false;
	}
}
