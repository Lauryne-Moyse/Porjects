package interfaces;

public interface ASetInterface {
	
	/**
	 * @brief ajouter un sommet	 
	 * @param vertex 
	 * sommet à ajouter 
	 */
	public void addVertex(VertexInterface vertex);
	
	
	/**
	 * @brief vérifier si un sommet appartient à A
	 * @param vertex 
	 * sommet considéré 
	 * @return booléen valant vrai si le sommet est dans A, faux sinon
	 */
	public boolean belong(VertexInterface vertex);
	
	
	 
}
