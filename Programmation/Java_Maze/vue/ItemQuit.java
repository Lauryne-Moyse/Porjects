package vue;
import javax.swing.*;

import modèle.LabyrinthModel;

import java.awt.event.*;

public class ItemQuit extends JMenuItem implements ActionListener {
	
	// ### Attribut ###
	
	private Labyrinth labyrinth;
	
	// ### Constructeur ###
	
	public ItemQuit(Labyrinth labyrinth) {
		super("Quit");
		this.labyrinth=labyrinth;
		addActionListener(this);
		
	}
	
	
	// ### Méthode ###
	
	/**
	 * @brief si isModified du modèle est true, demande confirmation à l'utilisateur avant de quitter 
	 */
	@Override 
	public void actionPerformed(ActionEvent e) {
		
		if (labyrinth.getModel().isModified()) {
			int response = JOptionPane.showInternalConfirmDialog(labyrinth.getWindow(), "Do you really want to quit without saving?", "Don't leave :(", 0);
		
			if (response == 1) 
				return;
		}
		
		System.exit(0);
	}
}

