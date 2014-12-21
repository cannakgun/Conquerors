import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
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
	    backButton = new JButton("Main Menu");
	    backButton.addActionListener(this);
	    menubar.add(backButton);
	    timeLabel = new JLabel("TEST");
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
		timeLabel.setText("Remaining Time: " + newTime + "   ");
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
			menubar.setVisible(false);
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
			if(GraphicEngine.getState() == GraphicEngine.State.PlayGame)
			{

				gManager.reset();
				

			}
			else if(GraphicEngine.getState() == GraphicEngine.State.GameOver)
			{

				gManager.reset();
			}
			GraphicEngine.setState(GraphicEngine.State.MainMenu);
		}
		
	}

}
