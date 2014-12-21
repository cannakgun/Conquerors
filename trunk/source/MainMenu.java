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
                playGameButton = new Rectangle(325, 100, 345, 60);
                levelSelectionButton = new Rectangle(325, 220, 345, 60);
                viewHelpButton = new Rectangle(325, 340, 345, 60);
                settingsButton = new Rectangle(325, 460, 345, 60);
                viewHighScoreButton = new Rectangle(325, 580, 345, 60);
                viewCreditsButton = new Rectangle(325, 700, 345, 60);
                exitButton = new Rectangle(325, 820, 345, 60);
        }
        
        public void paint(Graphics2D g){
                
                g.setFont(new Font("arial", Font.BOLD, 50));
                g.setColor(Color.BLACK);
                g.drawString("Play Game", playGameButton.x + 30, playGameButton.y+70);
                g.drawString("How to Play", viewHelpButton.x + 10, viewHelpButton.y+70);
                g.drawString("Exit", exitButton.x + 100, exitButton.y+70);
                
                g.draw(playGameButton);
                g.draw(levelSelectionButton);
                g.draw(viewHelpButton);
                g.draw(settingsButton);
                g.draw(viewHighScoreButton);
                g.draw(viewCreditsButton);
                g.draw(exitButton);
        }
        
}

