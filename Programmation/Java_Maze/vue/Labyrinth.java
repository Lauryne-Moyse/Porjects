package vue;
import javax.swing.*;
import modèle.LabyrinthModel;


/**
 * Un objet de Labyrinth affiche une certaine vue d'un LabyrinthModel
 * @see LabyrinthModel
 */
public final class Labyrinth extends JFrame  {

	
	// ### Attributs ###
	
	private final Window window ;
	private final MenuBar menuBar;
	private final LabyrinthModel model ;
	
	
	// ### Constructeur ###
	
	
	/**
	 * @brief crée un labyrinthe en fonction des dimensions rentrées par lutilisateur 
	 * Si deux entiers ne sont pas rentrés, cela entraîne la création par défaut d'un labyrinthe de taille (10,10)
	 * @see LabyrinthModel#LabyrinthModel
	 */
	public Labyrinth() {
		
		super("Maze");
		
		// Initilisation des dimensions 
		String ligneS = JOptionPane.showInputDialog(this,"Create your maze : Number of rows ?");
		String colonneS = JOptionPane.showInputDialog(this,"Create your maze : Number of columns?");
		int ligne=0; int colonne=0;
		
		try {
		ligne = Integer.parseInt(ligneS);
		colonne = Integer.parseInt(colonneS);
		
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this,"You must enter two integers >:( Creating a default maze : ");  
			
		} finally {
		
		model = new LabyrinthModel(ligne, colonne);
		window= new Window(this);
		setContentPane(window);
		menuBar = new MenuBar(this);
		setJMenuBar(menuBar);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);  }	
	}
	
	
	// ### Getters ###
	
	public LabyrinthModel getModel() {
		return model;
	}
	public Window getWindow() {
		return window;
	}
	

	// ### Méthode ###
	/**
	 * @see Window#update
	 */
	public void update() {
			window.update();
	}
	
}
