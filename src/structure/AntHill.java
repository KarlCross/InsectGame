package structure;

import global.Global;
import gui.ContextItem;
import gui.IconList;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import player.Player;
import unit.Ant;
import unit.Unit;

public class AntHill extends Structure {
	
	private Point WAYPOINT;
	private ArrayList<Unit> queue = new ArrayList<Unit>();
	private int unit_cost = 150;
	private double build_progress = 0;
	private int updates;
	private boolean show_context;
	private String[] units;
	private String[] actions = new String[]{"add", "remove"};
	
	public AntHill(int x, int y) {
		super(x, y);
		
	}

	@Override
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, x, y, null);
		if(show_context) {
			for (ContextItem ci : context_menu) {
				g2d.drawImage(ci.isHighlighted() ? ci.button.getScaledInstance((int)(ci.button.getWidth()*1.2), (int)(ci.button.getHeight()*1.2), BufferedImage.SCALE_SMOOTH): ci.button, (int)ci.getBounds().x, (int)ci.getBounds().y, null);
			}
		}
		if(queue.size() == 0) return;
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
		units = new String[4];
		units[0] = "Ant";
		units[1] = "Ant";
		units[2] = "Ant";
		units[3] = "Ant";
		actions = new String[2];
		actions[0] = "add";
		actions[1] = "remove";
		context_menu = new ContextItem[units.length*actions.length];
		populateContextMenu();
	}
	
	@Override
	public void update() {
		// If the queue is not empty
		if (queue.size() > 0) {
			if (Player.FOOD > 0) {
			// Build another tick of ant
				build_progress+=0.2;
				
				// Every ten updates remove 1 food
				updates+=1;
				if(updates == 5) {
					Player.FOOD-=1;
					updates = 0;
				}
			// If ant is completed then deploy and remove from queue
				if((int)build_progress == unit_cost) {
					// Create ant and add to players units
					Unit ant = queue.remove(0);
					ant.pathTo(WAYPOINT, 10);
					Global.BUGS.add(ant);
					build_progress = 0;
				}
			}
		}
	}
	
	public void add(String cls) {
		for(int i = 0; i < 1; i++) {
			try {
				Class<?> c = Class.forName("unit."+cls);
				Unit a = (Unit) c.newInstance();
				a.setUp(x+image.getWidth(), y+(image.getHeight()/2));
				queue.add(a);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			} 
		}
	}
	public void remove(String cls) {
		for(int i = 0; i < 5; i++) {
			try {
				Class<?> c = Class.forName("unit."+cls);
				for(int j = queue.size()-1; j >=0; j--) {
					if(queue.get(j).getClass() == c) {
						queue.remove(j);
						return;
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} 
		}
	}

	@Override
	public void showContextMenu(boolean show) {
		show_context = show;
	}

	@Override
	public void populateContextMenu() {
		System.out.println(units);
		int i = 1;
		for(String unit : units) {
			for(String action : actions) {
				int cx = x+(image.getWidth()/3);
				int cy = y+(image.getHeight()/3);
				int hl = ((image.getWidth()/3)*2);
				double hAngle = 2.0*Math.PI*i/context_menu.length; // 0..12 mapped to 0..2*Pi
				int cix = (int) (hl*Math.sin(hAngle))+cx;
				int ciy = (int) (hl*Math.cos(hAngle))+cy;
				BufferedImage img = buildIcon(0,0,60, 60, IconList.ICONLIST.get(action), Ant.getImage());
				
				context_menu[i-1] = new ContextItem(cix, ciy, img, action, new Object[]{unit});
				i++;
			}
			
		}
		
		
	}
	
	/**
	 * 
	 * Builds an image for an icon depending on a action and a production unit
	 * 
	 * @param i x
	 * @param j y
	 * @param k width
	 * @param l height
	 * @param icon unit image
	 * @param image action image
	 * @return BufferedImage img the finished icon image
	 */
	private BufferedImage buildIcon(int i, int j, int k, int l, BufferedImage icon, BufferedImage image) {
		// Create an empty image for the new icon
		BufferedImage img = new BufferedImage(k, l, BufferedImage.TYPE_INT_ARGB);
		
		// Get the graphics of the new image so we can draw on it
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		
		// Draw and fill a circle as the background
		g2d.setColor(Color.WHITE);
		g2d.fillOval(i, j, k/2, l/2);
		g2d.setColor(Color.BLACK);
		g2d.drawOval(i, j, k/2, l/2);
		
		// Set a scale for the graphics object so we draw the unit image smaller for the icon.
		AffineTransform shrink = new AffineTransform();
		shrink.scale(0.7, 0.7);
		g2d.setTransform(shrink);
		
		// Draw unit image and action icon.
		g2d.drawImage(image, 5, 5, null);
		g2d.drawImage(icon, 5, 5, null);
		
		// Return the image
		return img;
	}

}
