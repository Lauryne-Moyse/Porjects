package ihmSimpleAffichage;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Wall extends JPanel {
	
private final Labrinth labrinth;
	
	public Wall(Labrinth labrinth) {
		
		super();
		this.labrinth=labrinth;
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension (256,256));
		

}
}