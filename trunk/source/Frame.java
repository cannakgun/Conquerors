import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;


public class Frame extends JFrame{
	
	private static Frame thisFrame = null;
	
	private JMenuBar menubar;
	private JLabel time;
	private JButton backButton;
	private boolean menuState;
	
	public Frame()
	{
		super("Conquerors");
		menuState = false;
	    menubar = new JMenuBar();
	    backButton = new JButton("Back");
	    menubar.add(backButton);
	    time = new JLabel("TEST");
	    menubar.add(Box.createHorizontalGlue());
	    menubar.add(time);
	    menubar.setVisible(false);
        setLocation(400, 0);
        setResizable(false);
	    setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GameManager gManager = new GameManager();
        add(gManager.getgEngine());
        pack();
        setVisible(true);
	}
	public void addMenu()
	{
		if (!menuState){
			menuState = true;
		    add(menubar, BorderLayout.NORTH);
		    menubar.setVisible(true);
		    pack();
		}
	}
	
	public void updateTime(String newTime){
		time.setText("Remaining Time: " + newTime + "  ");
	}
	
	public void removeMenu()
	{
		if (menuState){
			menuState = false;
			remove(menubar);
		}
	}
	
	public static Frame getFrame(){
		if (thisFrame == null)
			thisFrame = new Frame();
		return thisFrame;
	}

}
