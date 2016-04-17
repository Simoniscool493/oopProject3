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

import javax.swing.JButton;
import javax.swing.JTextField;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Pixel extends PApplet {

	PGraphics pArt;
	PImage lastFrame;
	static String png = "";

	static int maxFrameNum;
	// The number of frame i am on
	static int frameNum;
	// The frame count for animation
	static int frameCount;

	boolean animationSet = false;
	boolean loop = false;

	boolean load = false;
	static boolean flip;
	static boolean showLines;
	PImage pen, eraser;
	static PApplet parent;
	static boolean loads = false;
	static boolean save = false;
	static boolean svimg = false;

	static File file = new File("");
	static File filen = new File("");

	int backGround;
	int userC;
	int x, y;
	int bk = 0;
	int pixNum;

	static int stages;
	static int r, g, b;
	static int backR, backG, backB;
	static int mode;
	static int screenSize;
	static float boxSize;
	static int frameRate;

	PVector mouse;

	public static ArrayList<Square> squares = new ArrayList<Square>();
	public static ArrayList<Framedata> data = new ArrayList<Framedata>();

	Scanner scanIn = new Scanner(System.in);

	public void setup() {

		size(screenSize, screenSize);
		pArt = createGraphics(width, height, JAVA2D);
		stages = 0;
		boxSize = 0;
		cursor(CROSS);

		// pen = loadImage("pen.png");
		// eraser = loadImage("eraser.png");

		// Start color off as black
		r = g = b = 0;
		backR = backG = backB = 200;
		frameNum = 0;
		frameRate = 59;

		backGround = color(backR, backG, backB);
		userC = color(r, g, b);
		mode = 1;

		showLines = true;
		flip = false;
		System.out.println(file.getParent());
		System.out.println(file.getName());
	}

	public void draw() {

		background(backR, backG, backB);
		rectMode(CORNER);

		fill(255);
		maxFrameNum = data.size() - 1;

		switch (stages) {
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
			// I realised the program is slower to respond if the frameRate is
			// slower
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

			if (showLines == true) {
				drawGrid();
			}
			break;

		// For animation
		case 2:
			runAnimation();
		}

		/*
		 * if(mode==3) { cursor(pen); }
		 * 
		 * else if(mode==2) { cursor(eraser); }
		 * 
		 * else { cursor(CROSS); }
		 */

		if (loads) 
		{
			squares = loaded(file);
			loads = false;
		}
		if (save) 
		{
			save(filen, squares);
			save = false;
		}
		if(svimg)
		{
			saveTrans();
			svimg=false;
		}

	}

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

	public void setColour() {
		userC = color(r, g, b);
	}

	public void mousePressed() {

		switch (mode) {

		case 1:

			if (stages == 1) {

				x = (int) ((mouseX / boxSize));
				y = (int) ((mouseY / boxSize));

				Square a = new Square(x * boxSize, y * boxSize, userC, this, pArt);
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

		case 3:

			mouse = new PVector(mouseX, mouseY);
			if (stages == 1) {
				for (int i = 0; i < squares.size(); i++) {
					Square temp = squares.get(i);

					if (temp.pos.dist(mouse) < boxSize / 2) {
						userC = temp.c;
					}
				}
			}

			mode = 1; // Changes back to draw mode for convenience
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

			if (mouseX > 100 && mouseX < width - 100 && mouseY < 420 && mouseY > 380) {
				pixNum = 64;
				stages = 1;
			}
		}
	}

	public void mouseDragged() {
		switch (mode) {
		case 1:

			if (stages == 1) {
				x = (int) ((mouseX / boxSize));
				y = (int) ((mouseY / boxSize));

				Square a = new Square(x * boxSize, y * boxSize, userC, this, pArt);
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

			// This is also a test
			if (key == 's') {
				addFrame();
			}

			if (key == 'h') {
				stages = 2;
			}

			//Undo 
			if (key == '\u001A' && !squares.isEmpty()) {
				bk--;
				if (bk > 0) {
					File file = new File(bk + ".bak");

					squares = loaded(file);
					// System.out.println("image loaded");
					// println(bk + "loading");
				}
				if (bk < 0) {
					bk = 2;
				}

			}

		}
	}

	public void flip() {
		for (int i = 0; i < squares.size(); i++) {
			Square squ = squares.get(i);

			squ.pos.x = width - squ.pos.x;
			squ.x = (int) squ.pos.x - (boxSize / 2 - 1);
			squ.y = (int) squ.pos.y - (boxSize / 2 - 1);
		}

	}

	public void saveTrans()
	{
			
		pArt.beginDraw();

		for (int i = 0; i < squares.size(); i++) {
			Square squ = squares.get(i);
			squ.saveImageTrans();
		}
		pArt.endDraw();

		pArt.save(png);
	}

	public void mouseReleased() {
		overWriteSquare();

		if (!squares.isEmpty()) 
		{
			bk++;
			
			File file = new File(bk+".bak");

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

	public void addFrame() {

		pArt.beginDraw();

		for (int i = 0; i < squares.size(); i++) {
			Square squ = squares.get(i);
			squ.saveImageTrans();
		}
		pArt.endDraw();

		pArt.save("Frame/frame" + frameNum + ".png");

		pArt.clear();
		lastFrame = loadImage("Frame/frame" + (frameNum) + ".png");

		Framedata newFrame = new Framedata(squares, lastFrame);
		data.add(newFrame);

		squares.clear();

		// test
		Framedata test = data.get(0);
		test.showArray();

		frameNum++;
	}

	public void lastFrame() {
		tint(255, 50);
		// Load up last frame
		Framedata tempImage = data.get(frameNum - 1);
		image(tempImage.lastFrame, 0, 0);
	}

	public void removeLastFrame() {
		data.remove(frameNum - 1);
		if (frameNum >= data.size() - 1) {
			frameNum--;
		}
	}

	public void runAnimation() {
		frameRate(frameRate);
		tint(255, 255);
		frameCount = (frameCount + 1) % data.size();

		// Load up last frame
		Framedata tempImage = data.get(frameCount);
		image(tempImage.lastFrame, 0, 0);
	}

	public void prevFrame() {
		if (frameNum > 0) {
			frameNum--;
			loadFramePixels();
		}
	}

	public void nextFrame() {
		if (frameNum <= maxFrameNum) {
			frameNum++;
			loadFramePixels();
		}
	}

	public void loadFramePixels() {
		//since it can't load what isn't made yet
		if (frameNum != data.size()) {
			println(frameNum);
			Framedata tempPixel = data.get(frameNum);
			tempPixel.showArray();
			squares = new ArrayList<Square>(tempPixel.pixels);
			
			//System.out.println(squares);
		}
	}

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		Square.parent = parent;
		Square.art = pArt;
	}
}
