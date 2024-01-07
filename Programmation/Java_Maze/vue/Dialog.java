package vue;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

import modèle.LabyrinthModel;


/**
 * Un objet de Dilog affiche en permanence un message à destination de l'utilisateur 
 */
public final class Dialog extends JPanel {
	
	
	// ### Attributs ###
	
	private String message;
	LabyrinthModel model;
	
	
	// ### Constructeur ###
	
	public Dialog(final Labyrinth labyrinth) {
		
		super();
		model=labyrinth.getModel();
		setPreferredSize(new Dimension (50,50));
	}
	
	
	// ### Méthodes ###
	
	@Override
	public void paintComponent(Graphics g) {
		
		message =  model.getMessage();
		g.drawString(message,(int)(getWidth()/2.5),(int)(getHeight()/1.7));
	}
	
	/**
	 * @see #paintComponent
	 */
	public void update() {
		repaint();
	}
}
