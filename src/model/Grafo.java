package model;

import java.io.Serializable;
import java.util.*;

/**
 * Classe que representa o Grafo em nossa aplicacao.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Grafo implements Serializable {

	private static final long serialVersionUID = 1L;
	private HashSet<Vertice> V = new HashSet<>();
	private HashSet<Aresta> E = new HashSet<>();
	private boolean dir;

	/**
	 * Construtor do Grafo
	 * @since 1.0
	 */
	public Grafo() {}
	
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
	public HashSet<Vertice> getV(){
		return this.V;
	}
	
	/**
	 * Metodo para retornar conjunto de arestas
	 * @return E HashSet
	 * @since 1.0
	 */
	public HashSet<Aresta> getE(){
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
	 * @return v model.Vertice
	 * @throws NullPointerException
	 * @since 1.0
	 */
	public Vertice getVertice(String nome) throws NullPointerException{
		Iterator<Vertice> iter = getV().iterator();
		Vertice u = null;
		Vertice v = null;
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
	 * @return e model.Aresta
	 * @throws NullPointerException
	 * @since 1.0
	 */
	public Aresta getAresta(String nome1, String nome2) throws NullPointerException{
		Vertice u = null;
		Vertice v = null;
		Vertice v2 = null;
		
		Iterator<Vertice> iter = getV().iterator();
		while (iter.hasNext()){
			u = iter.next();
			if(u.getNome().equals(nome1)){
				v = u;
			}
			if(u.getNome().equals(nome2)){
				v2 = u;
			}
		}
		
		Iterator<Aresta> iter2 = getE().iterator();
		Aresta e = null;
		Aresta e2 = null;
		
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
		if(e2 == null)throw new NullPointerException("Aresta inexistente!");
		else return e2;
	}
	
	/**
	 * Metodo para retornar informacoes sobre vertices
	 * @return nome model.Vertice
	 * @since 1.0
	 */
	public String getInfoVertices(){
		Iterator<Vertice> iter = getV().iterator();
		String nome = "{";
		Vertice u = null;

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
	 * @return nome model.Aresta
	 * @since 1.0
	 */
	public String getInfoArestas(){
		String nome = "{";

		Iterator<Aresta> iter = getE().iterator();
		Aresta edge = null;
		Vertice u = null;
		Vertice v = null;

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