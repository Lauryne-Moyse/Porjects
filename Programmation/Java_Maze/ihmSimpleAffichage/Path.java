package ihmSimpleAffichage;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Path extends JPanel {
	
private final Labrinth labrinth;
	
	public Path(Labrinth labrinth) {
		
		super();
		this.labrinth=labrinth;
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension (256,256));
		

}
}
