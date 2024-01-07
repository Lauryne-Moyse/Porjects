package mazeCode;
import java.util.Hashtable;

import interfaces.*;


/**
 * Un objet de la classe Previous est une liste des sommets d'un graphe auxquels 
 * on a associé leur père déterminé par l'algorithme de Dijkstra 
 *  @see Hashtable
 */
public final class Previous implements PreviousInterface {

	
	// ### Attribut ###
	
	
	private Hashtable<VertexInterface, VertexInterface> previous;  // La clé est un sommet et la valeur est son père 
	
	
	// ### Constructeur ###
	
	public Previous() {
		previous = new Hashtable<VertexInterface, VertexInterface>();
    }
	
	
	// ### Getters et setters ###
	
	public Hashtable<VertexInterface, VertexInterface> getPere() {
		return previous;
	}
	public void setPrevious(final VertexInterface son, final VertexInterface father) {  
			previous.put(son, father);
	}
	
	
	// ### Méthode ###	
		
	/**
	 * @brief obtenir le prédecesseur d'un sommet déterminé par l'algorithme de Dijkstra
	 * @param vertex 
	 * sommet considéré
	 * @return le père du sommet 
	 */
	public VertexInterface getPrevious(final VertexInterface vertex)  {
		return previous.get(vertex);
	}

	
	
	
	
	
	

}
