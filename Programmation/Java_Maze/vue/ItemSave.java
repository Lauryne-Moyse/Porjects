package vue;
import javax.swing.*;

import modèle.LabyrinthModel;

import java.awt.event.*;


public final class ItemSave extends JMenuItem implements ActionListener {
	
	
	// ### Attribut ###
	
    private final Labyrinth labyrinth;
    
    
    // ### Constructeur ###
    
	public ItemSave(final Labyrinth labyrinth) {
		super("Save");
		this.labyrinth=labyrinth;
		addActionListener(this);
		
	}

	
	// ### Méthode ###

	/**
	 * @brief lance un enregistrement du labyrinthe dans un fichier texte :
	 * demande le chemin vers le fichier et affiche une confirmation une fois l'enregistrement effectué
	 * @see LabyrinthModel#saveToTextFile
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
	
		final LabyrinthModel model = labyrinth.getModel();
	
		final String FileName = JOptionPane.showInputDialog(this,"Save your maze in a new file : PathName ?");
		model.saveToTextFile(FileName);
		model.setModified();
		JOptionPane.showMessageDialog(this,"Your maze has been saved !");
	}	
	
	
	
}	



