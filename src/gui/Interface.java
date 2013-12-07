package gui;

import input.KeyboardController;
import input.MouseController;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.GraphicsDevice;
import java.awt.Window;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import frames.View;
import global.GameThread;

/**
 * The game window.
 * 
 * @author Dan
 */
public class Interface extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2573566264471514702L;

	// Canvas on which the graphs are drawn.
	private static Canvas canvas = new Canvas();
		
	// Buffer strategy for frame blitting on canvas.
	private BufferStrategy bs;
		
	/**
	 * Constructor.
	 */
	public Interface(View initialFrame, GraphicsDevice gd, JFrame frame)
	{
		super(frame, gd.getDefaultConfiguration());
		gd.setFullScreenWindow(this);
		// Setup.
		//setSize(800, 816);
		canvas.setIgnoreRepaint(true);
		
		
		// Add keyboard listener (may need to add to JFrame also).
		KeyboardController kc = new KeyboardController();
		canvas.addKeyListener(kc);
		MouseController mc = new MouseController();
		canvas.addMouseListener(mc);
		canvas.addMouseMotionListener(mc);
		canvas.setFocusable(true);
		
		// Add Components
		add(canvas, BorderLayout.CENTER);
		
		// Finish & make visible.
		setVisible(true);
		
		// Set up buffer strategy.
		canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();
    
       // Start game threads.
       GameThread.startGameThreads(canvas, bs, initialFrame);
	}
}
