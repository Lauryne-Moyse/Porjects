package ihmSimpleAffichage;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Departure extends JButton {
	
private final Labrinth labrinth;
	
	public Departure(Labrinth labrinth) {
		
		super("Départ");
		this.labrinth=labrinth;
		//setBackground(Color.RED);
		//setPreferredSize(new Dimension (256,256));
		

}
}