package bin_simon;

public class Main
{	
	static MainWindow c;
	static DrawWindow d;

	public static int numColorChoosers = 3;
	public static int numSliders = 7;
	
	public static String[] sliderNames = 
	{ 		"Framerate", 	"Dimension 1", 	"Dimension 2", 
			"Dimension 3", 	"Dimension 4", 	"Dimension 5",
			"Dimension 6",	"Dimension 7"};

	public static String[] colorNames = {"Red","Green","Blue"};
	
	public static void main(String[] args)
	{		
		//PApplet.main(Main.class.getName());
		c = new MainWindow();
		d = new DrawWindow();
		c.createWindow();
	}
} 