package unit;

import global.Global;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import structure.ImageList;

import global.PathFinder;

public class Ant {
	// Position.
		private double x, y;
		
		// Speed.
		private double speed = 2;
		
		// Bug heading.
		private double heading = 0;
		
		// Path colour.
		private Color pathColour;
		
		// Movement Path.
		private ArrayList<Point> path = new ArrayList<Point>();
		
		// Selected.
		private boolean selected = false;
		
		// Image of this bug.
		private BufferedImage BUG_REST;
		private ArrayList<BufferedImage> BUG_MOVE = new ArrayList<BufferedImage>();
		
		// Bug animation frame.
		private double frame_pointer = 1;
		
		/**
		 * Constructor.
		 */
		public Ant(double x, double y) {
			this.x = x;
			this.y = y;
			
			Random r = new Random();
			pathColour = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
			
			// Load images.
			try {
				BUG_REST = ImageIO.read(ImageList.class.getResource("/units/" + "ant_rest.png"));
				BUG_MOVE.add(ImageIO.read(ImageList.class.getResource("/units/" + "ant_move_1.png")));
				BUG_MOVE.add(ImageIO.read(ImageList.class.getResource("/units/" + "ant_rest.png")));
				BUG_MOVE.add(ImageIO.read(ImageList.class.getResource("/units/" + "ant_move_2.png")));
				BUG_MOVE.add(ImageIO.read(ImageList.class.getResource("/units/" + "ant_rest.png")));
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Update bug state and position.
		 */
		public void update() {
			if (!path.isEmpty()) {
				Point p = path.get(0);
				
				double l = Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
				
				synchronized (path) {
					if (l < speed) {
						path.remove(0);
						frame_pointer = 1;
						return;
					}
				}
				
				double dx = (x - p.x) / l;
				double dy = (y - p.y) / l;
				
				x -= speed * dx;
				y -= speed * dy;
				
				heading = Math.toDegrees(Math.atan2(dy, dx)) - 90;
				
				// Image.
				frame_pointer = (frame_pointer + 0.1) % BUG_MOVE.size();
			}
		}
		
		/**
		 * Draw the bug.
		 */
		public void draw(Graphics2D g2d) {
			
			// If selected.
//			if (Global.SELECTED_BUGS.contains(this)) {
//				g2d.setColor(new Color(0, 0, 255, 50));
//				g2d.fillArc((int) x-12, (int) y-12, 24, 24, 0, 360);
//				g2d.setColor(Color.BLACK);
//				g2d.drawArc((int) x-12, (int) y-12, 24, 24, 0, 360);
//			}
			
			// Bug heading.
			AffineTransform at = g2d.getTransform();
			g2d.rotate(Math.toRadians(heading), x, y);
			g2d.drawImage(BUG_MOVE.get((int) frame_pointer), (int) x-16, (int) y-16, null);
			g2d.setTransform(at);
		}
		
		/**
		 * Find path to given point and set bug path.
		 */
		public void pathTo(Point p) {
			Random r = new Random();
			synchronized (path) {
				if (Global.FORMATION) {
					int rowLen = (int)Math.sqrt(Global.SELECTED_BUGS.size()+9);
					int dx = (Global.SELECTED_BUGS.indexOf(this) % rowLen) * 32;
					if (Global.SELECTED_BUGS.indexOf(this)+1 > Global.SELECTED_BUGS.size()-Global.SELECTED_BUGS.size()%rowLen) {
						dx += (rowLen-Global.SELECTED_BUGS.size()%rowLen)/2d*32;
					}
					int dy = ((Global.SELECTED_BUGS.size()*32)+Global.SELECTED_BUGS.indexOf(this) / rowLen) * 32;
				
					p.x+=dx-(rowLen-1)/2d*32;
					p.y+=(dy-(Global.SELECTED_BUGS.size()/rowLen*32)/2)+16;
					
				} else {
					int d = (int)( Math.sqrt(Global.BUGS.size()) * 16);
					System.out.println(d);
					p.x += r.nextInt(d+16)-(d/2);
					p.y += r.nextInt(d+16)-(d/2);
				}
				
				path.clear();
	            path.addAll(PathFinder.intelligentPath(new Point2D.Double(x, y), p));
			}
		}
		
		/**
		 * Set selected status.
		 */
		public void setSelected(boolean tf) {
			selected = tf;
		}
		
		public double getX() { return x; }
		public double getY() { return y; }
		public boolean isSelected() { return selected; }
}