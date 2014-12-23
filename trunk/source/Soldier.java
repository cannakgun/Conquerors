import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Soldier extends GameObject{
	
	//properties
	private int speed;
	private Random rand;
	private int walk;
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
		setWidht(50);
		setHeight(50);
		setPosX(rand.nextInt(750));
		setPosY(rand.nextInt(750));
		speed = 5;
		walk = 0;
		direction = -1;
	}
	public void move(EdgeManager eManager)
	{
		//set soldier positions according to the random generator value.
		if(walk <= 0)
		{
			direction = rand.nextInt(4)+1;
			walk = 20;
		}
		else
		{
			//System.out.println(direction);
			Rectangle border = new Rectangle(getPosX(),getPosY(),getWidht(),getHeight());
			if(direction == 1){
				border.y = border.y - speed;
				/*setPosY(getPosY() - speed);
				if (getPosY() < 0){
					setPosY(0);
					walk = 0;
					return;
				}*/
			}
			else if(direction == 2){
				border.x = border.x + speed;
				/*setPosX(getPosX() + speed);
				if (getPosX() > 949){
					setPosX(949);
					walk = 0;
					return;
				}*/
			}
			else if(direction == 3){
				border.y = border.y + speed;
				/*setPosY(getPosY() + speed);
				if (getPosY() > 949){
					setPosY(949);
					walk = 0;
					return;
				}*/
			}
			else if(direction == 4){
				border.x = border.x - speed;
				/*setPosX(getPosX() - speed);
				if (getPosX() < 0){
					setPosX(0);
					walk = 0;
					return;
				}*/
			}
			walk--;
			Polygon play = eManager.getPlayArea();
			for (int i = 0; i < play.npoints; i++) {
				if (border.intersectsLine(play.xpoints[i], play.ypoints[i], play.xpoints[(i + 1) % play.npoints], play.ypoints[(i + 1) % play.npoints]))
					return;				
			}
			setPosX(border.x);
			setPosY(border.y);
			
		}  	
	}

}
