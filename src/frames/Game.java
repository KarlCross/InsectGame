package frames;

import global.GameThread;
import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * The game frame.
 * @author Dan
 */
public class Game implements Frame {
	
	/**
	 * Game update.
	 */
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Draw screen.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setBackground(Color.RED);
		g2d.clearRect(0, 0, g2d.getClipBounds().width, g2d.getClipBounds().height);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Game - Press 'm' for menu screen", 10, 20);
	}

	/**
	 * Keyboard events are passed to this frame when active.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'm':
			GameThread.setActiveFrame(Global.MENU);
			break;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
