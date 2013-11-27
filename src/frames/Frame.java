package frames;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * All program drawing states (menu, game, etc.) must implement this
 * interface and its methods in order to be set as an active frame
 * to draw in GameThread.
 * @author Dan
 */
public interface Frame {

	/**
	 * Update the current frame.
	 */
	public void update();
	
	/**
	 * Draw the current frame.
	 */
	public void draw(Graphics2D g2d);
	
	/**
	 * Methods forwarded on from KeyboardController.
	 */
	public void keyPressed(KeyEvent e);
	public void keyReleased(KeyEvent e);
	public void keyTyped(KeyEvent e);
}
