package bin;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PVector;

public class Square implements java.io.Serializable
{
	
   transient static PApplet parent;
	PVector pos;
	float x;
	float y; 
	int c; 
	static PGraphics art;

	Square(float x, float y, int c, PApplet p, PGraphics pArt)
	{
		parent = p;
		art = pArt;
		
		this.x = x;
		this.y = y;
		this.c = c;
		this.pos = new PVector(x+(Pixel.boxSize/2),y+(Pixel.boxSize/2));
	}

	public void drawShape()
	{
		parent.stroke(c);
		parent.fill(c);
		parent.rect(pos.x, pos.y, Pixel.boxSize-1, Pixel.boxSize-1);
	}

	//If user wants to save image with no background
	public void saveImageTrans()
	{
		art.stroke(c);
		art.fill(c);
	
		//I draw using X and Y because changing rectMode for PGraphics gives problems
		art.rect(x, y, Pixel.boxSize-1, Pixel.boxSize-1);
	}
	
	/*Lets you see what the contents of an arraylist are normally
	*/
    @Override
    public String toString() {
        return "pos:"+ pos +"Colour: "+(int)c;
    }

}
