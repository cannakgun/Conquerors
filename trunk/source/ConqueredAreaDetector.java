import java.awt.Polygon;
import java.util.ArrayList;

public class ConqueredAreaDetector{
	
	//properties
	private Point leave;
	private boolean onEdge;
	private ArrayList<Point> path;
	

	//methods
	public ConqueredAreaDetector()
	{
		leave = new Point(-1, -1);
		path = new ArrayList<Point>();
		onEdge = true;
	}
	
	public Point get(int i)
	{
		return path.get(i);
	}
	
	public int size()
	{
		return path.size();
	}
	
	public void process(boolean nextOnEdge, Point p, EdgeManager edgeManager, EntityManager entityManager, GraphicEngine gEngine)
	{
		if(onEdge)
		{
			if(nextOnEdge)
			{
				//update Point leave to Point p
				leave.setX(p.getX());
				leave.setY(p.getY());
			}
			else
			{
				//add Point p to ArrayList path
				path.add(new Point(leave.getX(),leave.getY()));
				path.add(p);
			}
		}
		else
		{
			if(nextOnEdge)
			{
				//!(leave.x == p.x && leave.y == p.y)
				//add Point p to ArrayList path 
				//EdgeManager ýn createEdge metodunu çaðýr(path) 
				//EntityManager ýn destroyEntities metodunu çaðýr(path)
				if(!leave.equals(p))
				{
					path.add(p);
					path = simplifyPath(path);
					ArrayList<Point> dupe = (ArrayList<Point>) path.clone();
					dupe = completePath(dupe, edgeManager);
					edgeManager.createEdges(dupe,path.size());
					dupe = simplifyPath(dupe);
					entityManager.destroyEntities(dupe);
					gEngine.addArea(dupe);
					path = new  ArrayList<Point>();
				}
					
			}
			else if (path.size() > 0)
			{
				//add Point p to ArrayList path
				Point lastP = path.get(path.size()-1);
				if(!lastP.equals(p))
				{
					for (int i = 0; i < path.size(); i++)
					{
						if (path.get(i).equals(p))
						{
							Hero h = (Hero)(entityManager.get(0));
							h.setPosX(leave.getX() - (h.getWidht()/2) );
							h.setPosY(leave.getY() - (h.getWidht()/2));
							onEdge = true;
							path = new  ArrayList<Point>();
							return;
						}
					}
					path.add(p);
				}
			}
		}
		onEdge = nextOnEdge;
	}
	
	public ArrayList<Point> simplifyPath(ArrayList<Point> path){
		ArrayList<Integer> trash = new ArrayList<Integer>();
		for (int i = 1; i < path.size() - 1; i++) {
			if (path.get(i-1).getX() - path.get(i).getX() == path.get(i).getX() - path.get(i+1).getX())
				if (path.get(i-1).getY() - path.get(i).getY() == path.get(i).getY() - path.get(i+1).getY())
					trash.add(i);
		}
		for (int i = trash.size() - 1; i >= 0; i--) {
			path.remove(trash.get(i).intValue());
		}
		return path;
	}
	
	public ArrayList<Point> completePath(ArrayList<Point> path, EdgeManager edgeManager){
		int s = -1;
		int e = -1;
		Polygon playArea = edgeManager.getPlayArea();
		for (int i = 0; i < playArea.npoints; i++) {
			int i2 = (i + 1) % playArea.npoints;
			Point p1 = new Point(playArea.xpoints[i],playArea.ypoints[i]);
			Point p2 = new Point(playArea.xpoints[i2],playArea.ypoints[i2]);
			Edge edge = new Edge(p1, p2, -1);
			if(edge.isOn(path.get(0)))
				s = i;
			if(edge.isOn(path.get(path.size() - 1)))
				e = i;
		}
		if (s != e){
			ArrayList<Point> line1 = new ArrayList<Point>();
			ArrayList<Point> line2 = new ArrayList<Point>();
			line1.add(path.get(path.size() - 1));
			line2.add(path.get(path.size() - 1));
			for (int i = (e + 1)  % playArea.npoints; i != (s + 1) % playArea.npoints; i = (i + 1) % playArea.npoints) {
				Point p = new Point(playArea.xpoints[i],playArea.ypoints[i]);
				line1.add(p);				
			}
			for (int i = e ; i != s; i = (i - 1 + playArea.npoints) % playArea.npoints) {
				Point p = new Point(playArea.xpoints[i],playArea.ypoints[i]);
				line2.add(p);				
			}
			line1.add(path.get(0));
			line2.add(path.get(0));
			double l1 = 0;
			double l2 = 0;
			for (int i = 0; i < line1.size() - 1; i++) {
				l1 += line1.get(i).getDistance(line1.get(i+1));
			}
			for (int i = 0; i < line2.size() - 1; i++) {
				l2 += line2.get(i).getDistance(line2.get(i+1));
			}

			if (l1 > l2){
				path.addAll(line2);
			}
			else {
				path.addAll(line1);
			}
		}
		else{
			path.add(path.get(0));
		}
		return path;
	}
	public ArrayList<Point> getPath() {
		return path;
	}

	public void setPath(ArrayList<Point> path) {
		this.path = path;
	}
	
}