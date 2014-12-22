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
		Point temp = new Point(p);
		//North Checking
		temp.setY(temp.getY() - 1);
		boolean loop = true;
		while(loop)
		{
			if (temp.getY() < 0)
				break;
			ArrayList<Edge> sEdges = getStandingEdges(temp);
			for (int i = 0; i < sEdges.size(); i++)
			{
				int dir = sEdges.get(i).getDirection();
				if (dir == Edge.DIR_SOUTH)
					return false;
				else if (dir == Edge.DIR_NORTH)
				{
					loop = false;
					break;
				}
			}
			temp.setY(temp.getY() - 1);
		}
		//South Checking
		temp = new Point(p);
		temp.setY(temp.getY() + 1);
		loop = true;
		while(loop)
		{
			if (temp.getY() > 999)
				break;
			ArrayList<Edge> sEdges = getStandingEdges(temp);
			for (int i = 0; i < sEdges.size(); i++) 
			{
				int dir = sEdges.get(i).getDirection();
				if (dir == Edge.DIR_NORTH)
					return false;
				else if (dir == Edge.DIR_SOUTH)
				{
					loop = false;
					break;
				}
			}
			temp.setY(temp.getY() + 1);
		}
		//East Checking
		temp = new Point(p);
		temp.setX(temp.getX() + 1);
		loop = true;
		while(loop)
		{
			if (temp.getX() > 999)
				break;
			ArrayList<Edge> sEdges = getStandingEdges(temp);
			for (int i = 0; i < sEdges.size(); i++) 
			{
				int dir = sEdges.get(i).getDirection();
				if (dir == Edge.DIR_WEST)
					return false;
				else if (dir == Edge.DIR_EAST)
				{
					loop = false;
					break;
				}
			}
			temp.setX(temp.getX() + 1);
		}
		//West Checking
		temp = new Point(p);
		temp.setX(temp.getX() - 1);
		loop = true;
		while(loop)
		{
			if (temp.getX() < 0)
				break;
			ArrayList<Edge> sEdges = getStandingEdges(temp);
			for (int i = 0; i < sEdges.size(); i++) 
			{
				int dir = sEdges.get(i).getDirection();
				if (dir == Edge.DIR_EAST)
					return false;
				else if (dir == Edge.DIR_WEST)
				{
					loop = false;
					break;
				}
			}
			temp.setX(temp.getX() - 1);
		}	
		
		return true;
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

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void setEdgeList(ArrayList<Edge> edgeList) {
		this.edgeList = edgeList;
	}
	
	
}
