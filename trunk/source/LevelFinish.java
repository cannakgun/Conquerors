	import java.awt.Color;
	import java.awt.Font;
	import java.awt.Graphics2D;
	import java.awt.Rectangle;
public class LevelFinish {


		//properties
		private GameManager gManager;
		private Rectangle levelFinish;
		private Rectangle scoreRectangle;
		private Rectangle play;
		private int score;
	    private boolean calculateScore;

		
		//methods
		public LevelFinish(GameManager gManager)
		{
			this.gManager = gManager;
			levelFinish = new Rectangle(300, 370, 400, 100);
			scoreRectangle = new Rectangle(350, 470, 300, 100);
			play = new Rectangle(650, 720, 145, 70);
		    calculateScore = false;

		}
		
		public void paint(Graphics2D g){
						
			g.setFont(new Font("arial", Font.BOLD, 50));
			g.setColor(Color.BLACK);

			g.drawString("Level Finished", levelFinish.x + 30, levelFinish.y+70);
			
			if(!calculateScore)
				score = gManager.calculateScore();
			g.setFont(new Font("arial", Font.BOLD, 25));
			
			g.drawString("Your Score:  " + score  , scoreRectangle.x + 50, scoreRectangle.y+60);
			
			g.drawString("Next Level", play.x + 10, play.y + 40);
			g.draw(levelFinish);
			g.draw(scoreRectangle);
			g.draw(play);
			calculateScore = true;

		}

		public boolean getCalculateScore() {
			return calculateScore;
		}

		public void setCalculateScore(boolean calculateScore) {
			this.calculateScore = calculateScore;
		}

	}


