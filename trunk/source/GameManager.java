
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class GameManager implements ActionListener{

        //properties
    private int maxTime;
    private int level;
    private Hero hero;
    private Soldier soldier1;
    private Soldier soldier2;
    private Soldier soldier3;
    private InputManager iManager;
    private GraphicEngine gEngine;
    private EntityManager entityManager;
    private EdgeManager edgeManager;
    private ConqueredAreaDetector cAreaDetector;
    private BonusManager bManager;
    private CollisionDetector collisionDetector;
    private MainMenu mMenu;
    private GameOver gOver;
    private Timer timer;
    private long lastMove;
    private long startTime;
    private int remainigTime;
    private static boolean setRemainigTimeBool;
    private static boolean setRemainingTimeMinus;
    
    //methods
    public GameManager()
    {
        mMenu = new MainMenu();
        gOver = new GameOver();
        level = 1;
        startTime = -1;
        remainigTime = -1;
        lastMove = -1;
        setRemainigTimeBool = false;
        setRemainingTimeMinus = false;
        edgeManager = new EdgeManager();
        hero = Hero.getHero();
        cAreaDetector = new ConqueredAreaDetector();
        iManager = new InputManager(this);
        entityManager = new EntityManager();
        bManager = new BonusManager(this);
        gEngine = new GraphicEngine(this, iManager, entityManager, mMenu,cAreaDetector,gOver);
        entityManager.addObject(hero);
        collisionDetector = new CollisionDetector(entityManager);
        timer = new Timer(10, this);
        timer.start();
    }
    
        public void actionPerformed(ActionEvent arg0) 
        {       
                if(startTime == -1 && GraphicEngine.getState() == GraphicEngine.State.PlayGame)
                {
                        setStartTime();
                        initializeLevel(1);
                        
                }
                
                for(int i = 0; i < entityManager.size(); i++)
                {
                        if(i == 0)
                        {
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
                        }
                }

                if(GraphicEngine.getState() == GraphicEngine.State.PlayGame)
                {
                    hero.move(iManager.getVelX(), iManager.getVelY(), edgeManager);
                	if (System.currentTimeMillis() - lastMove > 10)
                	{
                        soldier1.move(edgeManager);
                        soldier2.move(edgeManager);
                        if(level > 3 )
                        	soldier3.move(edgeManager);
                        lastMove = System.currentTimeMillis();
                	}
                }


                Point heroPos = new Point(hero.getPosX() + (hero.getHeight()/2),hero.getPosY() + (hero.getHeight()/2));
                
                cAreaDetector.process(edgeManager.isOnEdge(heroPos), heroPos, edgeManager, entityManager, gEngine);
                gEngine.repaint();
                if(GraphicEngine.getState() != GraphicEngine.State.GameOver && collisionDetector.checkCollision() && !(edgeManager.isOnEdge(heroPos)))
                {
                	gEngine.onDeath();
                }
                               
                
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
                else if(setRemainingTimeMinus)
                {
                	maxTime -= 10;
                	setRemainingTimeMinus = false;
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

        public static void setSetRemainigTimeBool(boolean setRemainigTimeBool1) 
        {
               setRemainigTimeBool = setRemainigTimeBool1;
        }
        public static void setRemainingTimeMinus(boolean setRemainigTime) 
        {
               setRemainingTimeMinus = setRemainigTime;
        }


        public GraphicEngine getgEngine() 
        {
                return gEngine;
        }
        public Hero getHero() 
        {
                return hero;
        }
        public void reset()
        {

        	for(int i = entityManager.size()-1; i > 0; i--)
        	{
        		entityManager.removeObject(entityManager.get(i));
        	}
        	for(int i = gEngine.getPolygonList().size()-1; i > -1; i--)
        	{
        		gEngine.getPolygonList().remove(gEngine.getPolygonList().get(i));
        	}
        	for(int i = cAreaDetector.getPath().size()-1; i > -1; i--)
        	{
        		cAreaDetector.getPath().remove(cAreaDetector.getPath().get(i));
        	}
        	for(int i = edgeManager.getEdgeList().size()-1; i > 3; i--)
        	{
        		edgeManager.getEdgeList().remove(edgeManager.getEdgeList().get(i));
        	}

        	startTime = -1;
			getHero().setPosX(500 - (getHero().getWidht()/2));
			getHero().setPosY(999 - (getHero().getWidht()/2));
			getHero().setLife(1);
        }
        public void initializeLevel(int level)
        {
        	if(level == 1)
        	{
        		maxTime = 210;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
   
        	}
        	if(level == 2)
        	{
        		maxTime = 180;
        		soldier1 = new Soldier();
                soldier2 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
   
        	}
        	if(level == 3)
        	{
        		maxTime = 150;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
   
        	}
        	if(level == 4)
        	{
        		maxTime = 120;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                soldier3 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
                entityManager.addObject(soldier3);
   
        	}
        	if(level == 5)
        	{
        		maxTime = 90;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                soldier3 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
                entityManager.addObject(soldier3);
   
        	}
        }

		public int getMaxTime()
		{
			return maxTime;	
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
		
}