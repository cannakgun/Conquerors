	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
public class GameFinish {


		//properties
		private GameManager gManager;
		private Rectangle gameFinish;
		private Rectangle scoreRectangle;
		//private Rectangle play;
		private int score;
	    private boolean calculateScore;

		
		//methods
		public GameFinish(GameManager gManager)
		{
			this.gManager = gManager;
			gameFinish = new Rectangle(270, 370, 450, 200);
			scoreRectangle = new Rectangle(350, 570, 300, 100);

		    calculateScore = false;

		}
		
		public void paint(Graphics2D g){
						
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.setColor(Color.BLACK);

			g.drawString("Congratulations!", gameFinish.x + 20, gameFinish.y+70);
			g.drawString("Game Completed", gameFinish.x + 20, gameFinish.y+170);
			
			if(!calculateScore)
				score = gManager.calculateScore();
			g.setFont(new Font("arial", Font.BOLD, 25));
			
			g.drawString("Your Score:  " + score  , scoreRectangle.x + 50, scoreRectangle.y+60);
			

			g.draw(gameFinish);
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


