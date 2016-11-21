package model;

import java.io.Serializable;
import java.util.*;

/**
 * Um implementacao de fila simples.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class Fila<No> implements Serializable {

	private static final long serialVersionUID = 1L;
	private LinkedList<No> f = new LinkedList<No>();
	
	public Fila() {}

	public int size() {
		return this.f.size();
	}
	
	public boolean isEmpty() {
		if(f.size() == 0)
			return true;
		else
			return false;
	}
	
	public No peek() {
		if(isEmpty())
			System.out.println("Fila Vazia");
		return f.getFirst();
	}
	
	public No remove() {
		if(isEmpty())
			System.out.println("Fila Vazia");
		No r = f.removeFirst();
		return r;
	}

	public void add(No x) {
		f.addLast(x);
	}
}