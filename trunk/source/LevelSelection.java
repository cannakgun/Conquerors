import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class LevelSelection {

    //properties
    private Rectangle level1Button;
    private Rectangle level2Button;
    private Rectangle level3Button;
    private Rectangle level4Button;
    private Rectangle level5Button;
    
    //methods
   public LevelSelection() {
        level1Button = new Rectangle(60, 220, 130, 120);
        level2Button = new Rectangle(250, 220, 130, 120);
        level3Button = new Rectangle(440, 220, 130, 120);
        level4Button = new Rectangle(620, 220, 130, 120);
        level5Button = new Rectangle(810, 220, 130, 120);
	}
 
    
    public void paint(Graphics2D g){
            
            //g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.001f));
            g.draw(level1Button);
            g.draw(level2Button);
            g.draw(level3Button);
            g.draw(level4Button);
            g.draw(level5Button);
            
    }
}
