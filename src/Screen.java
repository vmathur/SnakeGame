import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JPanel;

public class Screen extends JPanel
{
	
	public static int direction=38;	
	private static ArrayList <Point> allPoints= new ArrayList<Point>();
	private static ArrayList<Integer> allDirections = new ArrayList<Integer>();
	
	private static int blockCount=0;	
	private static int blockSize=10;
	
	private static Point headlocation = new Point(100,100);	
	private static Point pointLocation = new Point (140,40);
	
	private static int score;
	
	public Screen()
	{
		setBackground(Color.WHITE);
		
		for (int i=0;i<=2;i++)
		{
			allPoints.add(new Point ( (int)headlocation.getX(), (int)headlocation.getY()+((i+1)*blockSize)));
			allDirections.add(direction);
			blockCount++;
		}

	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	
		g.setColor(Color.RED);
		g.fillRect((int) pointLocation.getX(),(int) pointLocation.getY(), blockSize,blockSize);	
				
		g.setColor(new Color(34,139,34));
		g.drawLine((int) pointLocation.getX(),(int) pointLocation.getY(),(int) pointLocation.getX()+5,(int) pointLocation.getY()+5);
		g.drawLine((int) pointLocation.getX()+5,(int) pointLocation.getY()+5,(int) pointLocation.getX()+10,(int) pointLocation.getY());
		
		g.setColor(Color.BLUE);
		g.fillRect((int)headlocation.getX(), (int)headlocation.getY(), blockSize, blockSize);
			
		for (int i = 0;i<(blockCount-1);i++)
		{		
			g.setColor(Color.BLACK);
			g.fillRect((int)allPoints.get(i).getX(), (int)allPoints.get(i).getY(), blockSize, blockSize);
		}	
		
		g.setColor(Color.GREEN);
		g.fillRect((int) allPoints.get(blockCount-1).getX(), (int) allPoints.get(blockCount-1).getY(), blockSize, blockSize);
		
	}

	public static void move ()
	{
		for (int i=(blockCount-1); i>0;--i)
		{
				allPoints.set(i,new Point(allPoints.get(i-1)));
			//	allDirections.set(i, new Integer(allDirections.get(i-1)));
		}
		
		int tempDirection = direction;
		allPoints.get(0).setLocation(new Point(headlocation)); 
	//	allDirections.set(0,tempDirection);
		
		int X_modifier=0;
		int Y_modifier=0;
		
		if (direction==37)
			 X_modifier=-blockSize;
		else if (direction==39)
			 X_modifier=blockSize;
		else if (direction==38)
			Y_modifier=-blockSize;
		else if (direction==40)
			Y_modifier=blockSize;		
		
		
		headlocation.setLocation( headlocation.getX()+X_modifier,  headlocation.getY()+Y_modifier);
		System.out.print("X: "+headlocation.getX()+" Y: "+headlocation.getY()+"     ");
		System.out.println("X: "+pointLocation.getX()+" Y: "+pointLocation.getY()+"     ");
		scorepoints();		
	}
	
	public static void scorepoints()
	{
		if (headlocation.getX()==pointLocation.getX()&&headlocation.getY()==pointLocation.getY())
		{
			allPoints.add(newPointLocation());
			blockCount++;
			score++;
			System.out.println("Score!");
			relocate();
		}
	}
			
	public static void relocate ()
	{
	
		do
		{
			Random generator = new Random();
			pointLocation.setLocation(generator.nextInt(21)*blockSize,generator.nextInt(16)*blockSize);
		}while(pointTaken());
	}
	
	public static Point newPointLocation()
	{
		return new Point(500,500);
	}
	
	public static boolean alive ()
	{
		if (headlocation.getX()<0||headlocation.getX()>Frame.XBoundary-20||
			headlocation.getY()<0||headlocation.getY()>Frame.YBoundary-60)
			return false;
		
		else if (hitItself())
			return false;

		else 
			return true;
	}
	
	public static boolean hitItself()
	{
		for (int i=0;i<blockCount;i++)
		{
			if ((headlocation.getX()==allPoints.get(i).getX())&&(headlocation.getY()==allPoints.get(i).getY()))
				return true;
		}
		return false;
	}
	
	public static boolean pointTaken()
	{
		if ((pointLocation.getX()==headlocation.getX())&&(pointLocation.getY()==headlocation.getY()))
			return true;
		
		for (int i=0;i<blockCount;i++)
		{
			if ((pointLocation.getX()==allPoints.get(i).getX())&&(headlocation.getY()==(allPoints.get(i).getY())))
				return true;
		}
		
		return false;		
	}
	
	public static void setDirection(int newD)
	{
			direction=newD;
	}
	
	
	public static int getScore()
	{
		return score;
	}
	
	public static int getDirection()
	{
		return direction;
	}
	
	public static int getBlockSize()
	{
		return blockSize;
	}
	/*
	public static void resetScore()
	{
		score=0;
	}
	
	public static int getblockCount()
	{
		return blockCount;
	}
	
	public static void resetblockCount()
	{
		blockCount=0;
	}
	
	public static int getBlockSize()
	{
		return blockSize;
	}


	
	public static int getDirection()
	{
		return direction;
	}

	public static void resetHeadLocation()
	{
		headlocation.setLocation(100,100);
	}
	
	public static void resetAllPoints()
	{
		allPoints.clear();
	}
	*/
}
