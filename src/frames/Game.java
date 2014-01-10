package frames;

import global.GameThread;
import global.Global;
import gui.ContextItem;
import gui.HUD;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import player.Player;
import structure.Structure;
import unit.Unit;

/**
 * The game view.
 * @author Dan
 */
public class Game extends View {
	
	private long lastUpdate = 0;
	private Structure selected;
	private ContextItem contextItem;
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
			for (Unit a : Global.BUGS) {
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
			int x1, x2, y1, y2;
			x1 = (int) Math.min(lastPressed.x, draggedTo.x);
			x2 = (int) Math.max(lastPressed.x, draggedTo.x);
			y1 = (int) Math.min(lastPressed.y, draggedTo.y);
			y2 = (int) Math.max(lastPressed.y, draggedTo.y);
			g2d.setColor(new Color(255,0,0,80));
			g2d.fillRect(x1, y1,x2-x1, y2-y1);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x1, y1,x2-x1, y2-y1);
		}
		
		
		// Draw buildings.
		synchronized (Player.STRUCTURES) {
			for (Structure s : Player.STRUCTURES) {
				s.draw(g2d);
			}
		}
		
		// Draw units.
		
		synchronized (Global.BUGS) {
			for (Unit a : Global.BUGS) {
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
		for(Structure s : Player.STRUCTURES) {
			if(s.getBounds().contains(e.getPoint())) {
				selected = s;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
				}
				if(s != null) {
					s.showContextMenu(true);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(selected != null && contextItem != null) {
			selected.doAction(contextItem.action, contextItem.params);
		}
		if(selected != null) {
			selected.showContextMenu(false);
		}

		selected = null;
		contextItem = null;
		if (e.getButton() == MouseEvent.BUTTON1) {
			
			if (!e.isShiftDown()) {
				Global.SELECTED_BUGS.clear();				
			}
			
			// Was drag box?
			if (draggedTo == null) {				
				for (Unit b : Global.BUGS) {
					double dist = Math.sqrt(Math.pow(b.getX() - e.getPoint().x, 2) + Math.pow(b.getY() - e.getPoint().y, 2));
					if (dist < 20) {
						Global.SELECTED_BUGS.add(b);
						b.setSelected(true);
						break;
					}
				}
			} else {
				int x1, x2, y1, y2;
				x1 = (int) Math.min(lastPressed.x, draggedTo.x);
				x2 = (int) Math.max(lastPressed.x, draggedTo.x);
				y1 = (int) Math.min(lastPressed.y, draggedTo.y);
				y2 = (int) Math.max(lastPressed.y, draggedTo.y);
				for (Unit b : Global.BUGS) {
					if (b.getX() > x1 && b.getX() < x2) {
						if (b.getY() > y1 && b.getY() < y2)
							Global.SELECTED_BUGS.add(b);
						b.setSelected(true);
					}
				}
			}
		}
		else if (e.getButton() == MouseEvent.BUTTON3) {
			for (Unit b : Global.SELECTED_BUGS) {
				b.pathTo(e.getPoint(), Global.SELECTED_BUGS.size());
			}
		}
		
		lastPressed = null;
		draggedTo = null;
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(selected != null) {
			contextItem = selected.getSelectedContext(e.getPoint());
		}
		else {
			draggedTo = e.getPoint();
		}
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
