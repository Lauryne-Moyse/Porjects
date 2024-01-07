package vue;
import modèle.LabyrinthModel;
import java.awt.event.*;
import javax.swing.*;


public final class ItemResize extends JMenuItem implements ActionListener {
	
	
	// ### Attribut ###
	
	private final Labyrinth labyrinth;
	
	
	// ### Constructeur ###
	
	public ItemResize(final Labyrinth labyrinth) {
		
		super("Resize");
		this.labyrinth=labyrinth;
		addActionListener(this);
	}
	
	
	// ### Méthode ###
	
	/**
	 * @brief met à jour les dimensions du labyrinthe en fonction de ce qui est entré par l'utilisateur
	 * reset et lance une mise à jour du labyrinthe
	 * @see LabyrinthModel#reset
	 * @see Labyrinth#update
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		final LabyrinthModel model = labyrinth.getModel();
		final String ligneS = JOptionPane.showInputDialog(this,"Create your maze : Number of rows ?","Warning : resize will reset your maze",2);
		final String colonneS = JOptionPane.showInputDialog(this,"Create your maze : Number of columns?");
		int ligne; int colonne;
		
		try {
		ligne = Integer.parseInt(ligneS);
		colonne = Integer.parseInt(colonneS);
		model.setColonne(colonne);
		model.setLigne(ligne);
		
		model.reset();  // Le changement de dimension réinitialise le labyrinthe 
		model.setMessage("Create your own maze !");
		labyrinth.update();	
		
		
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this,"You must enter two integers >:(");  
		} 
	}

	
}
