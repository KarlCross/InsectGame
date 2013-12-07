package global;

import frames.Game;
import frames.Menu;
import gui.Interface;
import gui.Map;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.UIManager;

import player.Player;
import structure.AntHill;

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
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		JFrame owner = new JFrame();
		owner.pack();
		owner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		owner.setVisible(true);
		Global.INTERFACE = new Interface(Global.MENU, gd, owner);
		
		
		Player.STRUCTURES.add(new AntHill(50, 50));
	}
}
