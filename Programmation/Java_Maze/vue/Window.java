package vue;

import java.awt.*;

import javax.swing.JPanel;

import modèle.LabyrinthModel;


public final class Window extends JPanel {

	
	// ### Attributs ###
	
	private final DrawMaze drawing;
	private final ButtonsPanel buttons;
	private final Dialog dialog;
	
	
	// ### Constructeur ###
	
	public Window(final Labyrinth labyrinth) {
		
		super();
		setLayout(new BorderLayout());
		add(dialog = new Dialog(labyrinth), BorderLayout.NORTH);
		add(drawing = new DrawMaze(labyrinth), BorderLayout.CENTER);
		add(buttons = new ButtonsPanel(labyrinth), BorderLayout.SOUTH);
	}
	
	
	// ### Getters ###
	public ButtonsPanel getButtons() {
		return buttons;
	}
	
	public Dialog getDialog() {
		return dialog;
	}
	
	
	// ### Méthode ###
	
	/**
	 * @see DrawMaze#update
	 * @see Dialog#update 
	 */
	public void update()  {
		drawing.update();
		dialog.update();
	}  
	
}
