package global;

import frames.Game;
import frames.Menu;
import gui.Interface;
import gui.Map;

/**
 * Global variable class.
 * @author Dan
 */
public abstract class Global {

	// Interface.
	public static Interface INTERFACE;
	
	// Menu Frame.
	public static Menu MENU;
	
	// Game Frame.
	public static Game GAME;
	
	// Current loaded game map
	public static Map CURRENT_MAP;
	
}
