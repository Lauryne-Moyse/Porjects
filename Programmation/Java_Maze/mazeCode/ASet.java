package mazeCode;
import java.util.*;

import interfaces.*;


/**
 * Un objet de la classe ASet représente un ensemble A des sommets dont le père a été initialisé 
 * par l'algorithme de Dijkstra
 * @see HashSet
 */
public final class ASet implements ASetInterface {

	
	// ### Attribut ###
	
	private HashSet<VertexInterface> A;

	
	// ### Constructeur ###
	
    public ASet() {
    	A = new HashSet<VertexInterface>();
    }
    
    
    // ### Getter et setter ###
    public  HashSet<VertexInterface> getA(){
		return A; 
	}
    public void addVertex(final VertexInterface vertex) {
		A.add(vertex);
		}

	
    // ### Méthode ###
    
    /**
	 * @brief vérifier l'appartenance d'un sommet à l'ensemble A 	 
	 * @param vertex 
	 * sommet considéré
	 * @return un booléen indiquant cette appartenance 
	 */
	public boolean belong(final VertexInterface vertex) {
		return A.contains(vertex);
	}
	
	

}
