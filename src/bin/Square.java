package bin;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PVector;

public class Square 
{
	  PApplet parent;
	  PVector pos;
	  float boxSize=0;
	  float x;
	  float y; 
	  int c; 
	  
	  Square(float x, float y, int c,PApplet p)
	  {
		parent = p;
	    this.x = x;
	    this.y = y;
	    this.c = c;
	    this.pos = new PVector(x+(boxSize/2),y+(boxSize/2));
	  }
	  
	  public void drawShape()
	  {
	    parent.rectMode(PConstants.CORNER);
	    parent.stroke(c);
	    parent.fill(c);
	    parent.rect(x, y, boxSize,boxSize);
	  }

}
