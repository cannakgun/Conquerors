import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class Mine extends GameObject {
	
	private Random rand;
	public Mine()
	{
		try {
	    	   setSprite(ImageIO.read(new File("mine.png")));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("hata! sprite yok");
			}
		setWidht(40);
		setHeight(40);
		rand = new Random();
	    setPosX(rand.nextInt(450) + 150);
	    setPosY(rand.nextInt(450) + 150);
//		setPosX(450 - (getWidht()/2));
//		setPosY(175 - (getWidht()/2));
		

	}

}
