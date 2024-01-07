package vue;
import javax.swing.*;


public final class MenuFile extends JMenu {
	
	private final ItemQuit quit;
	private final ItemInit importMenu;
	private final ItemSave export;

	
	public MenuFile(final Labyrinth labyrinth) {
		  super("File");
		  add(importMenu=new ItemInit(labyrinth));
		  add(export=new ItemSave(labyrinth));
		  add(quit=new ItemQuit(labyrinth));
	}

}
