import java.awt.Polygon;
import java.awt.geom.Line2D;
import java.util.ArrayList;


public class EdgeManager {
	
	//properties
	private ArrayList<Edge> edgeList;
	
	//methods
	public EdgeManager()
	{
		edgeList = new ArrayList<Edge>();
		addEdge(new Edge(new Point(0, 0), new Point(1000, 0), Edge.DIR_NORTH));
		addEdge(new Edge(new Point(0, 0), new Point(0, 1000), Edge.DIR_WEST));
		addEdge(new Edge(new Point(0, 1000), new Point(1000, 1000), Edge.DIR_SOUTH));
		addEdge(new Edge(new Point(1000,0), new Point(1000, 1000), Edge.DIR_EAST));
		
		/*addEdge(new Edge(new Point(950,1000), new Point(950, 950), Edge.DIR_EAST));
		addEdge(new Edge(new Point(950,950), new Point(1000, 950), Edge.DIR_SOUTH));
		
		addEdge(new Edge(new Point(950,950), new Point(950, 900), Edge.DIR_EAST));
		addEdge(new Edge(new Point(950,900), new Point(1000, 900), Edge.DIR_SOUTH));
		
		addEdge(new Edge(new Point(502,1000), new Point(502, 952), Edge.DIR_EAST));
		addEdge(new Edge(new Point(502,952), new Point(600, 952), Edge.DIR_SOUTH));
		addEdge(new Edge(new Point(600,952), new Point(600, 1000), Edge.DIR_WEST));*/
		
	}
	
	public void createEdges(ArrayList<Point> pointList, int size)
	{
		Polygon poly = new Polygon();
		for (int i = 0; i < pointList.size() - 1; i++) {
			Point p = pointList.get(i);
			poly.addPoint(p.getX(), p.getY());
		}
		
		for (int i = 0; i < size - 1; i++) {
			Point p1 = pointList.get(i); 
			Point p2 = pointList.get(i + 1);
			int dir = -1;
			if (p1.getY() == p2.getY()){
				if (poly.contains((p1.getX() + p2.getX())/2, p1.getY() + 1))
					dir = Edge.DIR_SOUTH;
				else
					dir = Edge.DIR_NORTH;
			}
			else{
				if (poly.contains(p1.getX() + 1,(p1.getY() + p2.getY())/2))
					dir = Edge.DIR_EAST;
				else
					dir = Edge.DIR_WEST;
				
			}
			Edge n = new Edge(pointList.get(i),pointList.get(i+1),dir);
			addEdge(n);
		}
		//Point listi edgelere dönüþtürülecek
	}
	
	public boolean isPositionValid(Point p)
	{
		Polygon play = getPlayArea();
		if (play.contains(p.getX(), p.getY()))
			return true;
		else
			for (int i = 0; i < play.npoints; i++) {
				if (Line2D.ptSegDist(play.xpoints[i], play.ypoints[i], play.xpoints[(i + 1) % play.npoints], play.ypoints[(i + 1) % play.npoints], p.getX(), p.getY()) == 0.0f)
					return true;
			}
		return false;
		/*Point temp = new Point(p);
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
			if (temp.getY() > 1000)
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
			if (temp.getX() > 1000)
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
		
		return true;*/
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
	public Polygon getPlayArea() {
		Polygon pa = new Polygon();
		Edge curEdge = edgeList.get(edgeList.size() - 1);
		Point start = curEdge.getStart();
		pa.addPoint(start.getX(), start.getY());
		Point cur = curEdge.getEnd();
		while(!cur.equals(start)) {
			pa.addPoint(cur.getX(), cur.getY());
			ArrayList<Edge> sEdges = getStandingEdges(cur);
			if (sEdges.size() == 2){
				sEdges.remove(curEdge);
				int oldDir = curEdge.getDirection();
				curEdge = sEdges.get(0);
				if (curEdge.getStart().equals(cur)){
					Point otherEnd = curEdge.getEnd();
					if (curEdge.getDirection() == Edge.DIR_EAST || curEdge.getDirection() == Edge.DIR_WEST){
						if (cur.getY() > otherEnd.getY()){
							for(int i = cur.getY() - 1; i >= otherEnd.getY(); i--){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getY() + 1; i <= otherEnd.getY(); i++){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
					else{
						if (cur.getX() > otherEnd.getX()){
							for(int i = cur.getX() - 1; i >= otherEnd.getX(); i--){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getX() + 1; i <= otherEnd.getX(); i++){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
				}
				else if (curEdge.getEnd().equals(cur)){
					Point otherEnd = curEdge.getStart();
					if (curEdge.getDirection() == Edge.DIR_EAST || curEdge.getDirection() == Edge.DIR_WEST){
						if (cur.getY() > otherEnd.getY()){
							for(int i = cur.getY() - 1; i >= otherEnd.getY(); i--){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getY() + 1; i <= otherEnd.getY(); i++){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
					else{
						if (cur.getX() > otherEnd.getX()){
							for(int i = cur.getX() - 1; i >= otherEnd.getX(); i--){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getX() + 1; i <= otherEnd.getX(); i++){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
				}
				else {
					if (oldDir == Edge.DIR_EAST){
						for(int x = cur.getX() - 1; x >= curEdge.getStart().getX() || x >= curEdge.getEnd().getX(); x--){
							Point p = new Point(x,cur.getY());
							ArrayList<Edge> tsEdges = getStandingEdges(p);
							if (tsEdges.size() > 1){
								cur = p;
								break;
							}
						}
					}
					else if (oldDir == Edge.DIR_WEST){
						for(int x = cur.getX() + 1; x <= curEdge.getStart().getX() || x <= curEdge.getEnd().getX(); x++){
							Point p = new Point(x,cur.getY());
							ArrayList<Edge> tsEdges = getStandingEdges(p);
							if (tsEdges.size() > 1){
								cur = p;
								break;
							}
						}
					}
					else if (oldDir == Edge.DIR_SOUTH){
						for(int y = cur.getY() - 1; y >= curEdge.getStart().getY() || y >= curEdge.getEnd().getY(); y--){
							Point p = new Point(cur.getX(),y);
							ArrayList<Edge> tsEdges = getStandingEdges(p);
							if (tsEdges.size() > 1){
								cur = p;
								break;
							}
						}
					}
					else {
						for(int y = cur.getY() + 1; y <= curEdge.getStart().getY() || y <= curEdge.getEnd().getY(); y++){
							Point p = new Point(cur.getX(),y);
							ArrayList<Edge> tsEdges = getStandingEdges(p);
							if (tsEdges.size() > 1){
								cur = p;
								break;
							}
						}
					}
				}
			}
			else if(sEdges.size() == 3){
				for (int j = 0; j < sEdges.size(); j++) {
				}
				int oldDir = curEdge.getDirection();
				sEdges.remove(curEdge);
				curEdge = sEdges.get(0);
				boolean wrong = false;
				Point otherEnd = curEdge.getStart();
				if (otherEnd.equals(cur))
					otherEnd = curEdge.getEnd();
				if (oldDir == Edge.DIR_EAST){
					wrong = otherEnd.getX() > cur.getX() && otherEnd.getY() == cur.getY();
				}
				else if (oldDir == Edge.DIR_WEST){
					wrong = otherEnd.getX() < cur.getX() && otherEnd.getY() == cur.getY();
				}
				else if (oldDir == Edge.DIR_SOUTH){
					wrong = otherEnd.getY() > cur.getY() && otherEnd.getX() == cur.getX();
				}
				else {
					wrong = otherEnd.getY() < cur.getY() && otherEnd.getX() == cur.getX();
				}
				if (wrong)
					curEdge = sEdges.get(1);
				
				if (curEdge.getStart().equals(cur)){
					Point otherEnd1 = curEdge.getEnd();
					if (curEdge.getDirection() == Edge.DIR_EAST || curEdge.getDirection() == Edge.DIR_WEST){
						if (cur.getY() > otherEnd1.getY()){
							for(int i = cur.getY() - 1; i >= otherEnd1.getY(); i--){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getY() + 1; i <= otherEnd1.getY(); i++){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
					else{
						if (cur.getX() > otherEnd1.getX()){
							for(int i = cur.getX() - 1; i >= otherEnd1.getX(); i--){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getX() + 1; i <= otherEnd1.getX(); i++){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
				}
				else {
					Point otherEnd1 = curEdge.getStart();
					if (curEdge.getDirection() == Edge.DIR_EAST || curEdge.getDirection() == Edge.DIR_WEST){
						if (cur.getY() > otherEnd1.getY()){
							for(int i = cur.getY() - 1; i >= otherEnd1.getY(); i--){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getY() + 1; i <= otherEnd1.getY(); i++){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
				}
			}
			else if(sEdges.size() == 4){
				int oldDir = curEdge.getDirection();
				sEdges.remove(curEdge);
				for (int i = 0; i < sEdges.size(); i++) {
					curEdge = sEdges.get(i);
					Point other = curEdge.getStart();
					if (other.equals(cur))
						other = curEdge.getEnd();
					if (oldDir == Edge.DIR_EAST){
						if (other.getX() < cur.getX() && other.getY() == cur.getY())
							break;
					}
					else if (oldDir == Edge.DIR_WEST){
						if (other.getX() > cur.getX() && other.getY() == cur.getY())
							break;
					}
					else if (oldDir == Edge.DIR_SOUTH){
						if (other.getY() < cur.getY() && other.getX() == cur.getX())
							break;
					}
					else {
						if (other.getY() > cur.getY() && other.getX() == cur.getX())
							break;
					}
				}
				if (curEdge.getStart().equals(cur)){
					Point otherEnd1 = curEdge.getEnd();
					if (curEdge.getDirection() == Edge.DIR_EAST || curEdge.getDirection() == Edge.DIR_WEST){
						if (cur.getY() > otherEnd1.getY()){
							for(int i = cur.getY() - 1; i >= otherEnd1.getY(); i--){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getY() + 1; i <= otherEnd1.getY(); i++){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
					else{
						if (cur.getX() > otherEnd1.getX()){
							for(int i = cur.getX() - 1; i >= otherEnd1.getX(); i--){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getX() + 1; i <= otherEnd1.getX(); i++){
								Point temp = new Point(i,cur.getY());
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
				}
				else {
					Point otherEnd1 = curEdge.getStart();
					if (curEdge.getDirection() == Edge.DIR_EAST || curEdge.getDirection() == Edge.DIR_WEST){
						if (cur.getY() > otherEnd1.getY()){
							for(int i = cur.getY() - 1; i >= otherEnd1.getY(); i--){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
								
							}
						}
						else{
							for(int i = cur.getY() + 1; i <= otherEnd1.getY(); i++){
								Point temp = new Point(cur.getX(),i);
								ArrayList<Edge> tsEdges = getStandingEdges(temp);
								if (tsEdges.size() > 1){
									cur = temp;
									break;
								}
							}
						}
					}
				}
			}
		}
		return pa;
		//forlardan sonra break koyulursa hatalý edgelerde infinite loopa girmez
	}
	
	
}
