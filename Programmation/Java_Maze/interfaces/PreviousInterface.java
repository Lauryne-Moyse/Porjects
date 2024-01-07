package interfaces;


public interface PreviousInterface {
	
	/**
	 * @brief obtenir l'identité du prédecesseur d'un sommet
	 * @param vertex
	 * sommet dont on veut connaître le prédecesseur
	 * @return sommet prédecesseur
	 */
	public VertexInterface getPrevious(VertexInterface vertex);
	
	
	 /**
	 * @brief définir/modifier l'identité du prédecesseur d'un sommet
	 * @param son et father
	 * sommet dont on veut mettre à jour le prédecesseur et le prédecesseur
	 * @return 
	 */
    public void setPrevious(VertexInterface father, VertexInterface son);
 
	
}
