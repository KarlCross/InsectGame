package structure;

import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;

import player.Player;
import unit.Ant;

public class AntHill extends Structure {
	
	private Point WAYPOINT;
	private int queue = 10;
	private int unit_cost = 50;
	private double build_progress = 0;
	private int updates;
	
	public AntHill(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, null);
		if(queue == 0) return;
		g2d.setColor(Color.RED);
		g2d.drawRect(x+5, y+5, 100, 3);
		g2d.setColor(Color.GREEN);
		g2d.drawRect(x+5, y+5, (int)(100*(build_progress/unit_cost)), 3);
		g2d.setColor(Color.BLACK);
	}

	@Override
	protected void configure() {
		image = ImageList.ANTHILL;
		HP = 5000;
		DEF = 500;
		WAYPOINT = new Point(x+450, y+400);
	}
	
	@Override
	public void update() {
		// If the queue is not empty
		if (queue > 0) {
			if (Player.FOOD > 0) {
			// Build another tick of ant
				build_progress+=0.1;
				
				// Every ten updates remove 1 food
				updates+=1;
				if(updates == 10) {
					Player.FOOD-=1;
					updates = 0;
				}
			// If ant is completed then deploy and remove from queue
				if((int)build_progress == unit_cost) {
					// Create ant and add to players units
					Ant ant = new Ant(x+100, y);
					ant.pathTo(WAYPOINT);
					Global.BUGS.add(ant);
					build_progress = 0;
					queue--;
				}
			}
		}
	}
	
	public void addToQueue() {
		queue++;
	}

}
