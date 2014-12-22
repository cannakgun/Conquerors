
import java.awt.*;
public class CollisionDetector {
	private EntityManager eManager;


	public CollisionDetector(EntityManager eManager)
	{
		this.eManager = eManager;
	
	}
	public boolean checkCollision()
	{
		//checks the hero position and detects whether any other game object collides with hero
		// according to their current positions and height/width values.
		//if there is a collision then decrease the hero life.
		boolean check = false;
		Rectangle r1 = new Rectangle((eManager.get(0)).getPosX()+15, (eManager.get(0)).getPosY()+15, 60, 60);

		for(int i = 1; i < eManager.size(); i++)
		{
			Rectangle r2;
			if(i == 1)
			{
				r2 = new Rectangle((eManager.get(i)).getPosX()+5, (eManager.get(i)).getPosY()+5, 85, 85);
			}
			else
			{
				r2 = new Rectangle((eManager.get(i)).getPosX()+5, (eManager.get(i)).getPosY()+5, 35, 35);
			}
			
			if(r1.intersects(r2))
			{
				check = true;
				((Hero)(eManager.get(0))).decreaseLife();
				break;
			}
			
		}

		return check;
	}
	

}
