import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameManager implements ActionListener{

	//properties
	private int maxTime; 
    private Hero hero;
    private InputManager iManager;
    private GraphicEngine gEngine;
    private EntityManager entityManager;
    private EdgeManager edgeManager;
    private ConqueredAreaDetector cAreaDetector;
    private BonusManager bManager;
    private CollisionDetector collisionDetector;
    private MainMenu mMenu;
    private Timer timer;
    private long startTime;
    private int remainigTime;
	private boolean setRemainigTimeBool;
    
    //methods
    public GameManager()
    {
    	mMenu = new MainMenu();
    	startTime = -1;
    	remainigTime = -1;
    	maxTime = 180;
    	setRemainigTimeBool = false;
    	edgeManager = new EdgeManager();
    	hero = Hero.getHero();
    	cAreaDetector = new ConqueredAreaDetector();
    	collisionDetector = new CollisionDetector(entityManager);
    	iManager = new InputManager();
    	entityManager = new EntityManager();
    	bManager = new BonusManager(this);
    	gEngine = new GraphicEngine(this, iManager, entityManager, mMenu,cAreaDetector);
    	entityManager.addObject(hero);
        timer = new Timer(5, this);
        timer.start();
    }
    
	public void actionPerformed(ActionEvent arg0) 
	{	
		if(startTime == -1)
		{
			setStartTime();
		}

		if(hero.getPosX() < 0-hero.getWidht()/2)
		{
			 hero.setPosX(0-hero.getWidht()/2);
			 iManager.setVelX(0);
		}
		if(hero.getPosY() < 0-hero.getWidht()/2)
		{
			 hero.setPosY(0-hero.getWidht()/2);
			 iManager.setVelY(0);
		}
		if(hero.getPosX() > 999-(hero.getWidht()/2)) 
		{
			hero.setPosX(999-(hero.getWidht()/2));
			iManager.setVelX(0);
		}
		if(hero.getPosY() > 999-(hero.getWidht()/2)) 
		{
			hero.setPosY(999-(hero.getWidht()/2));
			iManager.setVelY(0);
		}
		if(GraphicEngine.getState() == GraphicEngine.State.PlayGame)
			hero.move(iManager.getVelX(), iManager.getVelY(), edgeManager);

		Point heroPos = new Point(hero.getPosX() + (hero.getHeight()/2),hero.getPosY() + (hero.getHeight()/2));
		cAreaDetector.process(edgeManager.isOnEdge(heroPos), heroPos, edgeManager, entityManager, gEngine);
		gEngine.repaint();
		
		//System.out.printf(getRemaingTimeString()); => DONE
		
		//check hero life to continue PlayGame state or go to GameOver state!!!! 
	}
	public void setStartTime()
	{
		startTime = System.currentTimeMillis();
	}
	public int getRemainigTime()
	{		
		if(setRemainigTimeBool)
		{
			maxTime += 10;
			setRemainigTimeBool = false;
		}
		remainigTime = (int) (maxTime - (System.currentTimeMillis() - startTime)/1000);		
		return remainigTime;
	}
	
	public String getRemaingTimeString()
	{
		String s ="";
		s = s.format("%d:%02d\n", getRemainigTime()/60, getRemainigTime()%60);
		return s;
	}

	public void setSetRemainigTimeBool(boolean setRemainigTimeBool) {
		this.setRemainigTimeBool = setRemainigTimeBool;
	}

	public GraphicEngine getgEngine() {
		return gEngine;
	}

}
