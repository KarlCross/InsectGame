package unit;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Unit {
	abstract double getX();
	abstract double getY();
	abstract void update();
	abstract void setUp(double x, double y);
	abstract void draw(Graphics2D g2d);
	abstract void setSelected(boolean selected);
	abstract void pathTo(Point point, int group);
 }
