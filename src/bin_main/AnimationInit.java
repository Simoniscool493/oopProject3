package bin_main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import javax.swing.*;

public class AnimationInit
{	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	static MainWindow c;
	static DrawWindow d;
	
	public static int numSliders = 7;
	
	public static String[] sliderNames = 
	{ 		"Framerate", 	"Stroke Weight", 	"Dimension 1", 
			"Dimension 2", 	"Dimension 3", 	"Dimension 4",
			"Dimension 5",	"Dimension 6"};

	public static String[] colorNames = {"Red","Green","Blue"};
	
	public static void begin()
	{		
		
		c = new MainWindow();
		d = new DrawWindow();
		c.createWindow();
		d.setSize(new Dimension(d.getWidth(),c.getHeight()));
		
		stick(c,d);
	}
	
	public static void stick(JFrame c,JFrame d)
	{
		c.addComponentListener(new ComponentAdapter() 
        {
            public void componentMoved(ComponentEvent e) 
            {
            	d.setLocation(c.getLocation().x-(int)(screenSize.getWidth()/2.83),c.getLocation().y);
            	d.setExtendedState(JFrame.NORMAL);
            }
        }
        );
		d.setExtendedState(JFrame.ICONIFIED);
		d.setExtendedState(JFrame.NORMAL);
	}
	
} 