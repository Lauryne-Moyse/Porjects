package vue;
import javax.swing.*;
import modèle.LabyrinthModel;
import java.awt.event.*;
import java.util.*;
import interfaces.*;


public final class ButtonSolve extends JButton implements ActionListener {
	
	
	// ### Attributs ###
	
	private final Labyrinth labyrinth;
	
	
	// ### Constructeurs###
	
	public ButtonSolve(final Labyrinth labyrinth) {
		
		super("SOLVE");
		this.labyrinth=labyrinth;
		addActionListener(this);
	}

	
	// ### Méthodes ###
	
	/**
	 * @brief lance une résolution du labyrinthe dans le modèle et demande au labyrinthe de se mettre à jour 
	 * le chemin s'affiche et le message change 
	 * @see LabyrinthModel#solve
	 * @see Labyrinth#update
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		final LabyrinthModel model = labyrinth.getModel();
		model.solve();	
		ArrayList<VertexInterface> path = model.getPath();
		
		if (path!=null) {		
			
			if (!path.isEmpty()) {
				model.setMessage("Congratulations you're out ! You can play again.");
				labyrinth.update();
			}
			
			else {						// path est vide quand il n'y a pa de solution au labyrinthe 
				model.setMessage("This maze has no solution :(");
				labyrinth.update();
			}
		}
		
		else {						// path est null le labyrinthe a un problème (nombre d'arrivées/départs incorrect)
			model.setMessage("1 departure and 1 arrival required.");
			labyrinth.getWindow().getDialog().update();
			model.setPath(new ArrayList<VertexInterface>());
			//Cela permet de ne pas avoir d'erreur quand path est null et qu'on utilise la méthode contain
		   // Cette méthode est utilisé à chaque labyrinth.update()
		}
	}
	
	
}
