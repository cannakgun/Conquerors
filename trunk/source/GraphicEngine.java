import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GraphicEngine extends JPanel{

	//properties
	private Font font;;
    private BufferedImage backImage;
    private BufferedImage mainBack;
    private BufferedImage line;
    private BufferedImage creditsBack;
    private BufferedImage scoresBack;
    private BufferedImage settingsBack;
    private BufferedImage levelBack;
    private EntityManager eManager;
    private InputManager iManager;
    private EdgeManager edgeManager;
    private ConqueredAreaDetector cAreaDetector;
    public static enum State {PlayGame, MainMenu, ViewHelp, selectLevel, viewHighScore, Settings, ViewCredits, GameOver};
    private long deathTime;
    private static State state;
    private MainMenu mMenu;
    private GameOver gOver;
    private ArrayList<Polygon> polygonList;
    private GameManager gManager;
   
	//methods
    public GraphicEngine(GameManager gManager, InputManager iManager, EntityManager eManager, MainMenu mMenu, ConqueredAreaDetector cAreaDetector, GameOver gOver, EdgeManager edManager)
    {
    	deathTime = -1;    
	    
    	try {
			backImage = ImageIO.read(new File("back.jpg"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("back is missing");
			}
		try {
			line = ImageIO.read(new File("line.png"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("line is missing");
			}
		try {
			mainBack = ImageIO.read(new File("back2.jpg"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("back2 is missing");
			}
		try {
			creditsBack = ImageIO.read(new File("credits.png"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("credits is missing");
			}
		try {
			scoresBack = ImageIO.read(new File("scores.png"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("scores is missing");
			}
		try {
			settingsBack = ImageIO.read(new File("settings.png"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("settings is missing");
			}
		try {
			levelBack = ImageIO.read(new File("level.png"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("level is missing");
			}
		
		
		

		font = new Font("Arial", Font.BOLD, 25);
		this.gManager = gManager;
		this.cAreaDetector = cAreaDetector;
		this.eManager = eManager;
		this.iManager = iManager;
		this.mMenu = mMenu;
		this.gOver = gOver;
		edgeManager = edManager;
    	setPreferredSize(new Dimension(1000,1000));
		addKeyListener(iManager);
		addMouseListener(iManager);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		state = State.MainMenu;
		polygonList = new ArrayList<Polygon>();

    }
    
   
    @Override
	public void paint(Graphics g) 
	{
    	super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
 		if(state == State.PlayGame || (state == State.GameOver && System.currentTimeMillis() - deathTime < 2000))
 		{
 			Frame.getFrame().addMenu();
 			g.drawImage(backImage, 0, 0, getWidth(), getHeight(), this);
 			g.setColor(new Color(1.0f, 0.0f, 0.0f, 0.75f));
	    	for(int i = 0; i < polygonList.size(); i++)
			{	    		
				g.fillPolygon(polygonList.get(i));
			} 
	    	for(int i = 0; i < cAreaDetector.size(); i++)
	    	{
	    		Point p = cAreaDetector.get(i);
				g.drawImage(line, p.getX()-2, p.getY()-2,null);	
	    	}
 			g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.55f));
			g.fillPolygon(edgeManager.getPlayArea());
	    	for(int i = 0; i < eManager.size(); i++)
			{
				g.drawImage(eManager.get(i).getSprite(), eManager.get(i).getPosX(), eManager.get(i).getPosY(), null);
				//g.drawRect(eManager.get(i).getPosX(), eManager.get(i).getPosY(), eManager.get(i).getWidht(), eManager.get(i).getHeight());
			} 
   	
	    	g.setFont(font);
	    	g.setColor(Color.BLACK);

	    	if(gManager.getRemainigTime() >= 0)
	    	{
	    		Frame.getFrame().updateTime(gManager.getRemaingTimeString());
	    	}
	    	
	    	
	    	if(gManager.getRemainigTime() == 0)
	    	{
	    		onDeath();
	    	}
 		}
 		else if(state == State.ViewCredits)
 		{
 			Frame.getFrame().addMenu();
 			Frame.getFrame().updateTime(gManager.getRemaingTimeString());
 			g.drawImage(creditsBack, 0, 0, getWidth(), getHeight(), this);
 		}
 		else if(state == State.viewHighScore)
 		{
 			Frame.getFrame().addMenu();
 			Frame.getFrame().updateTime(gManager.getRemaingTimeString());
 			g.drawImage(scoresBack, 0, 0, getWidth(), getHeight(), this);
 		}
 		else if(state == State.Settings)
 		{
 			Frame.getFrame().addMenu();
 			Frame.getFrame().updateTime(gManager.getRemaingTimeString());
 			g.drawImage(settingsBack, 0, 0, getWidth(), getHeight(), this);
 		}
 		else if(state == State.selectLevel)
 		{
 			Frame.getFrame().addMenu();
 			Frame.getFrame().updateTime(gManager.getRemaingTimeString());
 			g.drawImage(levelBack, 0, 0, getWidth(), getHeight(), this);
 		}
 		else if(state == State.MainMenu)
 		{
 			Frame.getFrame().removeMenu();
 			g.drawImage(mainBack, 0, 0, getWidth(), getHeight(), this);
 			mMenu.paint(g2d);
		}	
 		else if(state == State.GameOver)
 		{
 			if(((Hero)(eManager.get(0))).getLife() < 1)
 			{
 				gOver.paint(g2d);
 			}
 				
 			else
 			{
 				gManager.getHero().setPosX(450);
 				gManager.getHero().setPosY(950);
 				gManager.resetTime();
 	        	for(int i = getPolygonList().size()-1; i > -1; i--)
 	        	{
 	        		getPolygonList().remove(getPolygonList().get(i));
 	        	}
 	        	for(int i = cAreaDetector.getPath().size()-1; i > -1; i--)
 	        	{
 	        		cAreaDetector.getPath().remove(cAreaDetector.getPath().get(i));
 	        	}
 	        	for(int i = edgeManager.getEdgeList().size()-1; i > 3; i--)
 	        	{
 	        		edgeManager.getEdgeList().remove(edgeManager.getEdgeList().get(i));
 	        	}
 				state = State.PlayGame;

 			}
 								
 		}
    }
    

	public void addArea(ArrayList<Point> path)
    {
    	Polygon p = new Polygon();
    	for (int i = 0; i < path.size(); i++) {
			Point point = path.get(i);
			p.addPoint(point.getX(), point.getY());
		}
    	polygonList.add(p);
    	//path in ba��ndan sonuna kadar edgeler �zerinde traverse et
    	//iki tane traversel path olucak; bunlardan k�sa olan�nn� path ile birle�tir
    	//polygon yarat polygon ArrayList ine ekle
    }
    
	public static State getState() {
		return state;
	}


	public static void setState(State aState) {
		state = aState;
	}
	
	public void onDeath(){
		state = State.GameOver;
		deathTime = System.currentTimeMillis();

	}
    public ArrayList<Polygon> getPolygonList() {
		return polygonList;
	}


	public void setPolygonList(ArrayList<Polygon> polygonList) {
		this.polygonList = polygonList;
	}

}