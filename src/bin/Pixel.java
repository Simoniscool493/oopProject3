package bin;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PVector;

public class Pixel extends PApplet
{

	int userC; //had to be changed because the type color does not exist outside the processing ide. more info at https://forum.processing.org/two/discussion/2753/color-data-type-is-not-recognized-in-eclipse

	float x, y;

	int stages;
	int mode;
	int pixNum;
	int r,g,b;

	public static float boxSize;
	PVector mouse;

	boolean colourMenu;
	boolean showLines;

	ArrayList<Square> squares = new ArrayList<Square>();
	ArrayList<Sliders> slide = new ArrayList<Sliders>();
	
	Scanner scanIn = new Scanner(System.in);

	public void setup() 
	{
		size(500, 500);
		stages = 0;
		boxSize = 0;
		r=g=b=255;

		userC = color(r,g,b);
		mode = 1;

		showLines = true;
		colourMenu = false;

		//Create 3 sliders
		Sliders red = new Sliders(150, color(255,0,0),this);
		slide.add(red);

		Sliders green = new Sliders(250, color(0,255,0),this);
		slide.add(green);

		Sliders blue = new Sliders(350, color(0,0,255),this);
		slide.add(blue);

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
			text("16 x 16",width/2, 200);
			text("32 x 32",width/2, 300);
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

			if(colourMenu == true)
			{
				ColourMenu();
			}

			//println(pixNum);
			break;
		} 
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
		Sliders Red = slide.get(0);
		r = (int)(Red.scale);

		Sliders Green = slide.get(1);
		g = (int)(Green.scale);

		Sliders Blue = slide.get(2);
		b = (int)(Blue.scale);

		userC = color(r,g,b);
	}

	public void ColourMenu()
	{
		//Colour menu
		stroke(0);
		fill(255);
		rect(50,50,400,400);

		for(int i = 0; i < slide.size(); i++)
		{
			stroke(0);
			line(150, 150+(i*100), width-150, 150+(i*100));

			Sliders entity = slide.get(i);
			entity.drawShape();
		}

		//Colour preview
		fill(userC);
		rect(70,70,50,50);

		setColour();
	}

	public void mousePressed()
	{ 
		switch (mode)
		{
		case 1:
			if(stages == 1 && colourMenu == false )
			{
				x = (int)((mouseX/boxSize));
				y = (int)((mouseY/boxSize));

				Square a = new Square(x*boxSize, y*boxSize, userC,this);
				squares.add(a);
			}
			break;

		case 2:

			mouse = new PVector(mouseX,mouseY);

			if(stages == 1 && colourMenu == false)
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

	/*
	 public void load(PImage img)
	 {
	     //Load functionality will be changed to be file explorer in future.

	     String name;

	     System.out.println("Enter a word or quit: ");
	     name = scanIn.nextLine();

	     img = loadImage(name + ".JPEG");
	     imageMode(CENTER);

	 }
	 */

	public void keyPressed()
	{
		if(stages == 1)
		{
			if(key == 't')
			{
				showLines = !showLines;
			}

			if(key == 'c')
			{
				colourMenu = !colourMenu;
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
			//Possibly change art.jpeg to other method. Make it so we use scanner to test then later use a file explorer to save the file like in paint. That way we can choose the file type as well :)
			if(key == 's')
			{
				save("art.jpeg");
			}
			/*
	     if(key == 'l')
	     {
	       PImage img = loadImage("art.jpeg");
	       load(img);
	       image(img,width/2,height/2);

	     }
			 */
		}
	}
}

