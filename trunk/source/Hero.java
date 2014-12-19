import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Hero extends GameObject{
	
	//properties
	private int speed;
	private int life;
	private static Hero h;
	private int heroType;

	
	//methods
	private  Hero()
	{
		try {
	    	   setSprite(ImageIO.read(new File("shield.png")));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("hata sprite yok");
			}
		speed = 2;
		setWidht(100);
		setHeight(100);
		setPosX(500 - (getWidht()/2));
		setPosY(999 - (getWidht()/2));
	}
    public void move(int velX, int velY, EdgeManager eManager) {
    	
    	Point newPoint = new Point(getPosX() + (speed * velX) + getWidht()/2,getPosY() + (speed * velY) +  getHeight()/2);
    	boolean canMove = eManager.isPositionValid(newPoint);
    	
    	
    	if (canMove)
    	{
	    	setPosX(getPosX() + (speed * velX));
	    	setPosY(getPosY() + (speed * velY));
    	}
    } 
    
	public static Hero getHero()
	{
		if(h == null )
		{
			return h = new Hero ();
		}
		else
			return h;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}

	public void increaseLife()
	{
		life = life +1;
	}
	public void decreaseLife()
	{
		if(life > 0)
			life = life - 1;
	}
	public void setHeroType(int heroType)
	{
		this.heroType = heroType;
	}

}
