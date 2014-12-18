import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class GameOver {
	//properties
	private Rectangle gameOver;

	
	//methods
	public GameOver()
	{

		gameOver = new Rectangle(350, 270, 300, 100);

	}
	
	public void paint(Graphics2D g){
		
		g.setFont(new Font("arial", Font.BOLD, 50));
		g.setColor(Color.BLACK);

		g.drawString("Game Over", gameOver.x + 10, gameOver.y+70);

		g.draw(gameOver);

	}

}
