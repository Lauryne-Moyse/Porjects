package vue;
import javax.swing.*;

public final class MenuBar extends JMenuBar {

	
	// ### Attributs ###
	
	private final MenuFile fileMenu;
	private final ItemResize resize;
	
	
	// ### Constructeur ###
	
	public MenuBar(final Labyrinth labyrinth) {
		super();
		add(fileMenu = new MenuFile(labyrinth));
		add(resize = new ItemResize(labyrinth));
	}
}
