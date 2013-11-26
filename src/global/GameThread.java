package global;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;

/**
 * This class starts game update and canvas rendering threads.
 * @author Dan
 *
 */
public abstract class GameThread {

	// Game update rate (ms)
	public static final int GAME_UPDATE_RATE = 10;
	
	// Toggle to pause canvas rendering.
	public static boolean PAUSE_RENDERING = false;
	
	/**
	 * Start both game update and rendering threads.
	 * A Canvas and BufferStrategy are required to draw upon.
	 */
	public static void startGameThreads(Canvas canvas, BufferStrategy bs) {
		update();
		render(canvas, bs);
	}
	
	/**
	 * Game update thread.
	 * Runs (1000/GAME_UPDATE_RATE) times a second.
	 * If the game computation takes longer than GAME_UPDATE_RATE, it will run slower.
	 */
	private static void update() {
		new Thread() {
			public void run() {
				try {
					long lastUpdate = 0;
					while (true) {
						if (System.currentTimeMillis() - lastUpdate > GAME_UPDATE_RATE) {
							lastUpdate = System.currentTimeMillis();
							
							// Update stuff.
							
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	/**
	 * The rendering thread.
	 * Runs as many times as possible per second.
	 * It is hard capped at 100fps, but will realistically run at around 60fps.
	 * To remove the cap (will also increase running fps), replace Thread.sleep(10); with Thread.yield();
	 */
	private static void render(final Canvas canvas, final BufferStrategy bs) {
		new Thread() {
			@Override
			public void run() {
				Graphics2D g2d = null;
				try {
					while (true) {
						if (!PAUSE_RENDERING) {
							g2d = (Graphics2D) bs.getDrawGraphics();
							g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
							
							// Fill the background.
							g2d.setBackground(Color.WHITE);
							g2d.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
							
							// Draw stuff.
							
							
							// Blit the back buffer to the screen.
						    if(!bs.contentsLost())
						    	bs.show();
						    
						    // Cap at 100fps.
						    Thread.sleep(10);
						} else {
							Thread.sleep(100);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					g2d.dispose();
				}
			}
		}.start();
	}
}
