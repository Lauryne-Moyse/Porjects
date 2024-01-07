package modèle;
import interfaces.*;
import mazeCode.*;
import java.util.*;

/**
 * <b> LabyrinthModel représente l'état courant du labyrinthe </b>
 * <p>
 * Les états suivants sont considérés :
 * <ul>
 * <li> message : le message affiché à l'utilisateur </li>
 * <li> chosenBox : la case sélectionnée par l'utilisateur </li>
 * <li> boxes : les cases constituant le labyrinthe </li> 
 * <li> path : le chemin du labyrinthe 
 * <li> dimension : booléen indiquant si les dimensions du labyrinthe sont modifiées ou non </li> 
 * <li> modified : booléen indiquant si le labyrinthe est modifié ou non </li> 
 * <li> fileCorrect: booléen indiquant si le fichier à partir duquel l'utilisateur souhaite
 * initialiser le labyrinthe est correct ou non </li> 
 * <li> colonne : le nombre de colonnes du labyrinthe </li>
 * <li> ligne : le nombre de lignes du labyrinthe </li>
 * </ul>
 * </p>
 * @see Maze
 */
public final class LabyrinthModel {
	
	
	// ### Attributs ###
	
	private String message = "Welcome :) Create your maze and escape the cave !";
	private String chosenBox = "Wall";
	
	private ArrayList<VertexInterface> boxes = new ArrayList<VertexInterface>();
	private ArrayList<VertexInterface> path =  new ArrayList<VertexInterface>();
	
	private boolean dimension = false;  		// vaut true si les dimensions du labyrinthe sont modifiées
	private boolean modified = false;  		   // vaut true si le labyrinthe labyrinthe est modifié
	private boolean fileCorrect = true;       // vaut false s'il y une MazeReadingException dans le fichier avec lequel on veut initier le labyrinthe
	
	private int colonne=10;
	private int ligne=10;
	
	
	
	
	
	// ### Constructeur ###
	
	/**
	 * @brief initilisation de boxes avec seulement des murs
	 * la taille de boxes dépend de ligne et colonne qui doivent être strictement positifs 
	 */
	public LabyrinthModel(final int ligne,final int colonne) {
		
		if (ligne >0)
			this.ligne=ligne;
		if (colonne >0)
			this.colonne=colonne;
		
		for(int i = 0 ; i<this.ligne ; i++) {
			for(int j = 0 ; j< this.colonne ; j++) 
				boxes.add(new WBox(i,j));  }   // Labyrinthe initial constitué seulement de mur 
	}
	
	
	
	
	// ### Getters et Setters ###
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		if (this.message != message) {
		this.message = message;
		}
	}

	public String getChosenBox() {
		return chosenBox;
	}
	public void setChosenBox(String chosenBox) {
		if (this.chosenBox != chosenBox) {
		this.chosenBox = chosenBox;
	   } 
	}


	public ArrayList<VertexInterface> getBoxes() {
		return boxes;
	}
	public void modifBoxes(final int x, final int y, final char label) {
		
		if (label=='A') {
			boxes.set(y+x*colonne, new ABox(x,y)); }
		else if (label=='W') {
			boxes.set(y+x*colonne, new WBox(x,y)); }
		else if (label=='E') {
			boxes.set(y+x*colonne, new EBox(x,y)); }
		else if (label=='D') {
			boxes.set(y+x*colonne, new DBox(x,y)); }
		modified=true;
	}
	
	public ArrayList<VertexInterface> getPath() {
		return path;
	}
	public void setPath(ArrayList<VertexInterface> path) {
		this.path=path;
	}


	public boolean isDimension() {
		return dimension;
	}
	public void setDimension() {
		dimension=false;
	}
	public boolean isModified() {
		return modified;
	}
	public void setModified() {
		modified=false;
	}
	public boolean isCorrect() {
		return fileCorrect;
	}
	public void setCorrect() {
		fileCorrect=true;
	}
	
	
	public int getColonne() {
		return colonne;
	}
	public void setColonne(int colonne) {
		if (this.colonne != colonne & colonne>0) {  
		this.colonne = colonne;						
		dimension = true;
		}
	}
	public int getLigne() {
		return ligne;
	}
	public void setLigne(int ligne) {
		if (this.ligne != ligne & ligne>0) {
		this.ligne = ligne;
		dimension = true;
		}
	}

	
	
	
	// ### METHODES ###
	
	
	/**
	 * @brief réinitialise boxes avec des cases mur  
	 */
	public void reset() {

		boxes.clear();
		for(int i = 0 ; i<ligne ; i++) {
			for(int j = 0 ; j< colonne ; j++) 
				boxes.add(new WBox(i , j));
		}
		modified = false;    
	}
	
	
	/**
	 * @brief actualise path avec les cases constituant le chemin d'arrivée à départ 
	 * @see Maze#solveMaze
	 */
	public void solve()  {
		final Maze maze = new Maze(ligne, colonne);
		maze.setVertex(boxes);
		path = maze.solveMaze();
	}
	
	/**
	 * @brief enregistre boxes dans un fichier texte 
	 * @param fileName
	 * le chemin vers le fichier 
	 * @see Maze#saveToTextFile
	 */
	public void saveToTextFile(final String fileName) {
		final Maze maze = new Maze(ligne, colonne);
		maze.setVertex(boxes);
		maze.saveToTextFile(fileName);	
	}
	

	
	/**
	 * @brief initialise boxes dans à partir d'un fichier texte 
	 * @param fileName
	 * le chemin vers le fichier 
	 * @see Maze#initFromTextFile
	 */	
	public void initFromTextFile(final String fileName) {
		
		final Maze maze = new Maze(0,0);
	
		try {
			maze.initFromTextFile(fileName);
		} catch (MazeReadingException e) {
			System.out.println("Error : " + e);
		}
		
		if (maze.getVertex()!=null && !maze.getVertex().isEmpty()) {  //maze.getVertex() est null s'il y une MazeReadingException dans le fichier
			boxes = maze.getVertex();								//et il est vide si le fichier est vide
			
			ligne = maze.getLigne();
			colonne = maze.getColonne();
			
			dimension=true;
			modified=true;
		}
		
		else 
			fileCorrect=false;
	}



	
}






