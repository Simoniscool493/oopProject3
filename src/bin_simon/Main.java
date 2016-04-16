package bin_simon;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.*;

public class Main
{	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static MainWindow c;
	static DrawWindow d;

	//public static int numColorChoosers = 3;
	public static int numSliders = 8;
	
	public static String[] sliderNames = 
	{ 		"Framerate", 	"Dimension 1", 	"Dimension 2", 
			"Dimension 3", 	"Dimension 4", 	"Dimension 5",
			"Dimension 6",	"Dimension 7"};

	public static String[] colorNames = {"Red","Green","Blue"};
	
	public static void main(String[] args)
	{		
		
		c = new MainWindow();
		d = new DrawWindow();
		c.createWindow();
		
		stick(c,d);
	}
	
	public static void stick(JFrame c,JFrame d)
	{
		c.addComponentListener(new ComponentAdapter() 
        {
            public void componentMoved(ComponentEvent e) 
            {
            	d.setLocation(c.getLocation().x-(int)(screenSize.getWidth()/2.9),c.getLocation().y);
            	d.setExtendedState(JFrame.NORMAL);
            }
        }
        );
	}
	
} 