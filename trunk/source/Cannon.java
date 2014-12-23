import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Cannon extends GameObject{
	
	public Cannon(Boolean sprite)
	{
		if(sprite)
		{
			try {
	            setSprite(ImageIO.read(new File("canon.png")));
	            
		         } catch (IOException e) 
		         {
		           System.out.println("hata! sprite yok");
		         }
			setWidht(50);
			setHeight(50);
			setPosX(10);
			setPosY(550);
		}

		else
		{
			try {
				setSprite(ImageIO.read(new File("reversecanon.png")));
		    	   
		        } catch (IOException e) 
				{
		    	  System.out.println("hata! sprite yok");
				}
			setWidht(50);
			setHeight(50);
			setPosX(940);
			setPosY(150);

		}

	}

}
