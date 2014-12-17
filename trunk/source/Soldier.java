import java.util.Random;


public class Soldier extends GameObject{
	
	//properties
	private int speed;
	private Random rand;
	
	//methods
	public Soldier()
	{
		rand = new Random();
	}
	public void move(EdgeManager eManager)
	{
		//set soldier positions according to the random generator value.
	}

}
