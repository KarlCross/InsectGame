package frames;

import global.GameThread;
import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

/**
 * The menu view.
 * @author Dan
 */
public class Menu implements View {

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
		g2d.setBackground(Color.BLACK);
		g2d.clearRect(0, 0, g2d.getClipBounds().width, g2d.getClipBounds().height);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Menu - Press 'g' for game screen.", 10, 20);
	}

	/**
	 * Keyboard events are passed to this frame when active.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'g':
			GameThread.setActiveFrame(Global.GAME);
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
