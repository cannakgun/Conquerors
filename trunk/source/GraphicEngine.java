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
    private BufferedImage line;
    private EntityManager eManager;
    private InputManager iManager;
    private ConqueredAreaDetector cAreaDetector;
    public static enum State {PlayGame, MainMenu, ViewHelp, selectLevel, viewHighScore, Settings, ViewCredits, GameOver};
    private long deathTime;
    private static State state;
    private MainMenu mMenu;
    private GameOver gOver;
    private ArrayList<Polygon> polygonList;
    private GameManager gManager;
   
	//methods
    public GraphicEngine(GameManager gManager, InputManager iManager, EntityManager eManager, MainMenu mMenu, ConqueredAreaDetector cAreaDetector, GameOver gOver)
    {
    	deathTime = -1;    	
	    
    	try {
			backImage = ImageIO.read(new File("back.jpg"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("sprite is missing");
			}
		try {
			line = ImageIO.read(new File("line.png"));
	    	   
	        } catch (IOException e) 
			{
	    	  System.out.println("line is missing");
			}
		

		font = new Font("Arial", Font.BOLD, 25);
		this.gManager = gManager;
		this.cAreaDetector = cAreaDetector;
		this.eManager = eManager;
		this.iManager = iManager;
		this.mMenu = mMenu;
		this.gOver = gOver;
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
			
	    	for(int i = 0; i < eManager.size(); i++)
			{
				g.drawImage(eManager.get(i).getSprite(), eManager.get(i).getPosX(), eManager.get(i).getPosY(), null);
				//g.drawRect(eManager.get(i).getPosX(), eManager.get(i).getPosY(), eManager.get(i).getWidht(), eManager.get(i).getHeight());
			} 
    	
	    	g.setFont(font);
	    	g.setColor(Color.BLACK);

	    	Frame.getFrame().updateTime(gManager.getRemaingTimeString());
	    	
	    	if(gManager.getRemainigTime() == 0)
	    	{
	    		onDeath();
	    	}
 		}
 		
 		else if(state == State.MainMenu)
 		{
 			Frame.getFrame().removeMenu();
 			mMenu.paint(g2d);
		}	
 		else if(state == State.GameOver)
 		{
 			gOver.paint(g2d);
 		}
    }
    

	public void addArea(ArrayList<Point> path)
    {
    	int [] xpoints = new int[path.size()];
    	int [] ypoints = new int[path.size()];
    	for (int i = 0; i < path.size(); i++) {
			Point p = path.get(i);
			xpoints[i] = p.getX();
			ypoints[i] = p.getY();
		}
    	Polygon p = new Polygon(xpoints, ypoints, path.size());
    	polygonList.add(p);
    	//path in baþýndan sonuna kadar edgeler üzerinde traverse et
    	//iki tane traversel path olucak; bunlardan kýsa olanýnný path ile birleþtir
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