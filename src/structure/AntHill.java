package structure;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class AntHill extends Structure {
	
	private Point WAYPOINT;
	private int queue = 0;
	private int unit_cost = 200;
	private int build_progress = 0;
	
	

	public AntHill(int x, int y, BufferedImage image) {
		super(x, y, image);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, null);
	}

	@Override
	protected void configure() {
		HP = 5000;
		DEF = 500;
		WAYPOINT = new Point(x+50, y);
	}
	
	public void update() {
		// If the queue is not empty
		if (queue > 0) {
		// Build another tick of ant
			build_progress++;
		// If ant is completed then deploy and remove from queue
			if(build_progress == unit_cost) {
				// Create ant and add to players units
				build_progress = 0;
				queue--;
			}
		}
	}
	
	public void addToQueue() {
		queue++;
	}

}
