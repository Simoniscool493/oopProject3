//main code
//package bin

import processing.core.PApplet;

public class Main extends PApplet
{
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
		ellipse(width/2,height/2,(width/100)*num[0],(height/100)*num[1]);
	}

	public static void main(String[] args)
	{		
		//PApplet.main(Main.class.getName());
	}
} 