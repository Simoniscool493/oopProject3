package bin_simon;

import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JCheckBox;
import processing.core.PApplet;

public class PSketch extends PApplet
{
	public static boolean showBackground = false;
	public static boolean saveImage = false;
	public static boolean enableDraw = true;
	public static boolean frameRateChanged = false;
	public static boolean randomStroke = false;
	public static boolean randomFill = false;
	public static boolean colorChanged = false;
	
	public static int indexColorChanged = 0;

	float range = 40.0f;
	float theta = 0;
	float thetaInc;
	float fps = 60.0f;
	float rad1 = 100;
	float rad2 = 200;
	float numPoints = 5;
	
	public static int[] num = new int[Main.numSliders+(Main.numColorChoosers*3)];
	public static boolean[] repeating = new boolean[Main.numSliders];
	public static boolean[] reversed = new boolean[Main.numSliders];
	
	public static ArrayList<Color> colors = new ArrayList<Color>();
	
	public static String currentAnimation = "Circle";

	public void setup()
	{
		size(1000,1000);
		background(255);
		
		for(int i=0;i<Main.numSliders;i++)
		{
			num[i] = MainWindow.sliderInit;
		}
		for(int i=0;i<Main.numColorChoosers;i++)
		{
			Color c = new Color(0,0,0);
			colors.add(c);
		}
	}
	
	public void draw()
	{
		if(randomStroke)
		{
			stroke(random(255),random(255),random(255));
		}
		
		if(randomFill)
		{
			fill(random(255),random(255),random(255));
		}
		if(colorChanged)
		{
			changeColor();
		}
		
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
			save("samples/art"+(int)random(1000)+".jpeg");
			saveImage = false;
		}
		
		if(frameRateChanged)
		{
			if(num[0]==0)
			{
				num[0] = 1;
			}

			frameRate(num[0]);
			frameRateChanged = false;
		}
	}
	
	public void drawAnimation()
	{
		beginShape();
		
		//1 is number of points on star
		//2 and 3 are h/w of inner radius
		//3 and 4 are h/w of outer radius
		
		if(currentAnimation == "Circle")
		{
			ellipse(width/2,height/2,(width/100)*num[1],(height/100)*num[2]);
		}
		if(currentAnimation == "Star")
		{
			float angle = TWO_PI / num[1]; 
			float halfAngle = angle/(float)2.0;
			
			for (float a = 0; a < TWO_PI; a += angle) 
			{
				float sx = width/2 + cos(a) * num[2]*5;
				float sy = height/2 + sin(a) * num[3]*5;
				vertex(sx, sy);
				sx = width/2 + cos(a+halfAngle) * num[4]*5;
				sy = height/2 + sin(a+halfAngle) * num[5]*5;
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
				if(!reversed[i])
				{
					num[i]++;

					if(num[i]==MainWindow.sliderMax)
					{
						num[i]=0;
					}
				}
				else
				{
					num[i]--;

					if(num[i]==MainWindow.sliderMin)
					{
						num[i]=MainWindow.sliderMax;
					}
				}
			}
		}
	}
	
	public void changeColor()
	{
		if(indexColorChanged<3)
		{
			Color c = getNewColor(0);
			background(c.getRed(),c.getGreen(),c.getBlue());
		}
		else if(indexColorChanged<6)
		{
			Color c = getNewColor(1);
			fill(c.getRed(),c.getGreen(),c.getBlue());
		}
		else if(indexColorChanged<9)
		{
			Color c = getNewColor(2);
			stroke(c.getRed(),c.getGreen(),c.getBlue());
		}
		colorChanged = false;
	}
	
	public Color getNewColor(int n)
	{
		Color c = colors.get(n);
		
		if(indexColorChanged%3 == 0)
		{
			c  = new Color(num[indexColorChanged+Main.numSliders],c.getGreen(),c.getBlue());
		}
		if(indexColorChanged%3 == 1)
		{
			c  = new Color(c.getRed(),num[indexColorChanged+Main.numSliders],c.getBlue());
		}
		if(indexColorChanged%3 == 2)
		{
			c  = new Color(c.getRed(),c.getGreen(),num[indexColorChanged+Main.numSliders]);
		}

		colors.set(n,c);
		
		return c;
	}
	
	

}
