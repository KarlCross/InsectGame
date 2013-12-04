package global;

import javax.swing.UIManager;

import frames.Game;
import frames.Menu;
import gui.Interface;
import gui.Map;

/**
 * Game entry class.
 * @author Dan
 */
public class InsectGame {

	/**
	 * Game entry method.
	 */
	public static void main(String[] args) {
		// Set l&f.
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		
		// Start a daemon thread to force windows to use high-precision timers.
		new Thread() {
			{
				setDaemon(true);
				start();
			}
			public void run() {
				while(true) {
					try {
						Thread.sleep(Long.MAX_VALUE);
					} catch(Exception e) {}
				}
			}
		};
		
		// Load Maps.
		Global.CURRENT_MAP = new Map("map1");
		
		// Instantiate all views.
		Global.MENU = new Menu();
		Global.GAME = new Game();
		
		// Start the interface.
		Global.INTERFACE = new Interface(Global.MENU);
	}
}
