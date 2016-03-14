package bin;

import processing.core.PApplet;
import processing.core.PVector;

public class Sliders extends  PApplet
{
	PVector pos;
	  
	  int x, y;
	  float scale;
	  int C;
	  
	  Sliders(int y, int C)
	  {
	    this.scale =  255.0f;
	    this.C = C;
	    this.pos = new PVector(map(scale, 0, 255,150,width-150),y);
	  }
	  
	  void drawShape()
	  {
	    pos.x = map(scale, 0, 255,150,width-150);
	    pushMatrix();
	    translate(map(scale, 0, 255, 150, width-150), pos.y);
	    stroke(0);
	    fill(C);
	    ellipse(0, 0, 25,25);
	    popMatrix();
	    
	  }
}
