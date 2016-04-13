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
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

public class Pixel extends PApplet {

	PGraphics pArt;
	
	boolean load = false;
	static boolean flip;
	static boolean showLines;
	PImage pen,eraser;
	static PApplet parent;

	int backGround;
	int userC;
	int x, y;
	int bk = 0;
	int stages;
	int pixNum;

	static int r, g, b;
	static int backR, backG, backB;
	static int mode;
	static int screenSize;
	static float boxSize;

	PVector mouse;

	public static ArrayList<Square> squares = new ArrayList<Square>();

	Scanner scanIn = new Scanner(System.in);

	public void setup() {
		
		size(screenSize, screenSize);
		pArt = createGraphics(width,height, JAVA2D);
		stages = 0;
		boxSize = 0;
		cursor(CROSS);
		
		//pen = loadImage("pen.png");
		//eraser = loadImage("eraser.png");

		// Start color off as black
		r = g = b = 0;
		backR = backG = backB = 200;

		backGround = color(backR, backG, backB);
		userC = color(r, g, b);
		mode = 1;

		showLines = true;
		flip = false;

	}

	public void draw() {
		background(backR, backG, backB);
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
			
			boxSize = (float) (width) / (float) (pixNum);

			for (int i = 0; i < squares.size(); i++) {
				Square squ = squares.get(i);
				squ.drawShape();
			}

			if (showLines == true) {
				drawGrid();
			}
			break;
		}
		
		/*
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
		*/

		/*
		 * if(load==true) { image(img,width/2,height/2); }
		 */

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
			
			
			mode=1; //Changes back to draw mode for convenience 
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

			if (key == 's') {

				File file = new File("data/" + "art.ser");

				// save("data/art.jpeg");

				save(file, squares);

				System.out.println("image saved");

			}

			if (key == 'l') {

				File file = new File("data/" + "art.ser");

				squares = loaded(file);
				System.out.println("image loaded");

			}
			
			if(key == 'j')
			{
				pArt.beginDraw();
				
				for (int i = 0; i < squares.size(); i++) {
					Square squ = squares.get(i);
					squ.saveImageTrans();
				}
				pArt.endDraw();
				
				pArt.save("Drawing.png");
			}

			if (key == '\u001A' && !squares.isEmpty()) {
				bk--;
				if (bk > 0) {
					File file = new File("data/" + "art" + bk + ".ser");

					squares = loaded(file);
					// System.out.println("image loaded");
					println(bk + "loading");
				}
				if (bk < 0) {
					bk = 2;
				}

			}

		}
	}

	public void mouseReleased() {
		overWriteSquare();

		if (!squares.isEmpty()) {
			bk++;
			File file = new File("data/" + "art" + bk + ".ser");

			// save("data/art.jpeg");

			save(file, squares);

			System.out.println("image saved");

			println(bk);
		}

	}

	//Bug is fixed
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
							
							//Shift index back one after deletion
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

	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
		in.defaultReadObject();
		Square.parent = parent;
		Square.art = pArt;
	}
}
