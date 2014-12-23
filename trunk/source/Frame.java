import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;


public class Frame extends JFrame implements ActionListener{
	
	private static Frame thisFrame = null;
	
	private JMenuBar menubar;
	private JLabel timeLabel;
	private JButton backButton;
	private boolean menuState;
	private GameManager gManager; 
	
	private Frame()
	{
		super("Conquerors");
		menuState = false;
	    menubar = new JMenuBar();
	    ImageIcon menuIcon = new ImageIcon("mainmenubutton.png");
	    backButton = new JButton(menuIcon);
	    backButton.setBorder(BorderFactory.createEmptyBorder());
	    backButton.addActionListener(this);
	    menubar.add(backButton);
	    timeLabel = new JLabel();
	    menubar.add(Box.createHorizontalGlue());
	    menubar.add(timeLabel);
	    menubar.setVisible(false);
        setLocation(400, 0);
        setResizable(false);
	    setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gManager = new GameManager();
        add(gManager.getgEngine());
        pack();
        setVisible(true);
	}
	public void addMenu()
	{
		if (!menuState)
		{
			menuState = true;
		    add(menubar, BorderLayout.NORTH);
		    menubar.setVisible(true);
		    pack();
		}
	}
	
	public void updateTime(String newTime)
	{	
		if(gManager.getRemainigTime() <= 10 && gManager.getRemainigTime() >= 0)
		{
	    	timeLabel.setForeground(Color.RED);
		}
		else
			timeLabel.setForeground(Color.BLACK);

		if(GraphicEngine.getState() == GraphicEngine.State.PlayGame ||GraphicEngine.getState() == GraphicEngine.State.GameOver)
			timeLabel.setText("Remaining Time: " + newTime + "   ");
		else
			timeLabel.setText("");
	}
	
	public JLabel getTimeLabel() {
		return timeLabel;
	}
	public void setTimeLabel(JLabel timeLabel) {
		this.timeLabel = timeLabel;
	}
	public JButton getBackButton() {
		return backButton;
	}
	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}
	public void removeMenu()
	{
		if (menuState){
			menuState = false;
			remove(menubar);
			pack();
		}
	}
	
	public static Frame getFrame()
	{
		if (thisFrame == null)
			thisFrame = new Frame();
		return thisFrame;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == backButton)
		{
			if(GraphicEngine.getState() == GraphicEngine.State.PlayGame||GraphicEngine.getState() == GraphicEngine.State.GameOver ||GraphicEngine.getState() == GraphicEngine.State.LevelFinish)
			{
	        	gManager.getHero().setScore(0);
				gManager.setLevel(1);
				gManager.reset();
			}

			GraphicEngine.setState(GraphicEngine.State.MainMenu);
		}
		
	}

}
