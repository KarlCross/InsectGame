package frames;

import global.GameThread;
import global.Global;
import gui.HUD;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import player.Player;

/**
 * The game frame.
 * @author Dan
 */
public class Game implements Frame {
	
	private long lastUpdate = 0;
	
	/**
	 * Game update.
	 */
	@Override
	public void update() {
		// TESTING - Increase resources by a little.
		if (System.currentTimeMillis() - lastUpdate > 1000) {
			Player.FOOD++;
			Player.LEAVES++;
			Player.MUD++;
			Player.TWIGS++;
			lastUpdate = System.currentTimeMillis();
		}
	}

	/**
	 * Draw screen.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setBackground(Color.RED);
		g2d.clearRect(0, 0, g2d.getClipBounds().width, g2d.getClipBounds().height);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Game - Press 'm' for menu screen", 10, 60);
		
		// HUD.
		HUD.draw(g2d);
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
