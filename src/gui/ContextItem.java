package gui;


import java.awt.geom.Ellipse2D;
import java.awt.geom.Ellipse2D.Double;
import java.awt.image.BufferedImage;

/**
 * A class to represent a context menu item
 * @author kwss2
 *
 */
public class ContextItem {
	// The icon to draw for this context item
	public BufferedImage button;
	// The method name associated with this context item
	public String action;
	// The parameters to be passed to the method
	public Object[] params;
	// If the item is hovered over
	private boolean highlight = false;
	
	// Intersect area
	private Double bounds;
	
	/**
	 * Constructor
	 * @param x the x position
	 * @param y the y position
	 * @param img the icon
	 * @param action the method to perform
	 * @param params the methods parameters
	 */
	public ContextItem(int x, int y, BufferedImage img, String action, Object[] params) {
		this.bounds = new Ellipse2D.Double(x, y, img.getWidth()*0.5, img.getHeight()*0.5);
		this.action = action;
		this.button = img;
		this.params = params;
	}
	
	/**
	 * Gets the intersect area
	 */
	public Ellipse2D.Double getBounds() {
		return bounds;
	}

	
	/**
	 * Set whether this item is hovered over
	 * @param b true or false
	 */
	public void setHighlight(boolean b) {
		this.highlight  = b;
		
	}
	
	/**
	 * 
	 * @return true if this is hovered over, else false
	 */
	public boolean isHighlighted() {
		return highlight;
	}
}
