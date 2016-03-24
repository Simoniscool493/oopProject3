package bin_simon;

import processing.core.PApplet;

public class Main extends PApplet
{
	public static boolean showBackground = true;
	
	public static int[] num = new int[5];

	public void setup()
	{
		size(1000,1000);
		background(255);
		TopLevelWindow t = new TopLevelWindow();
		t.createWindow();
	}
	
	public void draw()
	{
		if(showBackground)
		{
			background(255);
		}
		ellipse(width/2,height/2,(width/100)*num[0],(height/100)*num[1]);
	}
	
	public void keyPressed()
	{
		if(key == 's')
		{
			save("art.jpeg");
		}
	}

	public static void main(String[] args)
	{		
		PApplet.main(Main.class.getName());
	}
} 