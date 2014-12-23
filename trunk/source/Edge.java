import java.awt.geom.Line2D;


public class Edge {
	
	//properties
	public static final int DIR_NORTH = 0;
	public static final int DIR_EAST = 1;
	public static final int DIR_SOUTH = 2;
	public static final int DIR_WEST = 3;
	
	private Point start, end;
	private int direction;
	
	//methods
	public Edge(Point start, Point end, int direction)
	{
		this.start = start;
		this.end = end;
		this.direction = direction;
	}
	
	public boolean isOn(Point point)
	{
		return Line2D.ptSegDist(start.getX(), start.getY(), end.getX(), end.getY(), point.getX(), point.getY()) == 0.0f;
	}

	public Point getStart() {
		return start;
	}

	public void setStart(Point start) {
		this.start = start;
	}

	public Point getEnd() {
		return end;
	}

	public void setEnd(Point end) {
		this.end = end;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public String toString(){
		return "(" + start.getX() + "," + start.getY() + ")/(" + end.getX() + "," + end.getY() + ")(" + direction + ")";
	}
}
