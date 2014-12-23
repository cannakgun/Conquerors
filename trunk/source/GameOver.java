import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class GameOver {
	//properties
	private GameManager gManager;
	private Rectangle gameOver;
	private Rectangle scoreRectangle;
	private int score;

	
	//methods
	public GameOver(GameManager gManager)
	{
		this.gManager = gManager;
		gameOver = new Rectangle(340, 370, 310, 100);
		scoreRectangle = new Rectangle(340, 470, 310, 100);
		score = 0;

	}
	
	public void paint(Graphics2D g){

		g.setFont(new Font("arial", Font.BOLD, 50));
		g.setColor(Color.BLACK);

		g.drawString("Game Over", gameOver.x + 20, gameOver.y+70);
		
		g.setFont(new Font("arial", Font.BOLD, 25));
		
		g.drawString("Your Score:  " + score  , scoreRectangle.x + 60, scoreRectangle.y+60);

		g.draw(gameOver);
		g.draw(scoreRectangle);

	}

}
