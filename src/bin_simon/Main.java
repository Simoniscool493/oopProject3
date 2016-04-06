package bin_simon;

public class Main
{
	static ControlWindow c;
	static DrawWindow d;
	
	public static int numSliders = 7;
	
	public static void main(String[] args)
	{		
		//PApplet.main(Main.class.getName());
		c = new ControlWindow();
		d = new DrawWindow();
		c.createWindow();
	}
} 