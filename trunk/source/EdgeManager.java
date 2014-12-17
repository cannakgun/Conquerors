import java.util.ArrayList;


public class EdgeManager {
	
	//properties
	private ArrayList<Edge> edgeList;
	
	//methods
	public EdgeManager()
	{
		edgeList = new ArrayList<Edge>();
		addEdge(new Edge(new Point(-5, 0), new Point(1005, 0), Edge.DIR_NORTH));
		addEdge(new Edge(new Point(0, -5), new Point(0, 1005), Edge.DIR_WEST));
		addEdge(new Edge(new Point(-5, 999), new Point(1005, 999), Edge.DIR_SOUTH));
		addEdge(new Edge(new Point(999, -5), new Point(999, 1005), Edge.DIR_EAST));
		
	}
	
	public void createEdges(ArrayList<Point> pointList)
	{
		//Point listi edgelere dönüþtürülecek
	}
	
	public boolean isPositionValid(Point p)
	{
		
		return false;
		//kaðýttaki algoritma
	}
	public boolean isOnEdge(Point p)
	{
		for (int i = 0; i < edgeList.size(); i++)
			if (edgeList.get(i).isOn(p))
				return true;
		return false;
	}
	public ArrayList<Edge> getStandingEdges(Point p)
	{
		ArrayList<Edge> returnList = new ArrayList<Edge>();
		for (int i = 0; i < edgeList.size(); i++)
			if (edgeList.get(i).isOn(p))
				returnList.add(edgeList.get(i));
		return returnList;
	}
	
	public void addEdge(Edge edge)
	{
		edgeList.add(edge);
	}
	
}
