package frames;

import global.GameThread;
import global.Global;
import gui.HUD;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import player.Player;
import structure.Structure;

/**
 * The game view.
 * @author Dan
 */
public class Game implements View {
	
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
		// Draw map.
		Global.CURRENT_MAP.draw(g2d);
		
		// Draw buildings.
		synchronized (Player.STRUCTURES) {
			for (Structure s : Player.STRUCTURES) {
				s.draw(g2d);
			}
		}
		
		// Draw units.
		
		
		
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
