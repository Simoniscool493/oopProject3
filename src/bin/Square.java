package bin;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Square 
{
	PApplet parent;

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
		
		//there is a slight overlap 
		parent.rect(x, y, Pixel.boxSize, Pixel.boxSize);
	}
	
	/*Lets you see what the contents of an arralist are normally. in this example it is the field pos
	*testing so we can save the squares arraylist then reload it 
	*For whatever reason colour is negative?
	*check terminal when a square is coloured
	*/
    @Override
    public String toString() {
        return "pos:"+ pos +"Colour: "+(int)c;
    }

}
