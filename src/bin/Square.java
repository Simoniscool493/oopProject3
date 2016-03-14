package bin;

import processing.core.PApplet;
import processing.core.PVector;

public class Square extends PApplet
{
	  PVector pos;
	  float boxSize=0;
	  float x;
	  float y; 
	  int c; 
	  
	  Square(float x, float y, int c)
	  {
	    this.x = x;
	    this.y = y;
	    this.c = c;
	    this.pos = new PVector(x+(boxSize/2),y+(boxSize/2));
	  }
	  
	  void drawShape()
	  {
	    rectMode(CORNER);
	    stroke(c);
	    fill(c);
	    rect(x, y, boxSize,boxSize);
	  }

}
