package bin_main;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

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
	//public static int whichColorChanged = 0;
	//public static int whichChooser = 0;
	public static Color newColor; 


	float speed = 0;
	float range = 40.0f;
	float theta = 0;
	float thetaInc;
	float fps = 60.0f;
	float rad1 = 100;
	float rad2 = 200;
	float numPoints = 5;
	float angle1;
	float angle2;

	
	public static int[] num = new int[AnimationInit.numSliders];
	public static boolean[] repeating = new boolean[AnimationInit.numSliders];
	public static boolean[] reversed = new boolean[AnimationInit.numSliders];
	
	public static ArrayList<Color> colors = new ArrayList<Color>();
	
	public static String currentAnimation = "Circle";

	public void setup()
	{
		colors.add(new Color(255,255,255));
		colors.add(new Color(255,255,255));
		colors.add(new Color(0,0,0));
		
		fill(colors.get(1).getRed(),colors.get(1).getGreen(),colors.get(1).getBlue());
		stroke(colors.get(2).getRed(),colors.get(2).getGreen(),colors.get(2).getBlue());

		int windowSize = (int)(AnimationInit.screenSize.getWidth())/3;
		size(windowSize,windowSize);
		
		background(colors.get(0).getRed(),colors.get(0).getGreen(),colors.get(0).getBlue());
		
		for(int i=0;i<AnimationInit.numSliders;i++)
		{
			num[i] = MainWindow.sliderInit;
		}
		for(int i=0;i<3;i++)
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
		
		if(enableDraw)
		{
			if(showBackground)
			{
				background(colors.get(0).getRed(),colors.get(0).getGreen(),colors.get(0).getBlue());
				System.out.println(colors.get(0).getRed());
			}
			
			drawAnimation();
			
			if(colorChanged)
			{
				changeColor();
			}
		}
		
		if(saveImage)
		{
			saveDialog();
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
		if(currentAnimation == "Polygon")
		{
			 pushMatrix();
			  
			 float angle = TWO_PI /((num[1])+1);
			 beginShape();
			 
			 for(float a = 0; a < TWO_PI; a += angle) 
			 {
				    float sx = width/2 + cos(a) * num[2]*4;
				    float sy = height/2 + sin(a) * num[3]*4 ;
				    vertex(sx, sy);
			 }
			 
			  popMatrix();
		}
		if(currentAnimation == "Face")
		{
			strokeWeight((num[1]/5)+1);
			
			  for (int y = 0 ; y < height ; y += num[2]+20)
			  {
			    for (int x = 0 ; x < width ; x += num[3]+20)
			    {
			      //stroke(red, green, blue);
				  //THe face
			      ellipse(x + 25, y + 25, 50, 50);
			      // The  eyes and nose
			      line(x + 15, y + 10, x + 16, y + 10);
			      line(x + 35, y + 10, x + 36, y + 10);
			      ellipse(x + 25, y + 25, 4, 4);

			      // The straight mouth
			      line(x + 20, y + 38, x + 30, y + 38);
				}
			 }
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
			if(num[1]<1)
			{
				num[1] = 1;
			}
		    strokeWeight((num[1]/5));

			float posx,posy;
			posx = posy = width/2;
			
		    double ball1X = (posx + sin(angle1) * num[4]*5);
		    double ball1Y = (posy + cos(angle1) * num[5]*5);
		    double ball2X = (posx + sin(-angle2) * num[6]*5);
		    double ball2Y = (posy + cos(-angle2) * num[7]*5);

		    //strokeWeight(1);
		    
		    ellipse((float)ball1X,(float)ball1Y,num[2]*5,num[2]*5);
		    ellipse((float)ball2X,(float)ball2Y,num[2]*5,num[2]*5);

		    angle1+=(float)(num[3]/600.0f)+0.003;
		    angle2+=(float)(num[3]/600.0f)+0.003;
		}
		if(currentAnimation == "Spiral")
		{
			smooth();
			//pushMatrix();
				translate(width/2, height/2);
			//popMatrix();
			
			strokeWeight(2);
			beginShape();
			for(int i=0;i<350;i++)
			{
				curveVertex((i*2)*sin((float)(i/(num[1]/10.0))),(i*2)*cos((float)(i/(num[1]/10.0))));
			}
			endShape(); 
		}
		if(currentAnimation == "Square")
		{
			translate(random(1000),random(1000));

		    strokeWeight((num[1]/5)+1);
			rect(0,0,num[3]*5,num[4]*5);
		}
		endShape(CLOSE);
		
		System.out.println(num[5]);
		cycle();
	}
	
	public void cycle()
	{
		for(int i = 0;i<AnimationInit.numSliders;i++)
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
		int red = newColor.getRed();
		int green = newColor.getGreen();
		int blue = newColor.getBlue();

		
		if(indexColorChanged == 0)
		{
			background(red,green,blue);
		}
		if(indexColorChanged == 1)
		{
			fill(red,green,blue);
		}
		else if(indexColorChanged == 2)
		{
			stroke(red,green,blue);
		}
		
		colors.set(indexColorChanged,newColor);
		colorChanged = false;
	}
	
	public void saveDialog()
	{
		File f = new File("./data/Animations");
		JFileChooser ch = new JFileChooser();
		ch.setCurrentDirectory(f);
		
		int sv = ch.showSaveDialog(new JFrame());
		
		if(sv == JFileChooser.APPROVE_OPTION)
		{
			String s = ch.getSelectedFile().getAbsolutePath();
			save(s + ".png");
		}	
	}

}
