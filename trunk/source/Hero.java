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
		setPosX(500 - (getWidht()/2) );
		setPosY(999 - (getWidht()/2));
	}
    public void move(int velX, int velY, EdgeManager eManager) 
    {
    	//gitmemesi gereken yerlere gitmediðini kontrol et
    	
    	int curDir = -1;
    	if (velX == 0)
    	{
    		if (velY > 0)
    		{
    			curDir = Edge.DIR_SOUTH;
    		}
    		else 
    		{
    			curDir = Edge.DIR_NORTH;
    		}
    	}
    	else if (velX > 0)
    	{
			curDir = Edge.DIR_EAST;
    	}
    	else
    	{
			curDir = Edge.DIR_WEST;
    	}
    	boolean canMove = true;
    	ArrayList<Edge> standingEdges = eManager.getStandingEdges(new Point(getPosX(),getPosY()));
    	
    	//DEÐÝÞECEK
    	if(standingEdges.size() == 1)
    	{
    		canMove = curDir != standingEdges.get(0).getDirection();
    	}
    	else if(standingEdges.size() == 2)
    	{
    		
    	}
    	else
    	{
	    	for (int i = 0; i < standingEdges.size(); i++)
	    	{
				if (standingEdges.get(i).getDirection() == curDir)
				{
					canMove = false;
					break;
				}
			}
    	}
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
