package bin;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.Table;
import processing.data.TableRow;

public class Pixel extends PApplet
{
	//This should be inside a function that is only called if the user wants to load, not the constructor
	//PImage img = loadImage("art.jpeg");
	
	boolean load = false;
	int userC; //had to be changed because the type color does not exist outside the processing ide. more info at https://forum.processing.org/two/discussion/2753/color-data-type-is-not-recognized-in-eclipse

	float x, y;

	int stages;
	int pixNum;
	
	static int r,g,b;
	static int mode;
	static float boxSize;
	
	PVector mouse;

	static boolean showLines;

	ArrayList<Square> squares = new ArrayList<Square>();
	ArrayList<Sliders> slide = new ArrayList<Sliders>();
	
	Scanner scanIn = new Scanner(System.in);

	public void setup() 
	{
		size(1000,1000);
		stages = 0;
		boxSize = 0;
		
		//Start color off as black
		r=g=b=0;

		userC = color(r,g,b);
		mode = 1;

		showLines = true;

		/*
		//Create 3 sliders
		Sliders red = new Sliders(150, color(255,0,0),this);
		slide.add(red);

		Sliders green = new Sliders(250, color(0,255,0),this);
		slide.add(green);

		Sliders blue = new Sliders(350, color(0,0,255),this);
		slide.add(blue);
		*/

	}

	public void draw() 
	{
		background(200);
		rectMode(CORNER);
		fill(255);

		switch (stages)
		{
		case 0:

			textAlign(CENTER);
			fill(0);
			stroke(0);
			text("How many pixels would you like to work with?",width/2, 100);
			text("16 x 16",width/2, 200);//256
			text("32 x 32",width/2, 300);//1024
			break;

		case 1:

			boxSize = (float)(width) / (float)(pixNum); //Makes the boxes fit the size of the screen

			for(int i = 0; i < squares.size(); i++)
			{
				Square squ = squares.get(i);
				squ.drawShape();
			}

			if(showLines == true)
			{
				drawGrid();
			}

			//println(pixNum);
			break;
		}
		/*
		if(load==true)
		{
			image(img,width/2,height/2);
		}
		*/
		
		println(squares);
	}

	public void drawGrid()
	{
		for(int i = 1; i < pixNum+1; i++)
		{
			//Vertical lines
			stroke(0);
			line(boxSize*i, 0, boxSize*i, height);

			//Horizontal lines
			line(0, boxSize*i, width, boxSize*i);
		}
	}

	public void setColour()
	{
		userC = color(r,g,b);
	}

	public void mousePressed()
	{ 
		switch (mode)
		{
		case 1:
			if(stages == 1)
			{
				x = (int)((mouseX/boxSize));
				y = (int)((mouseY/boxSize));

				Square a = new Square(x*boxSize, y*boxSize, userC,this);
				squares.add(a);
				//square16.add(x,y);
			}
			break;

		case 2:

			mouse = new PVector(mouseX,mouseY);

			if(stages == 1)
			{
				for(int i = 0; i < squares.size(); i++)
				{
					Square temp = squares.get(i);

					if(temp.pos.dist(mouse) < boxSize/2)
					{
						squares.remove(i);
					}
				}
			}
			break;
		}

		if(stages == 0)
		{
			if(mouseX > 100 && mouseX < width-100 && mouseY < 220 && mouseY > 180)
			{
				pixNum = 16;
				stages = 1;
			}

			if(mouseX > 100 && mouseX < width-100 && mouseY < 320 && mouseY > 280)
			{
				pixNum = 32;
				stages = 1;
			}
		}
	}

	/* I dont think I need this anymore but left it in in case I do 
	public void mouseDragged()
	{
		mouse = new PVector(mouseX,mouseY);

		//check if mouse is on slider
		if(colourMenu == true)
		{
			for(int i = 0; i < slide.size(); i++)
			{
				Sliders temp = slide.get(i);
				println(temp.pos + " " + mouse);

				if(temp.pos.dist(mouse) < 25)
				{
					if(mouseX < width-150 && mouseX > 150)
					{
						temp.scale = map(mouseX, 150, width - 150, 0 ,255);
						println(temp.scale);
					}
				}
			}
		}
	}
	*/

	
	 public void load(PImage img)
	 {
	     //Load functionality will be changed to be file explorer in future.

	     String name;

	     System.out.println("Enter a word or quit: ");
	     name = scanIn.nextLine();

	     img = loadImage(name + ".jpeg");
	     imageMode(CENTER);
	     //This was printing all the time so I stopped it
	     //System.out.println(name);
	 }
	 

	public void keyPressed()
	{
		//Processing window must be selected for this to work?
		if(stages == 1)
		{
			if(key == 't')
			{
				showLines = !showLines;
			}

			//Draw mode
			if(key == 'd')
			{
				mode = 1;
			}

			if(key == 'e')
			{
				mode = 2;
			}

			//Testing the save here, need to change and put into menu
			if(key == 's')
			{
				save("data/art.jpeg");
				
				//String [] g = squares.get(0);
				  
				/*createGraphics("art.jpeg");https://processing.org/reference/createGraphics_.html
				 * can be used to create transparent images but also is a drawable surface. Might be able
				 Might have to use savestrings as saving bytes is overcomplicating the code.
				*/
				System.out.println("image saved");
			}
			
	     if(key == 'l')
	     {
	       load = true;
	       load(img);
	       System.out.println("image loaded");

	     }
			 
		}
	}
}

