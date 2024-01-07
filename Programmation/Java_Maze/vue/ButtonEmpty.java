package vue;
import javax.swing.*;

import modèle.LabyrinthModel;

import java.awt.Graphics;
import java.awt.event.*;


public final class ButtonEmpty extends JButton implements ActionListener {
	
	
	// ### Attributs ###
	
	private final Labyrinth labyrinth;
	ChosenBox chosen;
	
	
	// ### COnstructeur ###
	
	public ButtonEmpty(final Labyrinth labyrinth) {
		
		super();
		this.labyrinth=labyrinth;
		addActionListener(this);
	}

	
	// ### Méthodes ###
	
	/**
	 * @brief met à jour chosenBox dans le modèle et demande à ButtonsPanel de se mettre à jour 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		final LabyrinthModel model = labyrinth.getModel();
		model.setChosenBox("Empty");
		labyrinth.getWindow().getButtons().update();
	}
	
	@Override
	protected final void paintComponent(Graphics g) {
	
	   super.paintComponent(g) ;
	   g.drawImage(Picture.empty, 0,0,getWidth(),getHeight(),this);
	}

}
