import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Cannonball extends GameObject {

	private int speed;
	private Random rand;
	private int walk;
	
	public Cannonball()
	{
		try {
	    	   setSprite(ImageIO.read(new File("cannonball.png")));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("hata! sprite yok");
			}
		setWidht(40);
		setHeight(40);
		setPosX(60);
		setPosY(550);
		speed = 3;
	}
	public void move(EdgeManager eManager)
	{

			setPosX(getPosX() + speed);
			if (getPosX() > 949)
			{
				setPosX(60);
				walk = 0;
				return;
			}
	
	}
	public void backMove(EdgeManager eManager)
	{
		setPosX(getPosX() - speed);
		if (getPosX() < 0)
		{
			setPosX(900);
			walk = 0;
			return;
		}
	}


}
