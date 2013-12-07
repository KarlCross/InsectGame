package input;

import unit.Ant;
import global.GameThread;
import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Handles mouse actions.
 */
public class MouseController implements MouseListener, MouseMotionListener {

	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		GameThread.getActiveFrame().mouseClicked(e);
	}

	
	@Override
	public void mousePressed(MouseEvent e) {
		GameThread.getActiveFrame().mousePressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		GameThread.getActiveFrame().mouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		GameThread.getActiveFrame().mouseEntered(e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		GameThread.getActiveFrame().mouseExited(e);
	}

	
	@Override
	public void mouseDragged(MouseEvent e) {
		GameThread.getActiveFrame().mouseDragged(e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		GameThread.getActiveFrame().mouseMoved(e);
	}
	
	
}
