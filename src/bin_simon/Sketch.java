package bin_simon;

import processing.core.PApplet;

public class Sketch extends PApplet
{
	public static boolean showBackground = true;
	public static boolean saveImage = false;
	public static boolean enableDraw = true;
	
	float range = 40.0f;
	float theta = 0;
	float thetaInc;
	float fps = 60.0f;
	float rad1 = 100;
	float rad2 = 200;
	float numPoints = 5;
	
	public static int[] num = new int[Main.numSliders];
	public static boolean[] repeating = new boolean[Main.numSliders];
	
	public static String currentAnimation = "Circle";

	public void setup()
	{
		size(1000,1000);
		background(255);
	}
	
	public void draw()
	{
		if(enableDraw)
		{
			if(showBackground)
			{
				background(255);
			}
			
			drawAnimation();
		}
		
		if(saveImage)
		{
			save("art"+(int)random(1000)+".jpeg");
			saveImage = false;
		}
	}
	
	public void drawAnimation()
	{
		beginShape();

		if(currentAnimation == "Circle")
		{
			ellipse(width/2,height/2,(width/100)*num[0],(height/100)*num[1]);
		}
		if(currentAnimation == "Star")
		{
			float angle = TWO_PI / num[2];
			float halfAngle = angle/(float)2.0;
			
			for (float a = 0; a < TWO_PI; a += angle) 
			{
				float sx = width/2 + cos(a) * num[0]*5;
				float sy = height/2 + sin(a) * num[0]*5;
				vertex(sx, sy);
				sx = width/2 + cos(a+halfAngle) * num[1]*5;
				sy = height/2 + sin(a+halfAngle) * num[1]*5;
				vertex(sx, sy);
			}

		}
		if(currentAnimation == "Square")
		{
	
		}
		if(currentAnimation == "Bouncing")
		{
			
		}
		if(currentAnimation == "Pendulum")
		{
		    translate(width / 2, height / 2);
		    rotate(sin(theta) * radians(range));
		    line(0, 0, 0, 200);
		    ellipse(0, 200, 20, 20);    
		    theta += (PI / fps);
		}
		if(currentAnimation == "Orbit")
		{
			
		}
		endShape(CLOSE);
		
		cycle();

	}
	
	public void cycle()
	{
		for(int i = 0;i<Main.numSliders;i++)
		{
			if(repeating[i])
			{
				if(num[i]>ControlWindow.sliderMax)
				{
					num[i]=0;
				}
				
				num[i]++;
			}
		}
	}
	
	

}
