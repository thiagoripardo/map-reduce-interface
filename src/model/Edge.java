package model;

import java.io.Serializable;

/**
 * Classe que representa a Edge de um grafo.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Edge implements Serializable {

	private static final long serialVersionUID = 1L;
	private int weight;
	private String name;
	private Vertex u, v;
	private EdgePicture a = null;
	
	/**
	 * Construtor Edge
	 * @since 1.0 
	 */
	public Edge(){}

	/**
	 * Construtor Edge
	 * @param u model.Vertex
	 * @param v model.Vertex
	 * @since 1.0 
	 */
	public Edge(Vertex u, Vertex v){
		setVerticeU(u);
		setVerticeV(v);
		this.a = new EdgePicture(u.getPicture(),v.getPicture(), 0);
	}
	
	/**
	 * Construtor Edge
	 * @param u model.Vertex
	 * @param v model.Vertex
	 * @param peso int
	 * @since 1.0 
	 */
	public Edge(Vertex u, Vertex v, int peso){
		setVerticeU(u);
		setVerticeV(v);
		setPeso(peso);
		this.a = new EdgePicture(u.getPicture(),v.getPicture(), peso);
	}

	/**
	 * Construtor Edge
	 * @param u model.Vertex
	 * @param v model.Vertex
	 * @param name String
	 * @since 1.0 
	 */
	public Edge(Vertex u, Vertex v, String name){
		setNome(name);
		setVerticeU(u);
		setVerticeV(v);
		this.a = new EdgePicture(u.getPicture(),v.getPicture(), 0);
	}

	/**
	 * Construtor Edge
	 * @param u model.Vertex
	 * @param v model.Vertex
	 * @param name String
	 * @param weight int
	 * @since 1.0 
	 */
	public Edge(Vertex u, Vertex v, String name, int weight){
		setNome(name);
		setVerticeU(u);
		setVerticeV(v);
		setPeso(weight);
		this.a = new EdgePicture(u.getPicture(),v.getPicture(), weight);
	}

	/**
	 * Metodo para setar nome da aresta
	 * @since 1.0 
	 */
	public void setNome(String name){
		this.name = name;
	}
	
	/**
	 * Metodo para setar vertice u
	 * @since 1.0 
	 */
	public void setVerticeU(Vertex u){
		this.u = u;
	}

	/**
	 * Metodo para setar vertice v
	 * @since 1.0 
	 */
	public void setVerticeV(Vertex v){
		this.v = v;
	}

	/**
	 * Metodo para setar peso
	 * @since 1.0 
	 */
	public void setPeso(int weight){
		this.weight = weight;
	}

	/**
	 * Metodo para retornar vertice u
	 * @return Vertex u
	 * @since 1.0 
	 */
	public Vertex getU(){
		return this.u;
	}

	/**
	 * Metodo para retornar vertice u
	 * @return Vertex v
	 * @since 1.0 
	 */
	public Vertex getV(){
		return this.v;
	}

	/**
	 * Metodo para retornar nome da aresta
	 * @return nome String
	 * @since 1.0 
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Metodo para retornar peso da aresta
	 * @return peso int
	 * @since 1.0 
	 */
	public int getWeight(){
		return this.weight;
	}

	/**
	 * Metodo para retornar Figura da aresta
	 * @return a model.EdgePicture
	 * @since 1.0 
	 */
	public EdgePicture getPicture() throws NullPointerException{
		if(this.v == null) throw new NullPointerException("NULO");
		else return this.a;
	}

	/**
	 * Compara duas arestas para ver se sao iguais
	 * @return boolean
	 * @since 1.0 
	 */
	public boolean equals(Edge e){
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
	public boolean equalsOposite(Edge e){
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
	public boolean containsV(Vertex v) {
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
	public boolean containsU(Vertex u) {
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
	public boolean containsUV(Vertex u, Vertex v) {
		if((this.u.equals(u))&&(this.v.equals(v)))
			return true;
		else
			return false;
	}
}
