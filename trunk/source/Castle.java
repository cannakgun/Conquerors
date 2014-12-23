import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Castle extends GameObject{
	
	public Castle()
	{
		try {
            setSprite(ImageIO.read(new File("castle.png")));
            
         } catch (IOException e) 
         {
           System.out.println("hata! sprite yok");
         }
		setWidht(100);
		setHeight(100);
		setPosX(500 - (getWidht()/2));
		setPosY(75 - (getWidht()/2));
	}

}
