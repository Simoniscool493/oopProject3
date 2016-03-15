package bin;

import processing.core.PApplet;
import processing.core.PVector;

public class Sliders  
{
	PApplet parent;
	PVector pos;

	int x, y;
	float scale;
	int C;

	Sliders(int y, int C, PApplet p)
	{
		parent = p;
		this.scale =  255.0f;
		this.C = C;
		this.pos = new PVector(parent.map(scale, 0, 255,150,parent.width-150),y);
	}

	public void drawShape()
	{
		pos.x = parent.map(scale, 0, 255,150,parent.width-150);
		parent.pushMatrix();
		parent.translate(PApplet.map(scale, 0, 255, 150, parent.width-150), pos.y);
		parent.stroke(0);
		parent.fill(C);
		parent.ellipse(0, 0, 25,25);
		parent.popMatrix();

	}
}
