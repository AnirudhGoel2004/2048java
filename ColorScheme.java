//Anirudh Goel
//Class which contains all information regarding to display color for the game

//import statements
import java.awt.Color;
import java.util.HashMap;

public class ColorScheme {
	//declaring variables 
	public final static Color WINBG = new Color (0XFAF8EF);
	public final static Color GRIDBG = new Color (0XBBADA0);

	public final static Color BRIGHT = new Color (0X776E65);
	public final static Color LIGHT = new Color (0XF9F6F2);

	private HashMap<Integer, Color> background = new HashMap<>();
	
	/* 
	 * constructor method 
	 * pre: none
	 * post: calls on backgrounds method
	 */ 
	public ColorScheme() {
		initBackrounds();
	}
	
	/* 
	 * method with background colors for the game
	 * pre: none
	 * post: turns on background colors for the tiles
	 */
	private void initBackrounds() {
		background.put(0,new Color (238, 228, 218, 90));
		background.put(2,new Color (0XEEE4DA));
		background.put(4,new Color (0XEDE0C8));
		background.put(8,new Color (0XF2B179));
		background.put(16,new Color (0XF59563));
		background.put(32,new Color (0XF67C5F));
		background.put(64,new Color (0XF65E3B));
		background.put(128,new Color (0XEDCF72));
		background.put(256,new Color (0XEDCC61));
		background.put(512,new Color (0XEDC850));
		background.put(1024,new Color (0XEDC53F));
		background.put(2048,new Color (0XEDC22E));
		background.put(4096,new Color (0XEDC22E));
		background.put(8192,new Color (0XEDC22E));
		background.put(16384,new Color (0XEDC22E));
		background.put(32768,new Color (0XEDC22E));
		background.put(65536,new Color (0XEDC22E));
	}
	
	/*
	 * returns the background needed for the 4x4 tiles
	 * pre: integer value of tile (must be a valid value for tile)
	 * post: returns background 
	 */
	public Color getTileBackground(int value) {
		return background.get(value);
	}
	
	/*
	 * responsible for the tile color changes depending on the value of the tiles
	 * pre: value of tile (must be a valid value for tile)
	 * post: returns either color code for BRIGHT or LIGHT
	 */
	public Color getTileColor(int value) {
		if (value <= 8) {
			return BRIGHT;
		} else {
			return LIGHT;
		}
	}

}