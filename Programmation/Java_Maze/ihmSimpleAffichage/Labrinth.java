package ihmSimpleAffichage;
import java.util.*;
import javax.swing.*;

import interfaces.*;

// Simple affichage à l'écran de la résolution d'un labyrinthe initialisé à partir d'un fichier texte 

public class Labrinth extends JFrame {

	
	private final WindowPanel windowPanel;
	
	
	public Labrinth(ArrayList<VertexInterface> boxes,ArrayList<VertexInterface> path, final int ligne, final int colonne) {
		
		super("Labyrinthe");
		windowPanel= new WindowPanel(this,boxes, path, ligne, colonne);
		setContentPane(windowPanel);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}

}
