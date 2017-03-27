package model;

import java.io.Serializable;
import java.util.*;

/**
 * Classe que representa o Graph em nossa aplicacao.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Graph implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashSet<Vertex> V = new HashSet<>();
	private HashSet<Edge> E = new HashSet<>();
	private boolean dir;

	/**
	 * Construtor do Graph
	 * @since 1.0
	 */
	public Graph() {}
	
	/**
	 * Construtor do Graph
	 * @since 1.1
	 */
	public Graph(boolean dir) {
		this.dir = dir;
	}
	
	/**
	 * Metodo para setar se eh direcionado ou nao
	 * @since 1.0
	 */
	public void setDir(boolean dir){
		this.dir = dir;
	}
	
	/**
	 * Metodo para retornar conjunto de vertices
	 * @return V HashSet
	 * @since 1.0
	 */
	public HashSet<Vertex> getV(){
		return this.V;
	}
	
	/**
	 * Metodo para retornar conjunto de arestas
	 * @return E HashSet
	 * @since 1.0
	 */
	public HashSet<Edge> getE(){
		return this.E;
	}
	
	/**
	 * Metodo para retornar direcao
	 * @return dir boolean
	 * @since 1.0
	 */
	public boolean getDir(){
		return this.dir;
	}
	
	/**
	 * Metodo para retornar um determinado vertice
	 * @param nome String
	 * @return v model.Vertex
	 * @throws NullPointerException
	 * @since 1.0
	 */
	public Vertex getVertice(String nome) throws NullPointerException{
		Iterator<Vertex> iter = getV().iterator();
		Vertex u = null;
		Vertex v = null;
		while (iter.hasNext()){
			u = iter.next();
			if(u.getNome().equals(nome)){
				v = u;
			}
		}
		if(v==null) throw new NullPointerException(nome);
		else return v;
	}
	
	/**
	 * Metodo para retornar uma determinada aresta
	 * @param nome1 String
	 * @param nome2 String
	 * @return e model.Edge
	 * @throws NullPointerException
	 * @since 1.0
	 */
	public Edge getAresta(String nome1, String nome2) throws NullPointerException{
		Vertex u = null;
		Vertex v = null;
		Vertex v2 = null;
		
		Iterator<Vertex> iter = getV().iterator();
		while (iter.hasNext()){
			u = iter.next();
			if(u.getNome().equals(nome1)){
				v = u;
			}
			if(u.getNome().equals(nome2)){
				v2 = u;
			}
		}
		
		Iterator<Edge> iter2 = getE().iterator();
		Edge e = null;
		Edge e2 = null;
		
		if(getDir()){
			while (iter2.hasNext()){
				e = iter2.next();
				if(e.containsU(v)&&e.containsV(v2)){
					e2 = e;
				}
			}
		}
		else{
			while (iter2.hasNext()){
				e = iter2.next();
				if((e.containsU(v)&&e.containsV(v2))||(e.containsU(v2)&&e.containsV(v))){
					e2 = e;
				}
			}
		}
		if(e2 == null)throw new NullPointerException("Edge inexistente!");
		else return e2;
	}
	
	/**
	 * Metodo para retornar informacoes sobre vertices
	 * @return nome model.Vertex
	 * @since 1.0
	 */
	public String getInfoVertices(){
		Iterator<Vertex> iter = getV().iterator();
		String nome = "{";
		Vertex u = null;

		while (iter.hasNext()){
			u = iter.next();
			if (nome == "{")
				nome = nome  + u.getNome();
			else
				nome = nome + ", " + u.getNome();
		}
		nome = nome + "}";
		return nome;
	}

	/**
	 * Metodo para retornar informacoes sobre arestas
	 * @return nome model.Edge
	 * @since 1.0
	 */
	public String getInfoArestas(){
		String nome = "{";

		Iterator<Edge> iter = getE().iterator();
		Edge edge = null;
		Vertex u = null;
		Vertex v = null;

		while (iter.hasNext()){

			edge = iter.next();
			u = edge.getU();
			v = edge.getV();
			if (nome == "{")
				nome = nome + "(" + u.getNome() + ", " +v.getNome()+ ")";
			else
				nome = nome + ", " +"(" + u.getNome() + ", " +v.getNome()+ ")";
		}
		nome = nome + "}";
		return nome;
	}
}