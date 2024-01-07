package vue;
import javax.swing.*;

import interfaces.VertexInterface;
import modèle.LabyrinthModel;

import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * <b> Un objet de DrawBox représente une case du labyrinthe </b>
 * <p>
 * Celui est majoritairement défini par :
 * <ul>
 * <li> x : son numéro de ligne </li>
 * <li> y : son numéro de colonne </li>
 * <li> label : son nom qui détermine son type </li> 
 * </ul>
 * </p>
 * @see drawMaze 
 */
public final class DrawBox extends JPanel implements MouseListener {

	
	// ### Attributs ###
	
	private final Labyrinth labyrinth; 
	private final int x; 
	private final int y;
	private char label;
	LabyrinthModel model;
	
	
	// ### Constructeur ###
	
	public DrawBox(final Labyrinth labyrinth, final int x, final int y, final char label) {
		
		super();
		this.labyrinth = labyrinth;
		this.x =x;
		this.y=y;
		this.label = label;
		addMouseListener(this);
		}
	
	
	// ### Méthodes ###
	
	
	@Override
	public void paintComponent(Graphics g) {
	   
		
		
	   super.paintComponent(g) ;
	   

	   if(label =='A') {
		    g.drawImage(Picture.arrival, 0,0,getWidth(),getHeight(),this);
		    //g.drawString("ARRIVAL", (int)(getWidth()/3.5),(int)(getHeight()/1.7));
	   }
	   
	   else if (label == 'D') {
		   	g.drawImage(Picture.departure, 0,0,getWidth(),getHeight(),this);
		    //g.drawString("DEPARTURE", (int)(getWidth()/4.5),(int)(getHeight()/1.2));
	   }
	   
 	   else if (label == 'E')
			g.drawImage(Picture.empty, 0,0,getWidth(),getHeight(),this); 
	   
	   else if (label == 'W') 
			g.drawImage(Picture.wall, 0,0,getWidth(),getHeight(),this);  
	   
	   else 
			g.drawImage(Picture.path, 0,0,getWidth(),getHeight(),this);  
	   
	} 
	
	
	/**
	 * @brief si la case correpondant à this dans le modèle appartient à path, this change son label
	 * sinon this met à jour son label à partir du modèle 
	 * dans tous les cas, this se peint selon son label 
	 * @see #paintComponent
	 */
	public void update() {
		
		
		model = labyrinth.getModel();
		final ArrayList<VertexInterface> boxes = model.getBoxes();
		final int colonne = model.getColonne();
		
		if (model.getPath().contains(boxes.get(y+x*colonne)))
			label = 'P';
		
		else {
			label = boxes.get(y+x*colonne).getLabel(); }
		repaint();
	} 
	
	
	
	/**
	 * @brief this modifie boxes du modèle en fonction de la valeur de chosenBox puis se met à jour 
	 * @see #update
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
			
		model = labyrinth.getModel();
		final String chosen = model.getChosenBox();
		if (label !=chosen.charAt(0)) {
				model.modifBoxes(x, y, chosen.charAt(0)); 
				update();
		}
	}

	
		@Override
		public void mousePressed(MouseEvent e) {}
		@Override
		public void mouseReleased(MouseEvent e) {}
		@Override
		public void mouseEntered(MouseEvent e) {}
		@Override
		public void mouseExited(MouseEvent e) {}
		
  
}
