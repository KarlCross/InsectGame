package global;

import global.Graph.Node;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class PathFinder {
	
	public static ArrayList<Point> intelligentPath(Point2D start, Point dest) {
		ArrayList<Point> path =  new ArrayList<Point>();
		ArrayList<Node> sho_path = Global.CURRENT_MAP.GRAPH.shortest_path(start, dest);
		if (sho_path == null) {
			return path;
		}
		else {
			for (Node n : sho_path) {
				path.add(new Point(n.x, n.y));
			}
			return path;
		}
	}

}
