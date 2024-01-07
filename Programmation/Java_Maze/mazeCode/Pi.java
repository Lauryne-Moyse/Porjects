package mazeCode;
import java.util.*;

import interfaces.*;

import java.lang.Integer;


/**
 * Un objet de la classe Previous est une liste des sommets d'un graphe auxquels
 * on a associé une distance déterminée par l'algorithme de Dijkstra
 *  @see Hashtable
 */
public final class Pi implements PiInterface {
	
	
	// ### Attribut ###
	
	private Hashtable<VertexInterface, Integer> pi;  // La clé est un sommet et la valeur son pi 
	

	// ### Constructeur ###
	
	public Pi() {
		pi = new Hashtable<VertexInterface, Integer>();
	}
	
	
	// ### Getters and setters ###
	
	public Hashtable<VertexInterface, Integer> getPi() {
	    	return pi; 
	    }
	 
	
	public void initialize(final VertexInterface vertex, final Integer i) {
		  pi.put(vertex, i); 										// Ici on une nouvelle clé avec sa valeur
	}
	public void setDistance(final VertexInterface vertex, final Integer i) {
		pi.replace(vertex, i);									  // Et là on mofiie la valeur d'une clé déjà ajoutée
	}
	public int getDistance(final VertexInterface vertex) {
		return pi.get(vertex);
	}

	
	// ### Méthode ###
	
	
	/**
	 * @brief cherche le sommet non dans A de pi minimal  
	 * @return le sommet trouvé qui est le prochain pivot 
	 */
	public VertexInterface minimum() {
		
		final Set<Map.Entry<VertexInterface,Integer>> temp= pi.entrySet();
		Map.Entry<VertexInterface,Integer> min = null;
		int piMin= 0;
	    // Impossible d'utiliser getValue si min vaut null (et je ne sais pas comment l'initialiser autrement)
	   // Je passe donc par un entier piMin qui vaut min.getValue()
		
		
		// On retourne forcément un sommet de pi!=0, ça ne peut donc pas être la racine 
		for (final Map.Entry<VertexInterface,Integer> couple : temp) {
			if (piMin==0 || (piMin>couple.getValue().intValue() && couple.getValue().intValue()>0)) {
				piMin=couple.getValue().intValue();
				min = couple;
			}
		}
		
		return min.getKey();
	}

	
}
