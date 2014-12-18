import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Soldier extends GameObject{
	
	//properties
	private int speed;
	private Random rand;
	private boolean walk;
	private int direction;
	
	//methods
	public Soldier()
	{
		try {
	    	   setSprite(ImageIO.read(new File("soldier.png")));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("hata! sprite yok");
			}
		rand = new Random();
		setWidht(100);
		setHeight(100);
		setPosX(rand.nextInt(750));
		setPosY(rand.nextInt(750));
		walk = false;
		direction = -1;
	}
	public void move(EdgeManager eManager)
	{
		//set soldier positions according to the random generator value.
		if(!walk)
		{
			direction = rand.nextInt(4)+1;
			walk = true;
		}
		else if(walk)
		{
			//System.out.println(direction);
			if(direction == 1)
			{
				int i = getPosY();
				int y = getPosY() - 50;
				while(i > y)
				{
					setPosY(getPosY() + (-1));
					y = y+1;
				}
				
			}
			else if(direction == 2)
			{
				int i = getPosX();
				int y = getPosX() + 50;
				while(i < y)
				{
					setPosX(getPosX() + (1));
					i = i+1;
				}
				
			}
			else if(direction == 3)
			{
				int i = getPosY();
				int y = getPosY() + 50;
				while(i < y)
				{
					setPosY(getPosY() + (1));
					i = i+1;
				}
				
			}
			else if(direction == 4)
			{

				int i = getPosX();
				int y = getPosX() - 50;
				while(i > y)
				{
					setPosX(getPosX() + (-1));
					y = y+1;
				}
				
			}

			walk = false;
			
		}  	
	}

}
