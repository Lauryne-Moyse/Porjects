package interfaces;
import java.util.*;
import mazeCode.*;


public final class Dijkstra {

	
	/**
	 * @brief réalisation de l'algorithme de plus court chemin de Dikstra 
	 * @param vertex 
	 *  sommet à ajouter 
	 * @return
	 */
	public static PreviousInterface dijkstra(final GraphInterface g, final VertexInterface r) {
		
		
		ASet A = new ASet();
		Pi pi = new Pi();
		PreviousInterface previous = new Previous();
		VertexInterface pivot = r;
		final int n = g.getOrdre();

		
		// Initialisation des valeurs de pi et de l'ensemble A
		// La valeur de pi doit être un Integer, pour l'initialiser à l'infini on met donc une valeur grande devant l'ordre du graphe
		ArrayList<VertexInterface> graphe = g.getVertex();
		for (VertexInterface vertex : graphe) {
			pi.initialize(vertex, 10000*n);
		}
		pi.setDistance(r, 0);
		A.addVertex(r);
		
		
		for (int j = 0; j<n; j++) {
			
			// Mise à jour des valeurs pi et du père de tous les successeurs de pivot non dans A 
			final ArrayList<VertexInterface> successors = g.getSuccessors(pivot);
			for (VertexInterface vertex : successors) {
				
				if (!A.belong(vertex) && (pi.getDistance(pivot) + g.getValue(pivot,vertex)) < pi.getDistance(vertex)) {
					
						pi.setDistance(vertex, pi.getDistance(pivot) + g.getValue(pivot,vertex));
						previous.setPrevious(vertex, pivot);																		
				}
			} 
				
			
			// Une fois la valeur de pi(pivot) utilisée on la met à 0 
			// Cela permet de trouver le sommet de pi minimal NON dans A 
			// En effet, Minimum ne renvoie pas de sommet de pi=0
			pi.setDistance(pivot, 0);
			pivot = pi.minimum();
			A.addVertex(pivot);
		}
		
		return previous;
	} 
	
	
	

}