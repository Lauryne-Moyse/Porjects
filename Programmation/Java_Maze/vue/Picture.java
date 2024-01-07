package vue;

import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;


/**
 * Les variables static de Picture sont les photos correspondant à l'afficage des cases du labyrinthe 
 */
public final class Picture {

	public static Image path;
	public static Image wall;
	public static Image empty;
	public static Image arrival;
	public static Image departure;
	
	
	static 	{
		
			try {
			
				path = ImageIO.read(new File("data/Path.jpg"));
				wall = ImageIO.read(new File("data/Wall.jpg"));
				empty = ImageIO.read(new File("data/Empty.jpg"));
				arrival = ImageIO.read(new File("data/Arrival.jpg"));
				departure = ImageIO.read(new File("data/Departure.jpeg"));
				
			} catch (Exception e) {
				System.out.println( "Error : " + e);
			} 
	}
	
}
