import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class PostGame extends JFrame
{
	private static JPanel postGamePanel;
	private static JButton playAgainButton;
	public Frame newFrame;
	
	public PostGame()
	{
		setSize(Frame.XBoundary,Frame.YBoundary);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocation(500,250);
		
		postGamePanel = new JPanel();
		playAgainButton = new JButton ("Play Again");
		postGamePanel.add(playAgainButton);
		add(postGamePanel);
		
		playAgainButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent event)
				{
					Main.startFrame.close();
					dispose();
					newFrame = new Frame();	
					//Frame.restart();
				}				
			}				
		);
	}
	
}
