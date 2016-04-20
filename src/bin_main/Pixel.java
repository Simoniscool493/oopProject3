package bin_main;

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
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Pixel extends PApplet {

	PGraphics pArt;
	PImage lastFrame;
	static String png = "";
	static File Dir = new File("");

	static int maxFrameNum;
	// The number of frame i am on
	static int frameNum;
	// The frame count for animation
	static int frameCount;
	//rows and cols of squares
	static int pixNum;

	boolean load = false;
	static boolean flip;
	static boolean showLines;
	PImage pen, eraser;
	static PApplet parent;
	static boolean loads = false;
	static boolean save = false;
	static boolean svimg = false;
	static boolean fram = false;
	static boolean bg = false;
	boolean five = false;
	static boolean savebg=false;

	static File file = new File("");
	static File filen = new File("");

	int userC;
	int x, y;
	int bk = 0;

	static int stages;
	static int r, g, b;
	static int backR, backG, backB;
	static int mode;
	static int screenSize;
	static float boxSize;
	static int frameRate;

	PVector mouse;

	public static ArrayList<Square> squares = new ArrayList<Square>();

	// Temp array for new pixels
	public static ArrayList<Square> tempNew = new ArrayList<Square>();

	public static ArrayList<Framedata> data = new ArrayList<Framedata>();

	Scanner scanIn = new Scanner(System.in);

	public void setup() {

		size(screenSize, screenSize);
		pArt = createGraphics(width, height);
		stages = 0;
		boxSize = 0;
		cursor(CROSS);

		 pen = loadImage("./data/Icons/pen.png");
		 eraser = loadImage("./data/Icons/eraser.png");

		// Start color off as black
		r = g = b = 0;
		backR = backG = backB = 200;
		frameNum = 0;
		frameRate = 59;
		
		userC = color(r, g, b);
		mode = 1;

		showLines = true;
		flip = false;
		
		//Need this to initialise the arraylist for loading pixels on start up
		Square init = new Square(0,0,255,pixNum,this,pArt);
		squares.add(init);
		squares.clear();
	}

	public void draw() {

		background(backR, backG, backB);
		rectMode(CORNER);

		fill(255);
		maxFrameNum = data.size() - 1;

		switch (stages) {
		
		//Menu
		case 0:

			textAlign(CENTER);
			fill(0);
			stroke(0);
			text("How many pixels would you like to work with?", width / 2, 100);
			text("16 x 16", width / 2, 200);// 256
			text("32 x 32", width / 2, 300);// 1024
			text("64 x 64", width / 2, 400);// 4096
			break;

		// The actual drawing
		case 1:

			frameRate(60);

			if (data.size() > 0 && frameNum != 0) {
				lastFrame();
			}
			
			boxSize = (float) (width) / (float) (pixNum);

			rectMode(CENTER);
			
			for (int i = 0; i < squares.size(); i++) {
				Square squ = squares.get(i);
				squ.drawShape();
			}

			//To draw grid
			if (showLines == true) {
				drawGrid();
			}
			break;

		// For animation
		case 2:
			//Only run if there is a frame
			if (data.size() > 0) {
				runAnimation();
			}
		}

		
		  if(mode==3) 
		  { 
			  cursor(pen); 
		  }
		  
		  else if(mode==2) 
		  { 
			  cursor(eraser);
		  }
		  
		 else 
		 { 
			 cursor(CROSS); 
		 }
		 

		//For loading file
		if (loads) {
			squares = loaded(file);
			loads = false;
			
			if(squares.size() > 0){
				//Just pick a square for its value
				Square temp = squares.get(0);
				pixNum = temp.pixNum;
			}
		}
		
		//Saving file
		if (save) {
			save(filen, squares);
			save = false;
		}
		
		//Save as img
		if (svimg) {
			saveTrans();
			svimg = false;
		}
		
		//Saving all frames
		if(fram)
		{
			saveFrame();
			fram = false;
		}
		
		//Save with background
		if(bg)
		{
			saveFrame();
			fram = false;
		}
	}

	//Code for drawing grid
	public void drawGrid() {
		for (int i = 1; i < pixNum + 1; i++) {
			if (i == pixNum / 2) {
				stroke(255, 0, 0);
			} else {
				stroke(0);
			}

			// Vertical
			line(boxSize * i, 0, boxSize * i, height);

			// Horizontal
			line(0, boxSize * i, width, boxSize * i);
		}
	}

	//Function that is used with slider actioners to update colour Preview
	public void setColour() {
		userC = color(r, g, b);
	}

	public void mousePressed() {

		switch (mode) {

		//if in draw mode
		case 1:
			if (stages == 1) {

				x = (int) ((mouseX / boxSize));
				y = (int) ((mouseY / boxSize));

				Square a = new Square(x * boxSize, y * boxSize, userC, pixNum, this, pArt);
				squares.add(a);
			}
			break;

		//If erase mode
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

			//Magic pen mode
		case 3:

			mouse = new PVector(mouseX, mouseY);
			if (stages == 1) {
				for (int i = 0; i < squares.size(); i++) {
					Square temp = squares.get(i);

					if (temp.pos.dist(mouse) < boxSize / 2) {
						//Get the color of square
						userC = temp.c;
					}
				}
			}

			mode = 1; // Changes back to draw mode for convenience
			break;

		}

		//selecting the pixNum at menu
		if (stages == 0) {
			if (mouseX > 100 && mouseX < width - 100 && mouseY < 220 && mouseY > 180) {
				pixNum = 16;
				stages = 1;
			}

			if (mouseX > 100 && mouseX < width - 100 && mouseY < 320 && mouseY > 280) {
				pixNum = 32;
				stages = 1;
			}

			if (mouseX > 100 && mouseX < width - 100 && mouseY < 420 && mouseY > 380) {
				pixNum = 64;
				stages = 1;
			}
		}
	}

	//Drawing and erasing if mouse is dragged
	public void mouseDragged() {
		switch (mode) {
		case 1:

			if (stages == 1) {
				x = (int) ((mouseX / boxSize));
				y = (int) ((mouseY / boxSize));

				Square a = new Square(x * boxSize, y * boxSize, userC, pixNum, this, pArt);
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
	}

	//Hotkeys
	public void keyPressed() {
		if (stages == 1) {
			
			if (key == 't') {
				showLines = !showLines;
			}

			if (key == 'd') {
				mode = 1;
			}

			if (key == 'e') {
				mode = 2;
			}

			if (key == 'm') {
				mode = 3;
			}

			if (key == 'f') {
				flip();
			}

			// Used to move the drawing area
			if (key == CODED) {
				if (keyCode == UP) {
					for (int i = 0; i < squares.size(); i++) {
						Square squ = squares.get(i);

						squ.pos.y = squ.pos.y - boxSize;

						// I change X and Y for PGraphics
						squ.x = (int) squ.pos.x - (boxSize / 2 - 1);
						squ.y = (int) squ.pos.y - (boxSize / 2 - 1);
					}
				}

				if (keyCode == DOWN) {
					for (int i = 0; i < squares.size(); i++) {
						Square squ = squares.get(i);

						squ.pos.y = squ.pos.y + boxSize;
						squ.x = (int) squ.pos.x - (boxSize / 2 - 1);
						squ.y = (int) squ.pos.y - (boxSize / 2 - 1);
					}
				}

				if (keyCode == LEFT) {
					for (int i = 0; i < squares.size(); i++) {
						Square squ = squares.get(i);

						squ.pos.x = squ.pos.x - boxSize;
						squ.x = (int) squ.pos.x - (boxSize / 2 - 1);
						squ.y = (int) squ.pos.y - (boxSize / 2 - 1);
					}
				}

				if (keyCode == RIGHT) {
					for (int i = 0; i < squares.size(); i++) {
						Square squ = squares.get(i);

						squ.pos.x = squ.pos.x + boxSize;
						squ.x = (int) squ.pos.x - (boxSize / 2 - 1);
						squ.y = (int) squ.pos.y - (boxSize / 2 - 1);
					}
				}
			}

			// Undo
			if (key == '\u001A' && !squares.isEmpty()) {
				bk--;
				
				if (bk < 1 && !five) {
					bk = 1;
				}
				if (bk < 1 && five) 
				{
					bk = 5;
				}
				
				if (bk > 0) 
				{
					File file = new File("./data/Bak/" + bk + ".bak");

					squares = loaded(file);
				}
			
			}

		}
	}

	//Function to flip squares
	public void flip() {
		for (int i = 0; i < squares.size(); i++) {
			Square squ = squares.get(i);

			squ.pos.x = width - squ.pos.x;
			
			//Changing the x and y value also
			squ.x = (int) squ.pos.x - (boxSize / 2 - 1);
			squ.y = (int) squ.pos.y - (boxSize / 2 - 1);
		}

	}

	//Saving the image with/without background
	public void saveTrans() 
	{
		pArt.beginDraw();
		if(savebg)
		{
			pArt.background(backR,backG,backB);
		}
		
		for (int i = 0; i < squares.size(); i++) {
			Square squ = squares.get(i);
			squ.saveImageTrans();
		}
		pArt.endDraw();

		pArt.save(png);
	}

	public void mouseReleased() {
		//Deletes unnessacry squares
		overWriteSquare();
		
		//remove squares outside of sketch
		removeSquares();
		
		File Dir = new File("./data/Bak");
		
		if (!Dir.exists()) 
		{
			Dir.mkdir();
		}
		
		if (!squares.isEmpty()) 
		{
			bk++;
			
			if(bk > 5)
			{
				bk=1;
			}
			
			if(bk ==5)
			{
				five=true;
			}
			
			File file = new File("./data/Bak/" + bk + ".bak");

			save(file, squares);
			
		}

	}

	// Bug is fixed
	public void overWriteSquare() {
		// Goes through the array of squares and compares the position every
		// square underneath it
		if (squares.size() > 0) {
			for (int i = squares.size() - 1; i > 1; i--) {
				Square squareOne = squares.get(i);
				if (squares.size() > 1) {
					// Makes sure we're not comparing the same square
					for (int j = i - 1; j > 1; j--) {
						Square squareTwo = squares.get(j);

						if ((squareOne.pos).dist(squareTwo.pos) == 0) {
							squares.remove(j);

							// Shift index back one after deletion
							i = i - 1;
						}
					}
				}
			}
		}
	}
	
	
	//Outside of processing sketch
	public void removeSquares()
	{
		for(int i = 0; i < squares.size(); i++)
		{
			Square squ = squares.get(i);
			if(squ.pos.x < 0 || squ.pos.x > width || squ.pos.y < 0 || squ.pos.y > height)
			{
				squares.remove(i);
			}
		}
	}

	public static void save(File path, ArrayList<Square> squares2) {

		try (ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(path))) {
			write.writeObject(squares2);
			write.close();
		}

		catch (NotSerializableException nse) {
			println("Exception not seriliazble");
		} catch (IOException eio) {
			println("IO Exception");
		}
	}

	public static ArrayList<Square> loaded(File path) {
		Object square2 = null;
		ArrayList<Square> sq = new ArrayList<Square>();

		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path))) {
			square2 = inFile.readObject();

			sq = (ArrayList<Square>) square2;
			inFile.close();

			return sq;
		}

		catch (ClassNotFoundException cnfe) {
			println("Class not found");
		}

		catch (FileNotFoundException fnfe) {
			println("File not found");
		}

		catch (IOException e) {
			println("IO Exception ");
		}

		return null;
	}

	
	//Adding saving frame as img and loading it back up as background
	public void addFrame() {
		
		File Dir = new File("./data/Last");
		
		if (!Dir.exists()) 
		{
			Dir.mkdir();
		}

		pArt.beginDraw();

		for (int i = 0; i < squares.size(); i++) {
			Square squ = squares.get(i);
			squ.saveImageTrans();
		}
		pArt.endDraw();

		pArt.save("./data/Last/" + frameNum + ".png");

		pArt.clear();
		lastFrame = loadImage("./data/Last/" + (frameNum) + ".png");

		Framedata newFrame = new Framedata(squares, lastFrame);
		data.add(frameNum, newFrame);

		// If working on a previous frame, then overwrite it
		if (data.size() > 0) {
			if (frameNum != data.size() - 1) {
				data.remove(frameNum+1);
			}
		}

		squares.clear();

		frameNum++;
	}

	public void lastFrame() {
		tint(255, 50);
		// Load up last frame
		Framedata tempImage = data.get(frameNum - 1);
		image(tempImage.lastFrame, 0, 0);
	}

	public void saveFrame() 
	{	
		//Save the current squares
		tempNew = new ArrayList<Square>(squares);
		int i=0;
		
		Dir.mkdir();
		String s = Dir.getAbsolutePath();
		
		
		
		for(i =0; i < data.size();i++)
		{	
			
			Framedata d = data.get(i);
			squares = new ArrayList<Square>(d.pixels);
			
			pArt.beginDraw();
			
			for (int j = 0; j < squares.size(); j++)
			{
				Square squ = squares.get(j);
				squ.saveImageTrans();
			}
			pArt.endDraw();
			pArt.save(s+ "/"+ i+ ".png");
			pArt.clear();
		}
		//Reset squares to the previous squares
		squares = new ArrayList<Square>(tempNew);
		
	}
	
	//Deletes last frame
	public void removeLastFrame() {
		data.remove(frameNum - 1);
		if (frameNum >= data.size() - 1) {
			frameNum--;
		}
	}
	
	//Runs the animation
	public void runAnimation() {
		frameRate(frameRate);
		tint(255, 255);
		frameCount = (frameCount + 1) % data.size();

		// Load up last frame
		Framedata tempImage = data.get(frameCount);
		image(tempImage.lastFrame, 0, 0);
	}

	//Copying last pixel array and loads it to current one
	public void copyLastFrame()
	{
		if(data.size() > 0)
		{
			Framedata temp = data.get(frameNum-1);
			squares = new ArrayList<Square>(temp.pixels);
		}
	}
	
	// view/can overwrite previous frame
	public void prevFrame() {

		if (frameNum == data.size()) {
			tempNew = new ArrayList<Square>(squares);
		}

		if (frameNum > 0) {
			frameNum--;
			loadFramePixels();
		}
	}

	//traverse with frames
	public void nextFrame() {
		if (frameNum <= maxFrameNum) {
			frameNum++;
			loadFramePixels();
		}

		if (frameNum == data.size()) {
			squares = new ArrayList<Square>(tempNew);
		}
	}

	//can load pixels from previous/next frames
	public void loadFramePixels() {
		// since it can't load what isn't made yet
		if (frameNum != data.size()) {
			Framedata tempPixel = data.get(frameNum);
			tempPixel.showArray();
			squares = new ArrayList<Square>(tempPixel.pixels);

		}
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		Square.parent = parent;
		Square.art = pArt;
	}
}
