package global;

import java.util.ArrayList;

import frames.Game;
import frames.Menu;
import gui.Interface;
import gui.Map;
import unit.Unit;

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
	
	// Formation toggled
	public static boolean FORMATION = false;
	
	// All the bugs
	public static ArrayList<Unit> BUGS = new ArrayList<Unit>();
	
	// For later
	public static ArrayList<Unit> SELECTED_BUGS = new ArrayList<Unit>();
	
}
