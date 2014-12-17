import java.util.ArrayList;


public class EntityManager {
	
	//properties
	private ArrayList<GameObject> currentGameObjects;

	//methods
	public EntityManager()
	{
		
		currentGameObjects = new ArrayList<GameObject>();
	}
	public void destroyEntities(ArrayList<Point> path)
	{
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
    
    public GameObject get(int i)
    {
    	return currentGameObjects.get(i);
    }
    
    public int size()
    {
    	return currentGameObjects.size();
    }

}
