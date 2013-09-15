import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main extends JFrame
{
	public static void main (String [] args)
	{
		Main mainScreen = new Main();
		mainScreen.setSize(200,200);
		mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainScreen.setVisible(true);
		mainScreen.setLocation(500, 250);
		
	}
	
	private JPanel mainPanel;
	private JButton startButton;
	public static Frame startFrame;
	
	public Main()
	{
		mainPanel = new JPanel();
		startButton = new JButton ("Start");
		mainPanel.add(startButton);
		add(mainPanel);
		
		startButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					startFrame = new Frame();	
					dispose();
				}
				
			}
				
		);
	}
}
