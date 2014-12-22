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
			gManager.setLevel(gManager.getLevel());
			GraphicEngine.setState(GraphicEngine.State.PlayGame);
		}
		else if(e.getX() > 325 && e.getX() < 670 && e.getY() > 220 && e.getY() < 280)
		{
			GraphicEngine.setState(GraphicEngine.State.selectLevel);

		}
		else if(e.getX() > 325 && e.getX() < 670 && e.getY() > 340 && e.getY() < 400)
		{
			GraphicEngine.setState(GraphicEngine.State.ViewHelp);

		}
		else if(e.getX() > 325 && e.getX() < 670 && e.getY() > 460 && e.getY() < 520)
		{
			GraphicEngine.setState(GraphicEngine.State.Settings);

		}
		else if(e.getX() > 325 && e.getX() < 670 && e.getY() > 580 && e.getY() < 640)
		{
			GraphicEngine.setState(GraphicEngine.State.viewHighScore);

		}
		else if(e.getX() > 325 && e.getX() < 670 && e.getY() > 700 && e.getY() < 760)
		{
			GraphicEngine.setState(GraphicEngine.State.ViewCredits);

		}
		else if(e.getX() > 325 && e.getX() < 670 && e.getY() > 820 && e.getY() < 880)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.MainMenu)
				Frame.getFrame().dispose();

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
