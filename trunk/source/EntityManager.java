import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.ArrayList;


public class EntityManager {
	
	//properties
	private ArrayList<GameObject> currentGameObjects;
	private GameManager gManager;

	//methods
	public EntityManager(GameManager gManager)
	{
		this.gManager = gManager;
		currentGameObjects = new ArrayList<GameObject>();
	}
	public void destroyEntities(ArrayList<Point> path)
	{
    	Polygon p = new Polygon();
    	for (int i = 0; i < path.size(); i++) {
			Point point = path.get(i);
			p.addPoint(point.getX(), point.getY());
		}
    	for (int i = currentGameObjects.size() - 1; i > 0; i--) {
    		GameObject cur = currentGameObjects.get(i);
    		if (!(cur instanceof Cannonball)){
				Rectangle border = new Rectangle(cur.getPosX(),cur.getPosY(),cur.getWidht(),cur.getHeight());
				if (p.intersects(border))
				{
					gManager.onObjDestroy(cur);
					currentGameObjects.remove(i);
				}
    		}
			
		}
		//destroy all enemy entities that are in the area under the points in the path ArrayList
		//call applySpeciality function for each object that are in the area.
	}
    public void addObject(GameObject gObject)
    {
    	currentGameObjects.add(gObject);
    }
    
    public void removeObject(GameObject gObject)
    {
    	currentGameObjects.remove(gObject);
    }
    public void removeObjectByIndex(int i)
    {
        currentGameObjects.remove(i);
    }
    
    public GameObject get(int i)
    {
    	return currentGameObjects.get(i);
    }
    public int getWidth(int i)
    {
    	return currentGameObjects.get(i).getWidht();
    }
    
    public int size()
    {
    	return currentGameObjects.size();
    }

}
