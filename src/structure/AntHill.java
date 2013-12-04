package structure;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class AntHill extends Structure {

	public AntHill(int x, int y, BufferedImage image) {
		super(x, y, image);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, null);

	}

	@Override
	protected void configure() {
		// TODO Auto-generated method stub

	}
	
	public void update() {
		// If the queue is not empty
		
		// Build another tick of ant
		
		// If ant is completed then deploy and remove from queue
	}

}
