package view;

import java.awt.EventQueue;
import javax.swing.UIManager;

/**
 * Esta eh a classe Main, no qual eh setado o LookAndFeel do programa e eh iniciado a janela do mesmo.
 * 
 * @author Thiago Ripardo.
 * @version 1.0
 */

public class Main {

	public static void main(String args[]) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} 

		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		final GUI frame = new GUIGrafo();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				frame.showGUI();
			}
		});
	}
}
