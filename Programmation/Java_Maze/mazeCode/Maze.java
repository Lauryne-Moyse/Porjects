package mazeCode;
import java.io.*;
import java.util.*;

import ihmSimpleAffichage.Labrinth;
import interfaces.*;


/**
 * <b> Un objet de Maze représente un labyrinthe </b>
 * <p>
 * Celui est défini par :
 * <ul>
 * <li> boxes : la liste des cases du labyrinthe </li>
 * <li> ligne : le nombre de lignes du labyrinthe </li>
 * <li> colonne : le nombre de colonnes du labyrinthe </li> 
 * </ul>
 * </p>
 */
public final class Maze implements GraphInterface {

	
	// ### Attributs ###
	
	private ArrayList<VertexInterface> boxes;
	private int ligne;
	private int colonne;
    
   
	// ### Constructeur ###
	
	public Maze(int ligne, int colonne) {
    	boxes = new ArrayList<VertexInterface>();  
    	this.ligne=ligne;
    	this.colonne=colonne;  }
    
	
	
	// ### Getters et setters ###
	
    public int getColonne() {
    	return colonne;
    }
    public int getLigne() {
    	return ligne;
    }
    
	public ArrayList<VertexInterface> getVertex() {
		   return boxes ;   
	}
    public void setVertex(ArrayList<VertexInterface> boxes) {
		   this.boxes = boxes; ;   
	}

	
    public int getOrdre() {
		return ligne*colonne;
	}

    
    
    // ### Méthodes ###
    
    
    
    @Override
	public int getValue(final VertexInterface a, final VertexInterface b) {
	    
		if  (this.getSuccessors(a).contains(b)) 
	    	return 1;
		else 
			return 0;   
		}
	
    /**
	 * @brief cherche le sommet de départ  
	 * @throws MazeReadingException si le nombre de cases départ est différent de un
	 * @return le sommet de départ 
	 */
	public VertexInterface getDeparture() throws MazeReadingException {
		
		int count =0;
		VertexInterface departure = null;
		for (final VertexInterface vertex : boxes) {
			if (vertex.getLabel()=='D') {
				departure=vertex;
				count++;				
			}
		} 
		
		if (count!=1) 
			 throw new MazeReadingException("Labyrinthe non conforme : il y a " + count + " case(s) départ"); 
			
		return departure;
	}
	
	
	/**
	 * @brief cherche le sommet d'arrivée
	 * @throws MazeReadingException si le nombre de cases arrivée est différent de un
	 * @return le sommet d'arrivée
	 */
	public VertexInterface getArrival() throws MazeReadingException {
		
		int count = 0;
		VertexInterface arrival = null;
		for (final VertexInterface vertex : boxes) {
			if (vertex.getLabel()=='A') {
				count++;
				arrival=vertex;		
			}
		}
		
		if (count!=1) 
			 throw new MazeReadingException("Labyrinthe non conforme : il y a " + count + " case(s) arrivée"); 
		
		return arrival;
	}
	
	
	
	/**
	 * @brief Permet d'obtenir les cases voisines d'une case
	 * Une case de coordonnées (x,y) a pour indice dans boxes y+x*(colonne) et il faut veiller à ne pas dépasser les dimensions du labyrinthe 
	 * @param vertex 
	 * case considérée
	 * @return  
	 */
	public ArrayList<Integer> getVoisines(final VertexInterface vertex) {
		
		final MBox box = (MBox) vertex;
	    final int x = box.getx();
		final int y = box.gety(); 
		
		ArrayList<Integer> indexVoisines = new ArrayList<Integer>();
		if (x-1>=0)
			indexVoisines.add(y+(x-1)*(colonne));
		if (x+1<ligne)
			 indexVoisines.add(y+(x+1)*(colonne));
		if (y-1>=0)
			 indexVoisines.add((y-1)+x*(colonne));
		if (y+1<colonne)
			 indexVoisines.add((y+1)+x*(colonne));
		
		return indexVoisines;
	}
	
			
	@Override
	public ArrayList<VertexInterface> getSuccessors(final VertexInterface vertex) {
		 
		final ArrayList<Integer> indexVoisines = this.getVoisines(vertex);
		
		ArrayList<VertexInterface> successors = new ArrayList<VertexInterface>();
		
		for (final Integer index : indexVoisines)
			if (this.boxes.get(index).getLabel()=='E' || this.boxes.get(index).getLabel()=='A')
			   successors.add(this.boxes.get(index));
	
		return successors;
		}
	
	
	
	/**
	 * @brief initialise un labyrinthe à partir d'un fichier texte  
	 * @param fileName
	 * le chemin vers le fichier  
	 * @throws MazeReadingException si le fichier ne correpond pas à un labyrinthe
	 * (caractères ne correspondant à aucune case ou lignes de longueur différentes) 
	 */
	public void initFromTextFile(final String fileName) throws MazeReadingException {
		
		BufferedReader in = null;
	
		try { 
	
		 in = new BufferedReader(new FileReader(fileName));
		 String line = null;
		 
		 boolean FirstLine = true; 
		 int longueurInitiale = 0; int longueur = 0; int count = 0;
		 
		 while (in.ready()) {
			 
			 line=in.readLine(); 			 // On vérifie si toutes les lignes du fichier ont la même longueur
			 if (FirstLine) {				// La longueur de la première ligne sert de référence 
				 longueurInitiale=line.length(); 
				 FirstLine = false;    }
			 
			 else {
				 	longueur = line.length();
				    if (longueur != longueurInitiale) {
		                boxes =null;
				    	throw new MazeReadingException(fileName, count+1, "Ligne de longueurs différentes");  
				    }
			 }
				 
					
			 for (int index = 0; index<longueurInitiale; index++) {
				  
				 final char lettre = line.charAt(index);
						 
				 if (lettre=='A')
							 boxes.add(new ABox(count, index));
				 else if (lettre=='E')
							 boxes.add(new EBox(count, index));
				 else if (lettre=='D')
							 boxes.add(new DBox(count, index));
				 else if (lettre=='W')
							 boxes.add(new WBox(count, index));
				 else {
					 boxes = null;
					 throw new MazeReadingException(fileName, count+1, index+1, "Case inconnue");		
					}
			}
			
		     count++;	
		 } 
		 
		 
		ligne=count;     // Mise à jour des dimensions du labyrinthe
		colonne=longueurInitiale;
			 		
		 } catch (Exception e) { System.out.println("Error :" + e); }
		   finally  {
					try { in.close(); } 
					catch (Exception e) {}
			}
	} 

	
	 
	 
	/**
	 * @brief enregistre un labyrinthe dans un fichier texte 
	 * cela crée un nouveau fichier ou en écrase un préexistant 
	 * @param fileName
	 * le chemin vers le fichier
	 */
	 public void saveToTextFile(final String fileName) {
		 
		 File file = new File(fileName);
		 PrintWriter writer = null;
		 
		 
		 try {
			 
			 writer = new PrintWriter (file);
			 int count = 0;
			 
			  for (final VertexInterface vertex : boxes) {
				
				if (count==colonne) {     // On revient à la ligne une fois le bon nombre de colonne atteint
					   count=0;
					   writer.println(); 
				}
			 
				char label = vertex.getLabel();
				writer.append(label);
				count++;
			  }
			 
		} catch(Exception e) {
			    System.out.println("Error : " + e); 
		} finally {
			    writer.close(); 
			    }
	 }

	

	 /**
	 * @brief résout le labyrinthe avec la méthode de Dijkstra
	 * @see Dijkstra#dijkstra  
	 * @return ArrayList des cases constituant le chemin de départ à arrivée 
	 */
	 public ArrayList<VertexInterface> solveMaze() {
		 
		 ArrayList<VertexInterface> path = null;
		 
		 try {
			 
			Previous previous = (Previous) Dijkstra.dijkstra(this, this.getDeparture());
		   
		    VertexInterface departure = this.getArrival();
		    path = new ArrayList<VertexInterface>();

		    // Construction d'un chemin ne contenant pas A et D
		    while (departure.getLabel()!='D') {
			    departure = previous.getPrevious(departure);
			    if(departure.getLabel()!='D')
			    	path.add(departure); 
			   } 
		    
		 } catch (MazeReadingException e) {
			 System.out.println(e);
			 
		 } catch (Exception e) {
			 System.out.println("Ce labyrinthe n'a pas de solution"); // Cette exception survient quand un méthode getLabel 
		 }															 // est demandée à une case null ou inconnue 
		 															// Ce qui se produit quand aucun chemin n'est possible
		return path;
		}
	 
	 
	 
	 /*
	 public void displayMaze() {
		 try {
		ArrayList<VertexInterface> path = this.solveMaze();
		new Labrinth(boxes, path, this.getLigne(), this.getColonne());  
		
		 } catch (Exception e) {}
		
	 } */
	 
	 
}
