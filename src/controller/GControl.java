package controller;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import view.Observer;
import model.*;

/**
 * Controller principal da aplicacao. Aqui eh onde ocorrem todas as alteracoes no grafo.
 * Exemplos: Adicionar, Remover (vertices e arestas).
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */

public class GControl {
	
	/**
	 * Atributos
	 */
	
	private Graph G = null;
	private  List<Observer> observers = new LinkedList<Observer>();
	//private GraphPane pane = null;

	/**
	 * Construtor GControl
	 * @param G
	 * @since 1.0 
	 */
	public GControl(Graph G){
		this.G = G;
	}
	
	public void attach(Observer observer){
		observers.add(observer);
	}
	
	public void deattach(Observer observer){
		observers.remove(observer);
	}
	
	public void notifyAllObservers(){
		for(Observer observer : observers){
			observer.update();
		}
	}

	/**
	 * Determina se o grafo eh ou nao direcionado.
	 * @param dir boolean
	 * @since 1.0 
	 */
	public void addDir(boolean dir){
		G.setDir(dir);
	}
	
	/**
	 * Adiciona um novo vertice de acordo com seu nome e posicao.
	 * @param name String
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void addVertex(String name, int x, int y) {
		Vertex u = null;
		try {
			u = G.getVertex(name);
		}
		catch (NullPointerException e) {
			G.getV().add(new Vertex(name, x, y));
			//JOptionPane.showMessageDialog(frameDeControle,"Vertex inserido com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
		}
		finally {
			if(u!=null) 
				JOptionPane.showMessageDialog(null,"O componente inserido ja existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adiciona um novo vertice de acordo com seu nome e posicao.
	 * @param name String
	 * @param x int
	 * @param y int
	 * @param color String
	 * @since 1.0 
	 */
	public void addVertex(String name, int x, int y, String color) {
		Vertex u = null;
		try {
			u = G.getVertex(name);
		}
		catch (NullPointerException e) {
			Vertex v;
			v = new Vertex(name, x, y, color);
			v.getPicture().setCor(color);
			G.getV().add(v);
			//JOptionPane.showMessageDialog(frameDeControle,"Vertex inserido com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
		}
		finally {
			if(u!=null) 
				JOptionPane.showMessageDialog(null,"O componente inserido ja existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adiciona vertice v
	 * @param v model.Vertex
	 * @since 1.0 
	 */
	public void addVertex(Vertex v) {
		G.getV().add(v);
	}

	/**
	 * Adiciona a aresta e.
	 * @param e model.Edge
	 * @since 1.0 
	 */
	public void addEdge(Edge e) {
		G.getE().add(e);
	}

	/**
	 * Adiciona aresta para dois vertices conhecidos.
	 * @param u model.Vertex
	 * @param v model.Vertex
	 * @since 1.0 
	 */
	public void addEdge(Vertex u, Vertex v) {
		G.getE().add(new Edge(u, v));
	}

	/**
	 * Adiciona aresta de acordo com o nome de dois vertices e seu peso
	 * @param name1 String
	 * @param name2 String
	 * @param weight int
	 * @since 1.0 
	 */
	public void addEdge(String name1, String name2, int weight) {
		Edge e = null;
		try {
			e = G.getEdge(name1, name2);
		}
		catch(NullPointerException ex){
			Vertex u = null;
			Vertex v = null;
			try{
				u = G.getVertex(name1);
				v = G.getVertex(name2);
			}
			catch(NullPointerException ex2){
				JOptionPane.showMessageDialog(null,"Pelo menos um dos componentes inseridos nao existe, nao poderemos prosseguir. Tente novamente com componentes validos.","Ops! :(",JOptionPane.ERROR_MESSAGE);
			}
			if((u!=null)&&(v!=null)){
				G.getE().add(new Edge(u, v, weight));
				//JOptionPane.showMessageDialog(frameDeControle,"Edge inserida com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		finally{
			if(e!=null) 
				JOptionPane.showMessageDialog(null,"A ligacao inserida ja existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Remove vertice de acordo com seu nome
	 * @param name String
	 * @since 1.0 
	 */
	public void rmVertex(String name) {
		Vertex u = null;
		try{
			u = G.getVertex(name);
			Iterator<Edge> iter = G.getE().iterator();
			Edge e = null;

			while (iter.hasNext()){
				e = iter.next();
				if(e.containsU(u)||e.containsV(u)){
					iter.remove();
					G.getE().remove(e);

				}
			}
			G.getV().remove(u);
			//JOptionPane.showMessageDialog(frameDeControle,"Vertex removido com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(null,"O componente inserido nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Remove aresta de acordo com o nome de seus vertices
	 * @param name1 String
	 * @param name2 String
	 * @since 1.0 
	 */
	public void rmEdge(String name1, String name2) {
		Edge e = null;
		try{

			e = G.getEdge(name1,name2);
			G.getE().remove(e);
			//JOptionPane.showMessageDialog(frameDeControle,"Edge removida com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);

		}
		catch (NullPointerException ex){
			JOptionPane.showMessageDialog(null,"A aresta inserida nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Algoritmo de Busca em largura (BFS)
	 * @see <a href=http://pt.wikipedia.org/wiki/Busca_em_largura>BFS</a>
	 * @param s String
	 * @since 1.0 
	 */
	public void buscaEmLargura(final String s) {
		new Thread(new Runnable() {  
			public void run() {  
				try{
					if(G.getVertex(s) != null){
						resetState();
						Iterator<Vertex> iter = G.getV().iterator();
						Vertex u = new Vertex();
						while (iter.hasNext()){
							u = iter.next();
							u.setColor("Branco");
							u.setD(null); // O parametro null está sendo usado como infinito.
							u.setPi(null);
							atualizar(u);
							sleep();
						}
						G.getVertex(s).setColor("Cinza");
						G.getVertex(s).setD(0);
						atualizar(G.getVertex(s));
						sleep();
						Queue<Vertex> f = new Queue<>();
						f.add(G.getVertex(s));
						while(f.isEmpty() == false){
							u = f.remove();
							if(G.getDir() == false){
								Iterator<Edge> iter2 = G.getE().iterator();
								while (iter2.hasNext()){

									Edge e = iter2.next();
									Vertex v = new Vertex();

									if(e.containsU(u))
										v = e.getV();

									if(e.containsV(u))
										v = e.getU();

									if(G.getV().contains(v)){	
										if(v.getColor().equals("Branco")){
											v.setColor("Cinza");
											v.setD(u.getD() + 1);
											v.setPi(u);
											atualizar(v);
											sleep();
											f.add(v);
										}
									}
								}
								u.setColor("Preto");
								atualizar(u);
								sleep();
							}
							else {
								Iterator<Edge> iter2 = G.getE().iterator();
								while (iter2.hasNext()){

									Edge e = iter2.next();
									Vertex v = new Vertex();

									if(e.containsU(u)){
										v = e.getV();
									}

									if(G.getV().contains(v)){
										if(v.getColor().equals("Branco")){
											v.setColor("Cinza");
											v.setD(u.getD() + 1);
											v.setPi(u);
											atualizar(v);
											sleep();
											f.add(v);
										}
									}
								}
								u.setColor("Preto");
								atualizar(u);
								sleep();
							}
						}
					}
				}
				catch(NullPointerException e) {
					JOptionPane.showMessageDialog(null,"O vertice entrado nao existe. Tente novamente com um vertice valido.","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}  
		}).start();
	}

	/**
	 * Algoritmo de Busca em profundidade (DFS)
	 * @see <a href=http://pt.wikipedia.org/wiki/Busca_em_profundidade>DFS</a>
	 * @param s String
	 * @since 1.0 
	 */
	public void buscaEmProfundidade(final String s){
		new Thread(new Runnable() {  
			public void run() {

				resetState();
				Vertex u = null;
				Iterator<Vertex> iter = G.getV().iterator();
				if(iter.hasNext()){
					try{
						u = G.getVertex(s);
						while (iter.hasNext()){
							u = iter.next();
							u.setColor("Branco");
							u.setPi(null);
							atualizar(u);
							sleep();
						}
						int tempo = 0;

						tempo = visitar(G,G.getVertex(s),tempo);
						Iterator<Vertex> iter2 = G.getV().iterator();
						while (iter2.hasNext()){
							u = iter2.next();
							if(u.getColor().equals("Branco")){
								tempo = visitar(G,u,tempo);
							}
						}
					}
					catch (NullPointerException ex){
						JOptionPane.showMessageDialog(null,"O vértice inserido não existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"O Graph ainda nao contem um vertice, tente novamente apos inserir pelo menos um vertice","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}
		}).start();
	}

	/**
	 * Algoritmo visitar da busca em profundidade.
	 * @param G model.Graph
	 * @param u model.Vertex
	 * @param time int
	 * @return tempo Retorna novo tempo
	 * @since 1.0 
	 */
	public int visitar(Graph G, Vertex u, int time){
		time = time + 1;
		u.setD(time);
		u.setColor("Cinza");
		atualizar(u);
		sleep();
		if(G.getDir() == false){
			Iterator<Edge> iter = G.getE().iterator();
			while (iter.hasNext()){

				Edge e = iter.next();
				Vertex v = new Vertex();

				if(e.containsU(u))
					v = e.getV();

				if(e.containsV(u))
					v = e.getU();

				if(G.getV().contains(v)){
					if(v.getColor().equals("Branco")){
						v.setPi(u);
						time = visitar(G,v, time);
					}
				}
			}
		}
		else{
			Iterator<Edge> iter = G.getE().iterator();
			while (iter.hasNext()){

				Edge e = iter.next();
				Vertex v = new Vertex();

				if(e.containsU(u)){
					v = e.getV();
				}

				if(G.getV().contains(v)){
					if(v.getColor().equals("Branco")){
						v.setPi(u);
						time = visitar(G,v, time);
					}
				}
			}
		}
		u.setColor("Preto");
		time = time + 1;
		u.setF(time);
		atualizar(u);
		sleep();
		return time;
	}

	/**
	 * Atualiza estado de determinado vertice.
	 * Alterando sua cor, seu D e seu F. 
	 * Também repinta após ter alterado.
	 * @param u model.Vertex
	 * @since 1.0 
	 */
	public void atualizar(Vertex u){

		u.getPicture().setCor(u.getColor());
		u.getPicture().setD(u.getD());
		u.getPicture().setF(u.getF());
		notifyAllObservers();
		
	}

	/**
	 * Faz determinada Thread dormir por 1 segundo.
	 * @since 1.0 
	 */
	public void sleep(){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reseta estado atual do grafo para o estado original no qual foi criado.
	 * @since 1.0 
	 */
	public void resetState(){
		Iterator<Vertex> iter = G.getV().iterator();
		Vertex u = null;
		while (iter.hasNext()){
			u = iter.next();
			u.setColor(null);
			u.setD(null);
			u.setF(null);
			u.getPicture().setCor("dgray");
			u.getPicture().setD(null);
			u.getPicture().setF(null);
		}
	}

	/**
	 * Agrupa todos os vertices em uma determinada posicao
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void agroup(int x, int y){
		Iterator<Vertex> iter = G.getV().iterator();
		Vertex u = null;
		if(iter.hasNext()){
			while (iter.hasNext()){
				u = iter.next();
				u.setX(x);
				u.setY(y);
				u.getPicture().setX(x);
				u.getPicture().setY(y);
				//pane.repaint();
			}
		}
		else{
			JOptionPane.showMessageDialog(null,"Nao ha componentes para serem agrupados!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Ajusta um determinado vertice a uma posicao escolhida.
	 * @param name String
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void adjust(String name, int x, int y){
		try{
			Vertex u = G.getVertex(name);
			u.setX(x);
			u.setY(y);
			u.getPicture().setX(x);
			u.getPicture().setY(y);
		}
		catch (NullPointerException ex){
			JOptionPane.showMessageDialog(null,"O componente inserido não existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	

	/**
	 * Gera um grafo aleatorio entre 5 e 25 vertices.
	 * @since 1.0 
	 */
	public void randomGenerate(){

		Random gerador = new Random();
		int i;
		int numeroV = gerador.nextInt(20) + 5;
		int numeroE = gerador.nextInt(numeroV*(numeroV-1)/2);

		for (i=0; i<=numeroV; i++)
			addVertex("v"+String.valueOf(i), gerador.nextInt(840), gerador.nextInt(480) + 10);

		for (i=0; i<=numeroE; i++){

			Edge e = null;
			int primeiroVertice = gerador.nextInt(numeroV);
			int segundoVertice = gerador.nextInt(numeroV);
			try {
				e = G.getEdge("v" + String.valueOf(primeiroVertice), "v" + String.valueOf(segundoVertice));
			}
			catch(NullPointerException ex){
				addEdge("v" + String.valueOf(primeiroVertice), "v" + String.valueOf(segundoVertice), 0);
			}
			finally { 
				if(e!=null) {}
			}
		}
	}

	/**
	 * Seta qual model.Graph ira ser usado pela classe
	 * @param G model.Graph
	 * @since 1.0 
	 */
	public void setGrafo(Graph G){
		this.G = G;
	}

	/**
	 * Retorna o Graph que esta instanciado em GControl
	 * @return G <code>model.Graph</code>
	 * @since 1.0 
	 */
	public Graph getGrafo(){
		return this.G;
	}
	
}
