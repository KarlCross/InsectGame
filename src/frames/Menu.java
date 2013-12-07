package frames;

import global.GameThread;
import global.Global;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * The menu view.
 * @author Dan
 */
public class Menu extends View {

	
	/**
	 * The area around the "Play" button. 
	 */
	private Rectangle2D rect;
	/**
	 * The area around the "Exit" button.
	 */
	private Rectangle2D rect2;
	
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
		g2d.setFont(new Font("Arial", Font.PLAIN, 50));
		int x = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 65;
		int y = Toolkit.getDefaultToolkit().getScreenSize().height / 2;
		g2d.drawString("Play", x, y);
		g2d.drawString("Exit", x + 3, y + 120);
		g2d.setStroke(new BasicStroke(4));
		g2d.setColor(Color.CYAN);
		rect = new Rectangle2D.Double(x - 10, y - 45, 115, 60);
		g2d.draw(rect);
		rect2 = new Rectangle2D.Double(x - 10, y + 73, 115, 60);
		g2d.draw(rect2);
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
		case 'x':
			System.exit(0);
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if(rect.contains(e.getPoint())) {
			GameThread.setActiveFrame(Global.GAME);
		} 
		if(rect2.contains(e.getPoint())) {
			System.exit(0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
