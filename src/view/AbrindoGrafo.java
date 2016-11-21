package view;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

//import view.GUI;
import model.*;

/**
 * Classe que implementa como abrir um arquivo.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class AbrindoGrafo {

	private GUI frameDeControle;

	/**
	 * Construtor AbrindoGrafo
	 * @since 1.0
	 */
	public AbrindoGrafo() {}

	/**
	 * Metodo para iniciar operação de abrir arquivo serializado 
	 * @return G model.Grafo
	 * @throws NullPointerException
	 * @since 1.0
	 */
	public Grafo iniciar() throws NullPointerException{

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Arquivo .graph", "graph"));    
		fc.setAcceptAllFileFilterUsed(false); 
		int c = fc.showOpenDialog(null);
		Grafo G = null;

		if(c == JFileChooser.APPROVE_OPTION){


			try { 
				String nm = fc.getSelectedFile().getAbsolutePath();
				FileInputStream fi = new FileInputStream(nm); 
				ObjectInputStream oi = new ObjectInputStream(fi); 
				Object G2;
				try {
					G2 = oi.readObject();
					oi.close(); 
					fi.close(); 
					G = (Grafo)G2;
					return G;
					
				} 
				catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(frameDeControle, "Classe não encontrada","Erro ao Abrir", JOptionPane.ERROR_MESSAGE);
				} 

				oi.close(); 
				fi.close(); 
			}
			catch (IOException e) { 
				e.printStackTrace();
				JOptionPane.showMessageDialog(frameDeControle, "O arquivo não é do tipo .graph ou está desatualizado.","Erro ao Abrir", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		throw new NullPointerException("NULO"); 
	}
}