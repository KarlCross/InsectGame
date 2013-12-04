package gui;

import java.awt.Color;
import java.awt.Graphics2D;

import player.Player;

/**
 * The head up display for the game.
 * @author Dan
 */
public abstract class HUD {

	// HUD images.
	
	
	/**
	 * Draw the HUD.
	 */
	public static void draw(Graphics2D g2d) {
		// Resources.
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, g2d.getClipBounds().width, 30);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Food: " + Player.FOOD, 10, 20);
		g2d.drawString("Leaves: " + Player.LEAVES, 310, 20);
		g2d.drawString("Mud: " + Player.MUD, 110, 20);
		g2d.drawString("Twigs: " + Player.TWIGS, 210, 20);
		
	}
	
}
