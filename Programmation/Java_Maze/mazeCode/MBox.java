package mazeCode;
import interfaces.*;

/**
 * <b> Un objet de la classe MBox représente une case du labyrinthe </b>
 * <p> Cette classe est abstraite et se décline en quatre types de cases :
 * arrivée, départ, mur et vide <p/>
 */
public abstract class MBox implements VertexInterface {
	
	
	   // ### Attributs ###
	
       private final int x;
       private final int y;
       private final char label;
       
       
       // ### Constructeur ###
       
       public MBox(int x, int y, char label) {
    		this.x = x;
    		this.y = y;
    		this.label = label;}
       
      
       // ### Getters ###
       
       public final int getx() {
    	   return x;  }
       
       
       public final int gety() {
    	   return y;  }
    
       
       public final char getLabel() {
    	   return label; }
    	  
    	   
}
	



