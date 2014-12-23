
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class GameManager implements ActionListener{

        //properties
    private int maxTime;
    private int level;
    private int coefficient;
    private Hero hero;
    private Castle castle;
    private Soldier soldier1;
    private Soldier soldier2;
    private Soldier soldier3;
    private Wall wall1;
    private Wall wall2;
    private Wall wall3;
    private Wall wall4;
    private Cannon cannon;
    private Cannon reverseCannon;
    private Cannonball cannonball;
    private Cannonball cannonball2;
    private Mine mine;
    private InputManager iManager;
    private GraphicEngine gEngine;
    private EntityManager entityManager;
    private EdgeManager edgeManager;
    private ConqueredAreaDetector cAreaDetector;
  //  private BonusManager bManager;
    private CollisionDetector collisionDetector;
    private MainMenu mMenu;
    private GameOver gOver;
    private LevelFinish lFinish;
    private GameFinish gFinish;
    private Timer timer;
    private long lastMove;
    private long startTime;
    private int remainigTime;
    private static boolean setRemainigTimeBool;
    private static boolean setRemainingTimeMinus;
    private boolean cannonSprite;
    private HighScoreFile highScore;

    
    //methods
    public GameManager()
    {
        mMenu = new MainMenu();
        gOver = new GameOver(this);
        lFinish = new LevelFinish(this);
        gFinish = new GameFinish(this);
        highScore  = new HighScoreFile();
        level = 1;
        startTime = -1;
        remainigTime = -1;
        lastMove = -1;
        setRemainigTimeBool = false;
        setRemainingTimeMinus = false;
        cannonSprite = true;
        edgeManager = new EdgeManager();
        hero = Hero.getHero();
        castle = new Castle();
        cAreaDetector = new ConqueredAreaDetector();
        iManager = new InputManager(this);
        entityManager = new EntityManager(this);
   //     bManager = new BonusManager(this);
        gEngine = new GraphicEngine(this, iManager, entityManager, mMenu,cAreaDetector,gOver,edgeManager, lFinish, gFinish);
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
                        initializeLevel(level);                        
                }
                

                if(GraphicEngine.getState() == GraphicEngine.State.PlayGame)
                {
                    hero.move(iManager.getVelX(), iManager.getVelY(), edgeManager);
                	if (System.currentTimeMillis() - lastMove > 10)
                	{
                        soldier1.move(edgeManager);
                        soldier2.move(edgeManager);
                        if(level == 3 )
                        {
                        	cannonball.move(edgeManager);
                        
                        }
                        if(level == 4)
                        {
                        	cannonball.move(edgeManager);
                        	soldier3.move(edgeManager);
                        }
                        if(level == 5)
                        {
                        	cannonball.move(edgeManager);
                        	soldier3.move(edgeManager);
                        	cannonball2.backMove(edgeManager);
                        }

                        lastMove = System.currentTimeMillis();
                	}
                    Point heroPos = new Point(hero.getPosX() + (hero.getHeight()/2),hero.getPosY() + (hero.getHeight()/2));              
                    cAreaDetector.process(edgeManager.isOnEdge(heroPos), heroPos, edgeManager, entityManager, gEngine);
                    if( collisionDetector.checkCollision() && !(edgeManager.isOnEdge(heroPos)))
                    {
                    	hero.decreaseLife();
                    	gEngine.onDeath();
                    }
                }

               
                gEngine.repaint();
                               
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
        
        	lFinish.setCalculateScore(false);
        	startTime = -1;
			getHero().setPosX(450);
			getHero().setPosY(950);
			getHero().setLife(1);
        }
        public void initializeLevel(int level)
        {
        	if(level == 1)
        	{
        		entityManager.addObject(castle);
        		coefficient = 1;
        		maxTime = 210;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
   
        	}
        	if(level == 2)
        	{
        		entityManager.addObject(castle);
        		coefficient = 2;
        		maxTime = 180;
        		soldier1 = new Soldier();
                soldier2 = new Soldier();
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
   
        	}
        	if(level == 3)
        	{
        		entityManager.addObject(castle);
        		coefficient = 3;
        		maxTime = 150;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                wall1 = new Wall();
                cannonball = new Cannonball();
                cannon = new Cannon(cannonSprite);
                entityManager.addObject(cannon);
                entityManager.addObject(cannonball);
                entityManager.addObject(wall1);
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
   
        	}
        	if(level == 4)
        	{
        		entityManager.addObject(castle);
        		coefficient = 4;
        		maxTime = 120;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                soldier3 = new Soldier();
                wall1 = new Wall();
                wall2 = new Wall();
                cannon = new Cannon(cannonSprite);
                cannonball = new Cannonball();
                entityManager.addObject(cannon);
                entityManager.addObject(cannonball);
                entityManager.addObject(wall1);
                entityManager.addObject(wall2);
                entityManager.addObject(soldier1);
                entityManager.addObject(soldier2);
                entityManager.addObject(soldier3);
   
        	}
        	if(level == 5)
        	{
        		entityManager.addObject(castle);
        		coefficient = 5;
        		maxTime = 90;
                soldier1 = new Soldier();
                soldier2 = new Soldier();
                soldier3 = new Soldier();
                wall1 = new Wall();
                wall2 = new Wall();
                wall3 = new Wall();
                mine = new Mine();
                cannon = new Cannon(cannonSprite);
                reverseCannon = new Cannon(!cannonSprite);
                cannonball = new Cannonball();
                cannonball2 = new Cannonball();
                cannonball2.setPosX(900);
                cannonball2.setPosY(150);
                entityManager.addObject(cannon);
                entityManager.addObject(reverseCannon);
                entityManager.addObject(mine);
                entityManager.addObject(cannonball2);
                entityManager.addObject(cannonball);
                entityManager.addObject(wall1);
                entityManager.addObject(wall2);
                entityManager.addObject(wall3);
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
		public int calculateScore()
		{
			int score = getRemainigTime();
			if(score <= 0)
				score = 0;
			else
				score = (score * coefficient);
			score = score + hero.getScore();
			hero.setScore(score + hero.getScore());
			
			return score;
			
		}
        public void deleteAllEnemies()
        {
        	for(int i =entityManager.size()-1;i > 1;i--)
        	{
        		entityManager.removeObjectByIndex(i);
        	}
        }
        
        public void resetTime(){
        	startTime = System.currentTimeMillis();
        }
        
        public void addWalls()
        {
        	wall4 = new Wall();
        	entityManager.addObject(wall4);
                	
        }
        public void lifePlus()
        {
        	hero.setLife(hero.getLife() + 1);
        }
        
        public void onObjDestroy(GameObject obj){
        	if (obj instanceof Castle)
        	{

        		if(level == 5)
        		{
        			highScore.changeScore(calculateScore()*2);
        			GraphicEngine.setState(GraphicEngine.State.GameFinish);
        		}
        		else
        			GraphicEngine.setState(GraphicEngine.State.LevelFinish);
        	}
        		
        		
        }
        public ArrayList<Integer> getHighScoreList()
      {
      	
			return highScore.getHighScores();
      	
      }
		
}