import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputManager implements KeyListener, MouseListener{
	
	//properties
	private int velX = 0;
	private int velY = 0;
	private GameManager gManager;
	
	//methods
	public InputManager(GameManager gManager)
	{
		this.gManager = gManager;
	}
	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		int c = e.getKeyCode();
		
		if(c == KeyEvent.VK_LEFT)
		{
			velX = -2;
			velY = 0;
		}
		if(c == KeyEvent.VK_RIGHT)
		{
			velX = 2;
			velY = 0;
		}
		if(c == KeyEvent.VK_UP)
		{
			velX = 0;
			velY = -2;
		}
		if(c == KeyEvent.VK_DOWN)
		{
			velX = 0;
			velY = 2;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{		
		velX = 0;
		velY = 0;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 100 && e.getY() < 160)
		{
			//gManager.setLevel(gManager.getLevel());
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				GraphicEngine.setState(GraphicEngine.State.PlayGame);
		}
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 220 && e.getY() < 280)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				GraphicEngine.setState(GraphicEngine.State.selectLevel);

		}
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 340 && e.getY() < 400)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				GraphicEngine.setState(GraphicEngine.State.ViewHelp);

		}
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 460 && e.getY() < 520)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				GraphicEngine.setState(GraphicEngine.State.Settings);

		}
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 580 && e.getY() < 640)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				GraphicEngine.setState(GraphicEngine.State.viewHighScore);

		}
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 700 && e.getY() < 760)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				GraphicEngine.setState(GraphicEngine.State.ViewCredits);

		}
		if(e.getX() > 325 && e.getX() < 670 && e.getY() > 820 && e.getY() < 880)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				Frame.getFrame().dispose();

		}
		if(e.getX() > 60 && e.getX() < 190 && e.getY() > 220 && e.getY() < 340)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.selectLevel)
			{
				gManager.setLevel(1);
			}
		}
		if(e.getX() > 250 && e.getX() < 380 && e.getY() > 220 && e.getY() < 340)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.selectLevel)
			{
				gManager.setLevel(2);
			}
		}
		if(e.getX() > 440 && e.getX() < 570 && e.getY() > 220 && e.getY() < 340)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.selectLevel)
			{
				gManager.setLevel(3);
			}
		}
		if(e.getX() > 620 && e.getX() < 750 && e.getY() > 220 && e.getY() < 340)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.selectLevel)
			{
				gManager.setLevel(4);
			}
		}
		if(e.getX() > 810 && e.getX() < 940 && e.getY() > 220 && e.getY() < 340)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.selectLevel)
			{
				gManager.setLevel(5);
			}
		}
		if(e.getX() > 650 && e.getX() < 795 && e.getY() > 720 && e.getY() < 790)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.LevelFinish)
			{
				gManager.reset();
				gManager.setLevel(gManager.getLevel()+1);
				GraphicEngine.setState(GraphicEngine.State.PlayGame);
				
			}
		}
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
