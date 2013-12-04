package global;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A graph of collidable nodes for path finding with A* shortest path function.
 * @author kwss2
 *
 */
public class Graph {
	
	private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Edge> edges = new ArrayList<Edge>();
	private Node[][] ns;
	private Node current;
	private int NODE_SPACING = 32;
	
	public Graph(BufferedImage img, int node_spacing) {
		int[][] flagArray = build_collision_graph(img);
		NODE_SPACING = node_spacing;
		// New array for nodes
		ns = new Node[flagArray.length][flagArray[0].length];
		for(int y = 0; y<flagArray.length; y++ ) {
			for (int x = 0; x < flagArray[y].length; x++) {				
				int pos_x = y*NODE_SPACING+(NODE_SPACING/2);
				int pos_y = x*NODE_SPACING+(NODE_SPACING/2);
				Node n = new Node(pos_x, pos_y, flagArray[y][x] == 1);
				ns[y][x] = n;
				nodes.add(n);				
			}
		}
		for(int y = 0; y<ns.length; y++ ) {
			for (int x = 0; x < ns[y].length; x++) {
				if(ns[y][x] != null) {
					// Create edges to adjacent nodes
					if (y > 0) {
						Node node =  (ns[y-1][x] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					
					if (x > 0 && y > 0) {
						Node node =  (ns[y-1][x-1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					if (y > 0 && x < ns[0].length-1) {
						Node node =  (ns[y-1][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					
					if (y < ns.length-1 && x < ns[0].length-1) {
						Node node =  (ns[y+1][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
					
					if (x < ns[y].length-1) {
						Node node =  (ns[y][x+1] );
						if (node != null) {
							Edge e = new Edge(ns[y][x], node);
							if(!edges.contains(e)) {
								edges.add(e);
								node.addNeighbour(e);
								ns[y][x].addNeighbour(e);
							}
						}
					}
				}
			}
			
		}
	}
	
	public ArrayList<Node> shortest_path(Point2D start, Point dest) {
		ArrayList<Node> open_set = new ArrayList<Node>();
		ArrayList<Node> closed_set = new ArrayList<Node>();
		
		current = ns[(int)start.getX()/NODE_SPACING][(int) start.getY()/NODE_SPACING];
		int crow_flies = ((int) Math.sqrt(Math.pow(start.getX()-dest.x, 2)+Math.pow(dest.y-start.getY(), 2)));
		while (current == null) {
			current = ns[(int)start.getX()-1/NODE_SPACING][(int) start.getY()-1/NODE_SPACING];
		}
		current.f_score = crow_flies;
		
		current.g_score = 0;
		open_set.add(current);
		Node goal = ns[dest.x/NODE_SPACING][dest.y/NODE_SPACING];
		if (goal == null) return null;
		while (open_set.size() > 0) {
			
			Collections.sort(open_set);
			current = open_set.get(0);
			open_set.remove(current);
			closed_set.add(current);
			if (current == goal)
				return reconstruct_path(current.came_from , goal, new ArrayList<Node>());
			for(Edge n : current.neighbours) {
				
				Node neighbour;
				if (n.a == current) {
					neighbour = n.b;
				}
				else {
					neighbour = n.a;
				}
				if(!neighbour.active) continue;
				int tent_g_score = current.g_score + 1;
				int tent_f_score = (int) (tent_g_score + (Math.sqrt(Math.pow(neighbour.x - goal.x, 2)+Math.pow(neighbour.y - goal.y, 2))));
				if (closed_set.contains(neighbour) && tent_f_score >= neighbour.f_score) {
					continue;
				}
				
				if (!open_set.contains(neighbour) || tent_f_score < neighbour.f_score) {
					neighbour.came_from = current;
					neighbour.f_score = tent_f_score;
					neighbour.g_score = tent_g_score;
					if (!open_set.contains(neighbour)) 
						open_set.add(neighbour);
				}
			}
			
		}
		Collections.sort(closed_set);
		current = closed_set.get(0);
		return shortest_path(start, new Point(current.x, current.y));
	}
	
	private ArrayList<Node> reconstruct_path(Node came_from,
			Node goal, ArrayList<Node> path) {		
		while (came_from != null && !path.contains(came_from)) {
			path.add(0,goal);
			goal = came_from;
			came_from = came_from.came_from;
		}
		for (Node n : nodes) {
				n.f_score = 0;
				n.g_score = 0;
				n.came_from = null;			
		}	
		return path;
	}
	
	private int[][] build_collision_graph(BufferedImage img) {
		int[][] flagArray = new int[img.getWidth()/NODE_SPACING][img.getHeight()/NODE_SPACING];
		for(int y = 0; y< img.getHeight()-NODE_SPACING; y+=NODE_SPACING) {
			for (int x = 0; x < img.getWidth()-NODE_SPACING; x += NODE_SPACING) {
				// Get the colour of the four corners and set the array flag accordingly
				// Top left x,y
				int impassable = 0;
				int rgb = img.getRGB(x, y);
				if(rgb < -10000) {
					impassable++;
				}
				
				// None of the corners were impassable
				if (impassable < 1)
					flagArray[x/NODE_SPACING][y/NODE_SPACING] = 1;
			}
		}
		return flagArray;
		
	}
	
	/**
	 * A node
	 * @author kwss2
	 *
	 */
	public class Node implements Comparable<Node> {
		// Edges connected to this Node
		private ArrayList<Edge> neighbours = new ArrayList<Edge>();
		// Position on collision map
		public int x, y;
		public int f_score = 0;
		public int g_score;
		public Node came_from = null;
		public boolean active = false;
		
		public ArrayList<Edge> getNeighbours() {
			return neighbours;
		}
		
		public Node(int pos_x, int pos_y, boolean active) {
			this.active = active;
			this.x = pos_x;
			this.y = pos_y;
		}
		
		public void addNeighbour(Edge e) {
			this.neighbours.add(e);	
		}
		
		@Override
		public int compareTo(Node arg0) {
			if (arg0.f_score > f_score) return -1;
			if (arg0.f_score < f_score) return 1;
			return 0;
		}
	}
	
	/**
	 * An edge
	 * @author kwss2
	 *
	 */
	public class Edge {
		// Connected nodes
		Node a, b;
		public Edge(Node node, Node node2) {
			a = node;
			b = node2;
		}
		
		@Override
		public boolean equals(Object edge) {
			Edge e = (Edge) edge;
			if (e.a == a && e.b == b) {
				return true;
			}
			if (e.b == a && e.a == b) {
				return true;
			}
			return false;
		}
		
	}

}
