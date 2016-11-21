package controller;

import java.util.Iterator;
import java.util.Random;

import javax.swing.JOptionPane;

import view.GUI;
import view.Quadro;
import model.*;

/**
 * Controller principal da nossa aplicacao aqui eh onde ocorrem todas as alteracoes no grafo.
 * Exemplos: Adicionar, Remover (vertices e arestas).
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */

public class GControl {
	
	/**
	 * Atributos
	 */
	
	private Grafo G = null;
	private  GUI frameDeControle;
	private Quadro pane = null;

	/**
	 * Construtor GControl
	 * @param G
	 * @since 1.0 
	 */
	public GControl(Grafo G){
		this.G = G;
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
	 * @param nome String
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void addVertice(String nome, int x, int y) {
		Vertice u = null;
		try {
			u = G.getVertice(nome);
		}
		catch (NullPointerException e) {
			G.getV().add(new Vertice(nome, x, y));
			//JOptionPane.showMessageDialog(frameDeControle,"Vertice inserido com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
		}
		finally {
			if(u!=null) 
				JOptionPane.showMessageDialog(frameDeControle,"O vertice inserido ja existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adiciona um novo vertice de acordo com seu nome e posicao.
	 * @param nome String
	 * @param x int
	 * @param y int
	 * @param cor String
	 * @since 1.0 
	 */
	public void addVertice(String nome, int x, int y, String cor) {
		Vertice u = null;
		try {
			u = G.getVertice(nome);
		}
		catch (NullPointerException e) {
			Vertice v;
			v = new Vertice(nome, x, y, cor);
			v.getFigura().setCor(cor);
			G.getV().add(v);
			//JOptionPane.showMessageDialog(frameDeControle,"Vertice inserido com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
		}
		finally {
			if(u!=null) 
				JOptionPane.showMessageDialog(frameDeControle,"O vertice inserido ja existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Adiciona vertice v
	 * @param v model.Vertice
	 * @since 1.0 
	 */
	public void addVertice(Vertice v) {
		G.getV().add(v);
	}

	/**
	 * Adiciona a aresta e.
	 * @param e model.Aresta
	 * @since 1.0 
	 */
	public void addAresta(Aresta e) {
		G.getE().add(e);
	}

	/**
	 * Adiciona aresta para dois vertices conhecidos.
	 * @param u model.Vertice
	 * @param v model.Vertice
	 * @since 1.0 
	 */
	public void addAresta(Vertice u, Vertice v) {
		G.getE().add(new Aresta(u, v));
	}

	/**
	 * Adiciona aresta de acordo com o nome de dois vertices e seu peso
	 * @param nome1 String
	 * @param nome2 String
	 * @param peso int
	 * @since 1.0 
	 */
	public void addAresta(String nome1, String nome2, int peso) {
		Aresta e = null;
		try {
			e = G.getAresta(nome1, nome2);
		}
		catch(NullPointerException ex){
			Vertice u = null;
			Vertice v = null;
			try{
				u = G.getVertice(nome1);
				v = G.getVertice(nome2);
			}
			catch(NullPointerException ex2){
				JOptionPane.showMessageDialog(frameDeControle,"Pelo menos um dos vertices inseridos nao existe, nao poderemos prosseguir. Tente novamente com vertices validos.","Ops! :(",JOptionPane.ERROR_MESSAGE);
			}
			if((u!=null)&&(v!=null)){
				G.getE().add(new Aresta(u, v, peso));
				//JOptionPane.showMessageDialog(frameDeControle,"Aresta inserida com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		finally{
			if(e!=null) 
				JOptionPane.showMessageDialog(frameDeControle,"A aresta inserida ja existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Remove vertice de acordo com seu nome
	 * @param nome String
	 * @since 1.0 
	 */
	public void removerVertice(String nome) {
		Vertice u = null;
		try{
			u = G.getVertice(nome);
			Iterator<Aresta> iter = G.getE().iterator();
			Aresta e = null;

			while (iter.hasNext()){
				e = iter.next();
				if(e.containsU(u)||e.containsV(u)){
					iter.remove();
					G.getE().remove(e);

				}
			}
			G.getV().remove(u);
			//JOptionPane.showMessageDialog(frameDeControle,"Vertice removido com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(NullPointerException e){
			JOptionPane.showMessageDialog(frameDeControle,"O vertice inserido nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Remove aresta de acordo com o nome de seus vertices
	 * @param nome1 String
	 * @param nome2 String
	 * @since 1.0 
	 */
	public void removerAresta(String nome1, String nome2) {
		Aresta e = null;
		try{

			e = G.getAresta(nome1,nome2);
			G.getE().remove(e);
			//JOptionPane.showMessageDialog(frameDeControle,"Aresta removida com sucesso!","Yeah! :D",JOptionPane.INFORMATION_MESSAGE);

		}
		catch (NullPointerException ex){
			JOptionPane.showMessageDialog(frameDeControle,"A aresta inserida nao existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
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
					if(G.getVertice(s) != null){
						resetarEstado();
						Iterator<Vertice> iter = G.getV().iterator();
						Vertice u = new Vertice();
						while (iter.hasNext()){
							u = iter.next();
							u.setCor("Branco");
							u.setD(null); // O parametro null está sendo usado como infinito.
							u.setPi(null);
							atualizar(u);
						}
						G.getVertice(s).setCor("Cinza");
						G.getVertice(s).setD(0);
						atualizar(G.getVertice(s));
						Fila<Vertice> f = new Fila<>();
						f.add(G.getVertice(s));
						while(f.isEmpty() == false){
							u = f.remove();
							if(G.getDir() == false){
								Iterator<Aresta> iter2 = G.getE().iterator();
								while (iter2.hasNext()){

									Aresta e = iter2.next();
									Vertice v = new Vertice();

									if(e.containsU(u))
										v = e.getV();

									if(e.containsV(u))
										v = e.getU();

									if(G.getV().contains(v)){	
										if(v.getCor().equals("Branco")){
											v.setCor("Cinza");
											v.setD(u.getD() + 1);
											v.setPi(u);
											atualizar(v);
											f.add(v);
										}
									}
								}
								u.setCor("Preto");
								atualizar(u);
							}
							else {
								Iterator<Aresta> iter2 = G.getE().iterator();
								while (iter2.hasNext()){

									Aresta e = iter2.next();
									Vertice v = new Vertice();

									if(e.containsU(u)){
										v = e.getV();
									}

									if(G.getV().contains(v)){
										if(v.getCor().equals("Branco")){
											v.setCor("Cinza");
											v.setD(u.getD() + 1);
											v.setPi(u);
											atualizar(v);
											f.add(v);
										}
									}
								}
								u.setCor("Preto");
								atualizar(u);
							}
						}
					}
				}
				catch(NullPointerException e) {
					JOptionPane.showMessageDialog(frameDeControle,"O vertice entrado nao existe. Tente novamente com um vertice valido.","Ops! :(",JOptionPane.ERROR_MESSAGE);
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

				resetarEstado();
				Vertice u = null;
				Iterator<Vertice> iter = G.getV().iterator();
				if(iter.hasNext()){
					try{
						u = G.getVertice(s);
						while (iter.hasNext()){
							u = iter.next();
							u.setCor("Branco");
							u.setPi(null);
							atualizar(u);
						}
						int tempo = 0;

						tempo = visitar(G,G.getVertice(s),tempo);
						Iterator<Vertice> iter2 = G.getV().iterator();
						while (iter2.hasNext()){
							u = iter2.next();
							if(u.getCor().equals("Branco")){
								tempo = visitar(G,u,tempo);
							}
						}
					}
					catch (NullPointerException ex){
						JOptionPane.showMessageDialog(frameDeControle,"O vértice inserido não existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					JOptionPane.showMessageDialog(frameDeControle,"O Grafo ainda nao contem um vertice, tente novamente apos inserir pelo menos um vertice","Ops! :(",JOptionPane.ERROR_MESSAGE);
				}
			}
		}).start();
	}

	/**
	 * Algoritmo visitar da busca em profundidade.
	 * @param G model.Grafo
	 * @param u model.Vertice
	 * @param tempo int
	 * @return tempo Retorna novo tempo
	 * @since 1.0 
	 */
	public int visitar(Grafo G, Vertice u, int tempo){
		tempo = tempo + 1;
		u.setD(tempo);
		u.setCor("Cinza");
		atualizar(u);
		if(G.getDir() == false){
			Iterator<Aresta> iter = G.getE().iterator();
			while (iter.hasNext()){

				Aresta e = iter.next();
				Vertice v = new Vertice();

				if(e.containsU(u))
					v = e.getV();

				if(e.containsV(u))
					v = e.getU();

				if(G.getV().contains(v)){
					if(v.getCor().equals("Branco")){
						v.setPi(u);
						tempo = visitar(G,v, tempo);
					}
				}
			}
		}
		else{
			Iterator<Aresta> iter = G.getE().iterator();
			while (iter.hasNext()){

				Aresta e = iter.next();
				Vertice v = new Vertice();

				if(e.containsU(u)){
					v = e.getV();
				}

				if(G.getV().contains(v)){
					if(v.getCor().equals("Branco")){
						v.setPi(u);
						tempo = visitar(G,v, tempo);
					}
				}
			}
		}
		u.setCor("Preto");
		tempo = tempo + 1;
		u.setF(tempo);
		atualizar(u);
		return tempo;
	}

	/**
	 * Atualiza estado de determinado vertice.
	 * Alterando sua cor, seu D e seu F. 
	 * Também repinta após ter alterado.
	 * @param u model.Vertice
	 * @since 1.0 
	 */
	public void atualizar(Vertice u){

		u.getFigura().setCor(u.getCor());
		u.getFigura().setD(u.getD());
		u.getFigura().setF(u.getF());
		pane.repaint();
		dormir();
	}

	/**
	 * Faz determinada Thread dormir por 1 segundo.
	 * @since 1.0 
	 */
	public void dormir(){
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
	public void resetarEstado(){
		Iterator<Vertice> iter = G.getV().iterator();
		Vertice u = null;
		while (iter.hasNext()){
			u = iter.next();
			u.setCor(null);
			u.setD(null);
			u.setF(null);
			u.getFigura().setCor("dgray");
			u.getFigura().setD(null);
			u.getFigura().setF(null);
		}
	}

	/**
	 * Agrupa todos os vertices em uma determinada posicao
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void agrupar(int x, int y){
		Iterator<Vertice> iter = G.getV().iterator();
		Vertice u = null;
		if(iter.hasNext()){
			while (iter.hasNext()){
				u = iter.next();
				u.setX(x);
				u.setY(y);
				u.getFigura().setX(x);
				u.getFigura().setY(y);
				//pane.repaint();
			}
		}
		else{
			//JOptionPane.showMessageDialog(frameDeControle,"Nao ha vertices para serem agrupados!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Ajusta um determinado vertice a uma posicao escolhida.
	 * @param nome String
	 * @param x int
	 * @param y int
	 * @since 1.0 
	 */
	public void ajustar(String nome, int x, int y){
		try{
			Vertice u = G.getVertice(nome);
			u.setX(x);
			u.setY(y);
			u.getFigura().setX(x);
			u.getFigura().setY(y);
		}
		catch (NullPointerException ex){
			JOptionPane.showMessageDialog(frameDeControle,"O vertice inserido não existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}
	}

	

	/**
	 * Gera um grafo aleatorio entre 5 e 25 vertices.
	 * @since 1.0 
	 */
	public void gerarAleatorio(){

		Random gerador = new Random();
		int i;
		int numeroV = gerador.nextInt(20) + 5;
		int numeroE = gerador.nextInt(numeroV*(numeroV-1)/2);

		for (i=0; i<=numeroV; i++)
			addVertice("v"+String.valueOf(i), gerador.nextInt(840), gerador.nextInt(480) + 10);

		for (i=0; i<=numeroE; i++){

			Aresta e = null;
			int primeiroVertice = gerador.nextInt(numeroV);
			int segundoVertice = gerador.nextInt(numeroV);
			try {
				e = G.getAresta("v" + String.valueOf(primeiroVertice), "v" + String.valueOf(segundoVertice));
			}
			catch(NullPointerException ex){
				addAresta("v" + String.valueOf(primeiroVertice), "v" + String.valueOf(segundoVertice), 0);
			}
			finally {
				if(e!=null) {}
			}
		}
	}

	/**
	 * Seta qual model.Grafo ira ser usado pela classe
	 * @param G model.Grafo
	 * @since 1.0 
	 */
	public void setGrafo(Grafo G){
		this.G = G;
	}

	/**
	 * Seta qual o view.Quadro vai ser usado pela classe
	 * @param pane model.Quadro
	 * @since 1.0 
	 */
	public void setQuadro(Quadro pane){
		this.pane = pane;
	}

	/**
	 * Retorna o Grafo que esta instanciado em GControl
	 * @deprecated
	 * @return G <code>model.Grafo</code>
	 * @since 1.0 
	 */
	public Grafo getGrafo(){
		return this.G;
	}

	/**
	 * Instancia novo grafo
	 * @deprecated
	 * @since 1.0 
	 */
	public void novoGrafo(){
		G = new Grafo();
	}

	/**
	 * Inicia todos os vertices com a cor branca, seu D nulo e seu predecessor nulo.
	 * @deprecated
	 * @param s model.Vertice
	 * @since 1.0 
	 */
	public void iniciar_fonte_unica(Vertice s) {
		resetarEstado();
		Iterator<Vertice> iter = G.getV().iterator();
		Vertice u = new Vertice();
		while (iter.hasNext()){
			u = iter.next();
			u.setCor("white");
			u.setD(null); // O parametro null está sendo usado como infinito.
			u.setPi(null);
			atualizar(u);
		}
		s.setD(0);
		atualizar(u);
	}

	/**
	 * Relaxar se o D de um vertice for maior que o D de seu predecessor + o peso da aresta.
	 * @deprecated
	 * @param s model.Aresta
	 * @since 1.0 
	 */
	public void relaxar(Aresta s) {
		if((s.getV().getD())>(s.getU().getD() + s.getPeso())){
			s.getV().setD(s.getU().getD() + s.getPeso());
			s.getV().setPi(s.getU());
		}
	}

	/**
	 * Algoritmo de Bellman_Ford
	 * @deprecated
	 * @param s String
	 * @return <code>boolean</code> 
	 * @since 1.0 
	 */
	public boolean bellman_ford(String s){
		try{
			iniciar_fonte_unica(G.getVertice(s));
			Iterator<Vertice> iter = G.getV().iterator();
			Iterator<Aresta> iter1 = G.getE().iterator();
			while (iter.hasNext()){
				iter.next();
				while (iter1.hasNext()){
					relaxar(iter1.next());
				}
			}
			Iterator<Aresta> iter2 = G.getE().iterator();
			Aresta e = null;
			while (iter2.hasNext()){
				e = iter2.next();
				if((e.getV().getD())>(e.getU().getD() + e.getPeso())){
					return false;
				}
			}
			return true;
		}
		catch (NullPointerException ex){
			return false;
			//JOptionPane.showMessageDialog(frameDeControle,"O vertice inserido não existe!","Ops! :(",JOptionPane.ERROR_MESSAGE);
		}

	}
}
