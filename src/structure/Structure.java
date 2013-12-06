package structure;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Structure {
	//Image of the structure
	protected BufferedImage image;
	protected int x, y;
	protected int HP, DEF;
	
	public Structure(int x, int y) {
		this.x = x;
		this.y = y;
		configure();
	}
	
	public abstract void draw(Graphics2D g2d);
	
	public abstract void update();
	
	protected abstract void configure();
}
