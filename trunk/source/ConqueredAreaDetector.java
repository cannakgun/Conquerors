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
				path.add(leave);
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
					edgeManager.createEdges(path);
					entityManager.destroyEntities(path);
					gEngine.addArea(path);
					path = new  ArrayList<Point>();
				}
					
			}
			else
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
	public ArrayList<Point> getPath() {
		return path;
	}

	public void setPath(ArrayList<Point> path) {
		this.path = path;
	}
	
}