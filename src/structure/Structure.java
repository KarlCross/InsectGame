package structure;

import gui.ContextItem;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public abstract class Structure {
	//Image of the structure
	protected BufferedImage image;
	protected int x, y;
	protected int HP, DEF;
	protected ContextItem[] context_menu;
	
	public Structure(int x, int y) {
		this.x = x;
		this.y = y;
		configure();
	}
	
	public Rectangle2D getBounds() {
		return new Rectangle2D.Double(x, y, image.getWidth(), image.getHeight());
	}
	
	public abstract void draw(Graphics2D g2d);
	
	public abstract void update();
	
	protected abstract void configure();

	public abstract void showContextMenu(boolean show);
	
	public abstract void populateContextMenu();

	public ContextItem getSelectedContext(Point point) {
		for (ContextItem ci : context_menu) {
			ci.setHighlight(false);
			if (ci.getBounds().contains(point)) {
				ci.setHighlight(true);
				return ci;
			}
		}
		return null;
	}

	public void doAction(String action, Object[] params) {
		Method method;
		try {
			method = this.getClass().getDeclaredMethod(action, (new Class<?>[]{String.class}));
			try {
				method.invoke(this, params);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {e.printStackTrace();}
		} catch (NoSuchMethodException | SecurityException e1) {e1.printStackTrace();}
		
	}
}
