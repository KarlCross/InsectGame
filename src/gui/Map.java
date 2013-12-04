package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class holds details on the currently loaded map.
 * @author Dan
 */
public class Map {

	// Images.
	private static BufferedImage GROUND;
	private static BufferedImage COLLIDER;
	
	/**
	 * Constructor.
	 */
	public Map(String map) {
		try {
			GROUND = ImageIO.read(Map.class.getResource("/" + map + "/" + map + ".png"));
			COLLIDER = ImageIO.read(Map.class.getResource("/" + map + "/" + map + "_collider.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Draw the map.
	 */
	public void draw(Graphics2D g2d) {
		g2d.drawImage(GROUND, 0, 0, null);
	}
	
}
