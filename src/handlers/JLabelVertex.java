package handlers;

import javax.swing.JLabel;

import model.Vertex;

public class JLabelVertex extends JLabel {

	private static final long serialVersionUID = 1L;
	private Vertex vertex;
	
	public JLabelVertex(Vertex v){
		setVertex(v);
	}

	public Vertex getVertex() {
		return vertex;
	}

	public void setVertex(Vertex v) {
		this.vertex = v;
		this.setText(this.vertex.getName());
	}
}
