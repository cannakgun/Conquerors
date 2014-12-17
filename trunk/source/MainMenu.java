import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MainMenu {
	
	//properties
	private Rectangle playGameButton;
	private Rectangle viewHelpButton;
	private Rectangle levelSelectionButton;
	private Rectangle viewHighScoreButton;
	private Rectangle settingsButton;
	private Rectangle viewCreditsButton;
	private Rectangle exitButton;
	
	//methods
	public MainMenu()
	{
		playGameButton = new Rectangle(350, 150, 300, 100);
		viewHelpButton = new Rectangle(350, 270, 300, 100);
		exitButton = new Rectangle(350, 390, 300, 100);
	}
	
	public void paint(Graphics2D g){
		
		g.setFont(new Font("arial", Font.BOLD, 50));
		g.setColor(Color.BLACK);
		g.drawString("Play Game", playGameButton.x + 30, playGameButton.y+70);
		g.drawString("How to Play", viewHelpButton.x + 10, viewHelpButton.y+70);
		g.drawString("Exit", exitButton.x + 100, exitButton.y+70);
		
		g.draw(playGameButton);
		g.draw(viewHelpButton);
		g.draw(exitButton);
	}
	
}
