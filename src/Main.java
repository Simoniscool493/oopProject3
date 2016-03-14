//main code 
//package bin;

import processing.core.PApplet;
import ddf.minim.AudioInput;
import ddf.minim.Minim;
import ddf.minim.analysis.FFT;
import ddf.minim.analysis.WindowFunction;


public class Main extends PApplet
{
	public static int num1;
	public static int num2;

	public void setup()
	{
		size(1000,1000);
		background(255);
		TopLevelWindow t = new TopLevelWindow();
		t.createWindow();
	}
	
	public void draw()
	{
		ellipse(width/2,height/2,(width/100)*num1,(height/100)*num1);
	}

	public static void main(String[] args)
	{		
		PApplet.main(Main.class.getName());
	}
} 