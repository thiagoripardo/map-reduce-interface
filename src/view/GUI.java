package view;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;


/**
 * Classe GUI estende JFrame e serve de base para a criacao de novas janelas.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */

public class GUI extends JFrame {

	private static final long serialVersionUID = 1L;

	/**
	 * Construtor GUI
	 * @since 1.0 
	 */
	public GUI() throws HeadlessException {
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Construtor GUI
	 * @param gc
	 * @since 1.0 
	 */
	public GUI(GraphicsConfiguration gc) {
		super(gc);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Construtor GUI
	 * @param title
	 * @since 1.0 
	 */
	public GUI(String title) throws HeadlessException {
		super(title);
		setSize(800,550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Construtor GUI
	 * @param title
	 * @param gc
	 * @since 1.0 
	 */
	public GUI(String title, GraphicsConfiguration gc) {
		super(title, gc);
		setSize(800,550);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Metodo para mostrar GUI
	 * @since 1.0 
	 */
	public void showGUI() {
		this.setVisible(true);
	}

	/**
	 * Metodo para esconder GUI
	 * @since 1.0 
	 */
	public void rideGUI() {
		this.setVisible(false);
	}
}