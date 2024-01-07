package interfaces;

import java.util.ArrayList;


public interface GraphInterface {
	
	/**
	 * @brief obtenir la liste des sommets du graphe
	 * @return ArrayList des sommets
	 */
	public ArrayList<VertexInterface> getVertex();
	
	
	/**
	 * @brief obtenir la liste des successeurs d'un sommet 
	 * @param vertex 
	 * sommet dont on veut connaître les successeurs  
	 * @return ArrayList de ses successeurs 
	 */
	public ArrayList<VertexInterface> getSuccessors(VertexInterface vertex);

	
	/**
	 * @brief obtenir le poids de l'arc de a vers b 
	 * @param a 
	 * @param b
	 * sommets définissant l'arc 
	 * @return entier représentant la valeur de l'arc
	 */
	public int getValue(VertexInterface a, VertexInterface b);
	
	/**
	 * @brief obtenir l'ordre du graphe (nombre de sommets)
	 * @return entier valant l'ordre de g 
	 */
	public int getOrdre();

	

}
