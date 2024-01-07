package vue;
import javax.swing.*;

import modèle.LabyrinthModel;

import java.awt.Graphics;
import java.awt.event.*;


public final class ButtonReset extends JButton implements ActionListener {
	
	
	// ### Attributs ###
	
	private final Labyrinth labyrinth;
	
	
	// ### Constructeur ###
	
	public ButtonReset(final Labyrinth labyrinth) {
		
		super("RESET");
		this.labyrinth=labyrinth;
		addActionListener(this);
	}
	
	
	// ### Méthodes ###
	
	/**
	 * @brief lance un reset du modèle et demande au labyrinthe de se mettre à jour 
	 * toutes les cases redeviennents des murs et le message change 
	 * @see LabyrinthModel#reset
	 * @see Labyrinth#update
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if (labyrinth.getModel().isModified()) {
			
			final int response = JOptionPane.showInternalConfirmDialog(labyrinth.getWindow(), "Do you really want to reset ?", "Reset ?", 0);
		
			if (response == 1)  
				return; 
		}
		
		final LabyrinthModel model = labyrinth.getModel();
		model.reset();
		model.setMessage("Create your own maze and escape the cave !");
		labyrinth.update();
	}
	
	
	
}
