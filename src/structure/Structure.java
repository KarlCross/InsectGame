package structure;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Structure {
	//Image of the structure
	protected BufferedImage image;
	protected int x, y;
	
	public Structure(int x, int y, BufferedImage image) {
		this.image = image;
		configure();
	}
	
	public abstract void draw(Graphics2D g2d);
	
	protected abstract void configure();
}
