package bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


public class Pixel extends PApplet {
	// This should be inside a function that is only called if the user wants to
	// load, not the constructor
	// PImage img = loadImage("art.jpeg");

	boolean load = false;
	int userC; // had to be changed because the type color does not exist
				// outside the processing ide. more info at
				// https://forum.processing.org/two/discussion/2753/color-data-type-is-not-recognized-in-eclipse

	float x, y;

	int stages;
	int pixNum;

	static int r, g, b;
	static int mode;
	static int screenSize;
	static float boxSize;
	
	static boolean flip;
	
	PVector mouse;

	static boolean showLines;

	public static ArrayList<Square> squares = new ArrayList<Square>();

	Scanner scanIn = new Scanner(System.in);

	public void setup() {
		size(screenSize, screenSize);
		stages = 0;
		boxSize = 0;

		// Start color off as black
		r = g = b = 0;

		userC = color(r, g, b);
		mode = 1;

		showLines = true;
		flip=false;

		/*
		 * //Create 3 sliders Sliders red = new Sliders(150,
		 * color(255,0,0),this); slide.add(red);
		 * 
		 * Sliders green = new Sliders(250, color(0,255,0),this);
		 * slide.add(green);
		 * 
		 * Sliders blue = new Sliders(350, color(0,0,255),this);
		 * slide.add(blue);
		 */

	}

	public void draw() {
		background(200);
		rectMode(CORNER);
		fill(255);

		switch (stages) {
		case 0:

			textAlign(CENTER);
			fill(0);
			stroke(0);
			text("How many pixels would you like to work with?", width / 2, 100);
			text("16 x 16", width / 2, 200);// 256
			text("32 x 32", width / 2, 300);// 1024
			break;

		case 1:

			boxSize = (float) (width) / (float) (pixNum); // Makes the boxes fit
															// the size of the
															// screen

			for (int i = 0; i < squares.size(); i++) {
				Square squ = squares.get(i);
				squ.drawShape();
			}

			if (showLines == true) {
				drawGrid();
			}
			


			// println(pixNum);
			break;
		}
		/*
		 * if(load==true) { image(img,width/2,height/2); }
		 */

		// println(squares);
	}

	public void drawGrid() {
		for (int i = 1; i < pixNum + 1; i++) {
			// Vertical lines
			stroke(0);
			line(boxSize * i, 0, boxSize * i, height);

			// Horizontal lines
			line(0, boxSize * i, width, boxSize * i);
		}
	}

	public void setColour() {
		userC = color(r, g, b);
	}

	public void mousePressed() {
		switch (mode) {
		case 1:
			if (stages == 1) {
				x = (int) ((mouseX / boxSize));
				y = (int) ((mouseY / boxSize));

				Square a = new Square(x * boxSize, y * boxSize, userC, this);
				squares.add(a);

			}
			break;

		case 2:

			mouse = new PVector(mouseX, mouseY);

			if (stages == 1) {
				for (int i = 0; i < squares.size(); i++) {
					Square temp = squares.get(i);

					if (temp.pos.dist(mouse) < boxSize / 2) {
						squares.remove(i);
					}
				}
			}
			break;
		}

		if (stages == 0) {
			if (mouseX > 100 && mouseX < width - 100 && mouseY < 220 && mouseY > 180) {
				pixNum = 16;
				stages = 1;
			}

			if (mouseX > 100 && mouseX < width - 100 && mouseY < 320 && mouseY > 280) {
				pixNum = 32;
				stages = 1;
			}
		}
	}

	/*
	 * I dont think I need this anymore but left it in in case I do public void
	 * mouseDragged() { mouse = new PVector(mouseX,mouseY);
	 * 
	 * //check if mouse is on slider if(colourMenu == true) { for(int i = 0; i <
	 * slide.size(); i++) { Sliders temp = slide.get(i); println(temp.pos + " "
	 * + mouse);
	 * 
	 * if(temp.pos.dist(mouse) < 25) { if(mouseX < width-150 && mouseX > 150) {
	 * temp.scale = map(mouseX, 150, width - 150, 0 ,255); println(temp.scale);
	 * } } } } }
	 */

	public void load(PImage img) {
		// Load functionality will be changed to be file explorer in future.

		/*
		 * String name;
		 * 
		 * System.out.println("Enter a word or quit: "); name =
		 * scanIn.nextLine();
		 * 
		 * img = loadImage(name + ".jpeg"); imageMode(CENTER);
		 * 
		 */
	}

	public void keyPressed() {
		// Processing window must be selected for this to work?
		if (stages == 1) {
			if (key == 't') {
				showLines = !showLines;
			}

			// Draw mode
			if (key == 'd') {
				mode = 1;
			}

			if (key == 'e') {
				mode = 2;
			}
			
			if(key == 'f')
			{
				
				
				 for(int i =0; i < squares.size(); i++)
				 {
					 
					 squares.get(i).y=-y;
					 
				 }
				 
				 println("flipped");
				 
			}

			// Testing the save here, need to change and put into menu
			if (key == 's') {

				/*
				 * File file = new File("C:/Data/" + "art.ser");
				 * 
				 * //save("data/art.jpeg");
				 * 
				 * save(file, squares);
				 * 
				 * System.out.println("image saved");
				 */
			}

			if (key == 'l') {

				/*
				File file = new File("C:/Data/" + "art.ser");
				load = true;
				System.out.println("image loaded");

				squares = loaded(file);
				*/
			}

		}

	}

	
	public static void save(File path, ArrayList<Square> squares2) 
	{
	
		try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(path))) {
			write.writeObject(squares2);
			write.close();
		}
		
		catch (NotSerializableException nse) 
		{
			println("Exception not seriliazble");
		} 
		catch (IOException eio) 
		{
			println("IO Exception");
		}
	}

	public static ArrayList<Square> loaded(File path) 
	{
		Object square2 = null;
		ArrayList<Square> sq = new ArrayList<Square>();

		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path))) 
		{
			square2 = inFile.readObject();

			sq = (ArrayList<Square>) square2;

			return sq;
		} 
		
		catch (ClassNotFoundException cnfe) 
		{
			println("Class not found");
		} 
		catch (FileNotFoundException fnfe) 
		{
			println("File not found");
		} 
		catch (IOException e) 
		{
			println("IO Exception ");
		}
		return null;
	}
}
