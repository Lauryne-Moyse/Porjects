package vue;
import javax.swing.*;

import modèle.LabyrinthModel;

import java.awt.event.*;


public final class ItemInit extends JMenuItem implements ActionListener {
	
	
	// ### Attribut ###
	
	private final Labyrinth labyrinth;
	
	
	// ### Constructeur ###
	
	public ItemInit(final Labyrinth labyrinth) {
		super("Init");
		this.labyrinth=labyrinth;
		addActionListener(this);
		
	}

	
	// ### Méthode ###

	/**
	 * @brief initialise le labyrinthe à partir d'un fichier texte si isCorrect du modèle vaut true
	 * demande le chemin vers le fichier et affiche un message d'avertissement si le fichier n'est pas correct 
	 * @see LabyrinthModel#initFromTextFile
	 * @see Labyrinth#update 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
	
		final LabyrinthModel model = labyrinth.getModel();
	
		final String FileName = JOptionPane.showInputDialog(this,"Create your maze from a file : PathName ?");
	
		model.initFromTextFile(FileName);
	
		if (model.isCorrect()) 
			labyrinth.update();
	
		else {
			model.setCorrect();
			JOptionPane.showMessageDialog(this,"You need an existing and non-emty file; Chars allowed : "
				+ "'A', 'D', 'E', 'W'; Rows of the same length", "Verify the validity of your file ", 0);
		}
	}
	
	
}
