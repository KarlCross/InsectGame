package gui;

import frames.View;
import global.GameThread;
import input.KeyboardController;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/**
 * The game window.
 * 
 * @author Dan
 */
public class Interface extends JFrame {

	// Canvas on which the graphs are drawn.
	private static Canvas canvas = new Canvas();
		
	// Buffer strategy for frame blitting on canvas.
	private BufferStrategy bs;
		
	/**
	 * Constructor.
	 */
	public Interface(View initialFrame)
	{
		// Setup.
		setSize(816, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		canvas.setIgnoreRepaint(true);
		
		// Add keyboard listener (may need to add to JFrame also).
		KeyboardController kc = new KeyboardController();
		canvas.addKeyListener(kc);
		
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
