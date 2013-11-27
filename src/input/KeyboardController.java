package input;

import global.GameThread;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Listens to keyboard events and forwards them on to the
 * active program frame.
 * @author Dan
 */
public class KeyboardController implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		GameThread.getActiveFrame().keyPressed(e);
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		GameThread.getActiveFrame().keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		GameThread.getActiveFrame().keyTyped(e);
	}
}
