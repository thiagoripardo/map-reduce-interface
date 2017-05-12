package model;

import java.io.Serializable;
import java.util.*;

/**
 * Um implementacao de fila simples.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Queue<Node> implements Serializable {

	private static final long serialVersionUID = 1L;
	private LinkedList<Node> f = new LinkedList<Node>();
	
	public Queue() {}

	public int size() {
		return this.f.size();
	}
	
	public boolean isEmpty() {
		if(f.size() == 0)
			return true;
		else
			return false;
	}
	
	public Node peek() {
		if(isEmpty())
			System.out.println("Queue Vazia");
		return f.getFirst();
	}
	
	public Node remove() {
		if(isEmpty())
			System.out.println("Queue Vazia");
		Node r = f.removeFirst();
		return r;
	}

	public void add(Node x) {
		f.addLast(x);
	}
}