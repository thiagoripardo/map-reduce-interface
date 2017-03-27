package readerwriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Graph;
import view.GUI;

/**
 * Classe que implementa como salvar um arquivo do tipo .graph
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */
public class GraphWriter {

	private Graph G = null;
	private GUI frameDeControle;

	/**
	 * Construtor GraphWriter
	 * @param G model.Graph
	 * @since 1.0
	 */
	public GraphWriter(Graph G) {
		this.G = G;
	}

	/**
	 * Metodo para iniciar operação de salvar arquivo serializado 
	 * @throws NullPointerException
	 * @since 1.0
	 */
	public void iniciar(){

		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("Arquivo .graph", "graph"));    
		fc.setAcceptAllFileFilterUsed(false); 
		int c = fc.showSaveDialog(frameDeControle);

		if(c == JFileChooser.APPROVE_OPTION){
			String nm = fc.getSelectedFile().getAbsolutePath();
			if(!nm.endsWith(".graph"))
				nm += ".graph";

			try { 
				FileOutputStream fo = new FileOutputStream(nm); 
				ObjectOutputStream ou = new ObjectOutputStream(fo); 
				ou.writeObject(G); 
				ou.close(); 
				fo.flush();
				fo.close(); 

			}
			catch (IOException e) { 
				JOptionPane.showMessageDialog(frameDeControle, "Permissão Negada!","Erro ao Salvar", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}