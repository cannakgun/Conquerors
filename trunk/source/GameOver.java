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
    private boolean calculateScore;

	
	//methods
	public GameOver(GameManager gManager)
	{
		this.gManager = gManager;
		gameOver = new Rectangle(350, 270, 300, 100);
		scoreRectangle = new Rectangle(350, 470, 300, 100);
	    calculateScore = false;

	}
	
	public void paint(Graphics2D g){

		g.setFont(new Font("arial", Font.BOLD, 50));
		g.setColor(Color.BLACK);

		g.drawString("Game Over", gameOver.x + 10, gameOver.y+70);
		
		if(!calculateScore)
			score = gManager.calculateScore();
		g.setFont(new Font("arial", Font.BOLD, 25));
		
		g.drawString("Your Score:  " + score  , scoreRectangle.x + 40, scoreRectangle.y+60);

		g.draw(gameOver);
		g.draw(scoreRectangle);
		calculateScore = true;

	}

	public boolean getCalculateScore() {
		return calculateScore;
	}

	public void setCalculateScore(boolean calculateScore) {
		this.calculateScore = calculateScore;
	}

}
