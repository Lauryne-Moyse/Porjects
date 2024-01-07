package vue;

import java.awt.*;
import javax.swing.*;

import modèle.*;

/**
 * Un objet de ChosenBox écrit le nom du type de case sélectionné par l'utilisateur 
 */
public final class ChosenBox extends JPanel {

	
	// ### Attributs ###
	
	private final Labyrinth labyrinth;
	private String chosenBox ;
	
	
	// ### Constructeur ###
	
	public ChosenBox(final Labyrinth labyrinth) {
		
		super();
		this.labyrinth = labyrinth ;
		this.chosenBox = labyrinth.getModel().getChosenBox();
	}

	
	// ### Méthodes ###
	
	@Override
	protected final void paintComponent(Graphics g) {
	
	   super.paintComponent(g) ;

	  
	   chosenBox = labyrinth.getModel().getChosenBox();
	   g.drawString(chosenBox, (int)(getWidth()/2.5),(int)(getHeight()/1.7));     
	} 
	
	/**
	*@see #paintComponent 
	*/
	public void update() {
			repaint();
	}
	
	
	
}
