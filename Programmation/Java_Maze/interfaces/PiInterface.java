package interfaces;
import java.lang.Integer;

public interface PiInterface {
	
	/**
	 * @brief mise à jour de la valeur de pi d'un sommet
	 * @param vertex
	 * sommet considéré, i : la nouvelle valeur de distance 
	 */
	public void setDistance(VertexInterface vertex, Integer i);
	
	
	/**
	 * @brief obtenir la valeur de pi d'un sommet
	 * @param vertex 
	 * sommet considéré 
	 * @return entier étant la valeur de pi du sommet
	 */
	public int getDistance(VertexInterface vertex);
	
	
	
	/**
	 * @brief obtenir le sommet non dans A de pi minimum   
	 * @return sommet qui est le prochain pivot 
	 */
	public VertexInterface minimum();
	
	
	
}
 