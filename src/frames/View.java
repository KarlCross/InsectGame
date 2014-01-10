package frames;

import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * All program drawing states (menu, game, etc.) must implement this
 * interface and its methods in order to be set as an active view
 * to draw in GameThread.
 * @author Dan
 */
public abstract class View implements KeyListener, MouseListener, MouseMotionListener {

	/**
	 * Update the current frame.
	 */
	public abstract void update();
	
	/**
	 * Draw the current frame.
	 */
	public abstract void draw(Graphics2D g2d);
	
}
