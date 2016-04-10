package bin;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Square implements java.io.Serializable
{
	
   transient static PApplet parent;
	PVector pos;
	float x;
	float y; 
	int c; 

	Square(float x, float y, int c, PApplet p)
	{
		parent = p;
		this.x = x;
		this.y = y;
		this.c = c;
		this.pos = new PVector(x+(Pixel.boxSize/2),y+(Pixel.boxSize/2));
	}

	public void drawShape()
	{
		parent.stroke(c);
		parent.fill(c);
		parent.rect(x, y, Pixel.boxSize-1, Pixel.boxSize-1);
	}
	
	/*Lets you see what the contents of an arralist are normally
	*/
    @Override
    public String toString() {
        return "pos:"+ pos +"Colour: "+(int)c;
    }

}
