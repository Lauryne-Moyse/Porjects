package ihmSimpleAffichage;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Empty extends JPanel {
	
private final Labrinth labrinth;
	
	public Empty(Labrinth labrinth) {
		
		super();
		this.labrinth=labrinth;
		setBackground(Color.YELLOW);
		setPreferredSize(new Dimension (256,256));
		

}
}