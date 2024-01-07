package ihmSimpleAffichage;
import javax.swing.*;

import interfaces.*;
import mazeCode.ABox;
import mazeCode.DBox;
import mazeCode.EBox;
import mazeCode.WBox;

import java.awt.*;
import java.util.*;

public class WindowPanel extends JPanel {
	
	
	public WindowPanel(Labrinth labrinth, ArrayList<VertexInterface> boxes, ArrayList<VertexInterface> path, final int ligne, final int colonne) {
		
		super();
		setLayout(new GridLayout(ligne, colonne));
		
		for (VertexInterface vertex : boxes) {
			
			if (path.contains(vertex)) 
				add(new Path(labrinth));
			
			else {
			
				final char label = vertex.getLabel();
				
				if (label=='A')
					add(new Arrival(labrinth));
				 else if (label=='E')
					add(new Empty(labrinth));
				 else if (label=='D')
					 add(new Departure(labrinth));
				 else if (label=='W')
					 add(new Wall(labrinth));
				
			}
		}
		
	}

	

}
