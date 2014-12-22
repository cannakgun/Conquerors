
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Wall extends GameObject{
	
	private Random rand;
	
	public Wall()
	{
		try {
            setSprite(ImageIO.read(new File("wall.jpg")));
            
         } catch (IOException e) 
         {
           System.out.println("hata! sprite yok");
         }
         rand = new Random();
         setWidht(50);
         setHeight(50);
         setPosX(rand.nextInt(750) + 50);
         setPosY(rand.nextInt(750) + 150);
	}

}