import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Frame extends JFrame
{

	private Screen screen=new Screen();

	private int timerSpeed=100;
	private static int timerCount=0;
	private static double timeInSeconds=0;
	private DecimalFormat dFormat = new DecimalFormat("0.00");
	public static Timer timer;
	private JPanel topBar = new JPanel();
	private JLabel scoreLabel=new JLabel("Score: ");
	private JLabel timeLabel=new JLabel("Time: ");
	public KeyHandler KeyHandlerObject = new KeyHandler();
	public static int XBoundary=30*Screen.getBlockSize();
	public static int YBoundary=30*Screen.getBlockSize();
	Dimension screenSizes = Toolkit.getDefaultToolkit().getScreenSize();
	double screenHeights = screenSizes.height;
	double screenWidths = screenSizes.width;
	
	
	public Frame()
	{
		add(screen,BorderLayout.CENTER);
		add(topBar,BorderLayout.NORTH);
		topBar.add(scoreLabel);
		topBar.add(timeLabel);
		
		addKeyListener(KeyHandlerObject);
		
		timer = new Timer (timerSpeed,new TimerListener());
		timer.start();
		
		
		setSize(XBoundary,YBoundary);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setLocation(500,250);
			
	}
	
	
	private class KeyHandler extends KeyAdapter
	{
		public void keyPressed(KeyEvent event)
		{
			if (acceptableKeyInput(event.getKeyCode()))
			{
				System.out.print("         ");
				Screen.setDirection(event.getKeyCode());
				Screen.move();
				repaint();
				scoreLabel.setText("Score: "+Screen.getScore());
			}
		}	
		
		public boolean acceptableKeyInput(int newD)
		{
			if ((Screen.getDirection()==38||Screen.getDirection()==40)&&(newD==38||newD==40))
			{
				return false;
			}
			else if ((Screen.getDirection()==37||Screen.getDirection()==39)&&(newD==37||newD==39))
				return false;
			else
				return true;
		}
	}
	
	private class TimerListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if (Screen.alive())
			{
				System.out.print(timerCount+"        ");
				Screen.move();
				if (Screen.alive())
					repaint();
				scoreLabel.setText("Score: "+Screen.getScore());
				timeLabel.setText("Time: "+ dFormat.format(timeInSeconds));
				
				timeInSeconds+=((double)timerCount/(1000.0));
				timerCount++;
			}
			else
			{
				timer.stop();
				System.out.print("LOSE");			
				new PostGame();				
				
			}
		}
		
	}
	
    	public void closeFrame()
    	{
    		WindowEvent wev = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
    		Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
    	}

		public void close()
		{
			dispose();			
		}

		public static void restart() 
		{
			timerCount=0;
			timeInSeconds=0;
			/*
			Screen.resetHeadLocation();
			Screen.resetAllPoints();	
			Screen.resetScore();
			Screen.resetblockCount();
			Screen.setDirection(38);		
			*/
		}
}
