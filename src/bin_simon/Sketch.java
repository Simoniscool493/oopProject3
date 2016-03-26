package bin_simon;

import processing.core.PApplet;

public class Sketch extends PApplet
{
	public static boolean showBackground = true;
	public static boolean saveImage = false;
	
	public static int[] num = new int[5];

	public void setup()
	{
		size(1000,1000);
		background(255);
	}
	
	public void draw()
	{
		if(showBackground)
		{
			background(255);
		}
		
		ellipse(width/2,height/2,(width/100)*num[0],(height/100)*num[1]);
		
		if(saveImage)
		{
			save("art"+(int)random(1000)+".jpeg");
			saveImage = false;
		}
	}
}
