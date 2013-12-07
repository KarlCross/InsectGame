package frames;

import global.GameThread;
import global.Global;
import gui.HUD;

import input.MouseController;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import player.Player;
import structure.Structure;
import unit.Ant;

/**
 * The game view.
 * @author Dan
 */
public class Game extends View {
	
	private long lastUpdate = 0;
	// Last pressed pos.
	private static Point lastPressed = null;
	private static Point draggedTo = null;
	
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
		// Update buildings.
		synchronized (Player.STRUCTURES) {
			for (Structure s : Player.STRUCTURES) {
				s.update();
			}
		}
		
		// Update units.
		
		synchronized (Global.BUGS) {
			for (Ant a : Global.BUGS) {
				a.update();
			}
		}
	}

	/**
	 * Draw screen.
	 */
	@Override
	public void draw(Graphics2D g2d) {
		// Draw map.
		Global.CURRENT_MAP.draw(g2d);
		
		// Drag box.
		Point lastPressed_ = lastPressed, draggedTo_ = draggedTo;
		if (lastPressed_ != null && draggedTo_ != null) {
			g2d.setColor(new Color(255,0,0,80));
			g2d.fillRect(lastPressed_.x, lastPressed_.y, draggedTo_.x-lastPressed_.x, draggedTo_.y-lastPressed_.y);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(lastPressed_.x, lastPressed_.y, draggedTo_.x-lastPressed_.x, draggedTo_.y-lastPressed_.y);
		}
		
		
		// Draw buildings.
		synchronized (Player.STRUCTURES) {
			for (Structure s : Player.STRUCTURES) {
				s.draw(g2d);
			}
		}
		
		// Draw units.
		
		synchronized (Global.BUGS) {
			for (Ant a : Global.BUGS) {
				a.draw(g2d);
			}
		}
		
		
		
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
		case 'x':
			System.exit(0);
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		lastPressed = e.getPoint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			if (!e.isShiftDown()) {
				Global.SELECTED_BUGS.clear();				
			}
			
			// Was drag box?
			if (draggedTo == null) {				
				for (Ant b : Global.BUGS) {
					double dist = Math.sqrt(Math.pow(b.getX() - e.getPoint().x, 2) + Math.pow(b.getY() - e.getPoint().y, 2));
					if (dist < 20) {
						Global.SELECTED_BUGS.add(b);
						b.setSelected(true);
						break;
					}
				}
			} else {
				for (Ant b : Global.BUGS) {
					if (b.getX() > lastPressed.x && b.getX() < draggedTo.x) {
						if (b.getY() > lastPressed.y && b.getY() < draggedTo.y)
							Global.SELECTED_BUGS.add(b);
						b.setSelected(true);
					}
				}
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			for (Ant b : Global.SELECTED_BUGS) {
				b.pathTo(e.getPoint());
			}
		}
		
		lastPressed = null;
		draggedTo = null;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		draggedTo = e.getPoint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}
	
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
