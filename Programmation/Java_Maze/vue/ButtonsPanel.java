package vue;
import javax.swing.*;

import java.awt.*;


public final class ButtonsPanel extends JPanel {
	
	
	// ### Attributs ###
	
	private final ButtonArrival arrival;
	private final ButtonDeparture departure;
	private final ButtonEmpty empty;
	private final ButtonWall wall;
	private final ButtonSolve solve;
	private final ButtonReset reset;
	private final ChosenBox chosenBox;
	
	
	// ### Constructeur ###
	
	public ButtonsPanel(Labyrinth labyrinth) {
		
		super();
		setLayout(new GridLayout(1,7));
		add(chosenBox= new ChosenBox(labyrinth));
		add(departure = new ButtonDeparture(labyrinth));
		add(arrival = new ButtonArrival(labyrinth));
		add(empty = new ButtonEmpty(labyrinth));
		add(wall = new ButtonWall(labyrinth));
		add(solve = new ButtonSolve(labyrinth));
		add(reset = new ButtonReset(labyrinth));
		setPreferredSize(new Dimension (100,100));
	}
    
	
	// ### Méthode ###
	
	/** 
	 * @see ChosenBox#update
	 */
	public void update() {
		 chosenBox.update();
	 }
}
