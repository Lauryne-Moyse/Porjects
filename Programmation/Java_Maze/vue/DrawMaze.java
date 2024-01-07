package vue;
import java.awt.*;

import javax.swing.*;

import interfaces.*;
import mazeCode.MBox;
import modèle.*;

import java.util.*;


/**
 * <b> Un objet de DrawMaze est unJPanel contenant l'ensemble des cases du labyrinthe </b>
 * <p> L'attribut boxes contient l'ensemble des cases </p>
 * @see DrawBox
 */
public final class DrawMaze extends JPanel {
	
	
	// ### Attributs ###
	
	private final Labyrinth labyrinth; 
	private ArrayList<DrawBox> boxes = new ArrayList<DrawBox>();
	
	LabyrinthModel model;
	
	
	// ### Constructeur ###
	
	public DrawMaze(final Labyrinth labyrinth) {
		
		super();
		this.labyrinth = labyrinth;
		
		model = labyrinth.getModel();
		setLayout(new GridLayout(model.getLigne(),model.getColonne()));
		
		for(final VertexInterface vertex : model.getBoxes()) {
			 	final MBox box = (MBox) vertex;
				final int x = box.getx();
				final int y = box.gety();
	
				final DrawBox drawB = new DrawBox(labyrinth, x, y, vertex.getLabel());
				
				boxes.add(drawB);
				add(drawB);
		}
		
		setPreferredSize(new Dimension (500,500));
	}
	
	
	
	// ### Méthodes ###
	
	@Override
	public void paintComponent(Graphics g) {
		   
		   super.paintComponent(g);
		   for (final DrawBox box : boxes)
				box.update(); 
}	
	
	/**
	 * @brief vide le JPanel puis le re-remplie si les dimensions du labyrinthe ont été modifiées 
	 * dans tous les cas mise à jour de la vue 
	 * @see DrawBox#update 
	 */
	public void update() {   
		
		// Le changement de taille réinitialise le boxes du model, il faut donc faire de même avec le boxes de DrawMaze
		
		if (model.isDimension()) {
		
			model.setDimension(); 
			
			removeAll();
			
			setLayout(new GridLayout(model.getLigne(),model.getColonne()));
			
			
			boxes.clear();
			for(final VertexInterface vertex : model.getBoxes()) {
			 	final MBox box = (MBox) vertex;
				final int x = box.getx();
				final int y = box.gety();
	
				final DrawBox drawB = new DrawBox(labyrinth, x, y, vertex.getLabel());
				boxes.add(drawB);
				add(drawB); 
			}
		}
			
		revalidate();
		repaint();
	}
	
	
	
		
}

	

