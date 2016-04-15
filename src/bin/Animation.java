package bin;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Animation {
	//Fields
	ArrayList<PImage> frames = new ArrayList<PImage>();
	int frameCount;
	int frame;
	boolean loop;
	
	PApplet parent;
	
	Animation(ArrayList<PImage> frames, boolean loop, PApplet p)
	{
		parent = p;
		this.frame = 0;
		this.frames = frames;
		this.loop = loop;
		this.frameCount = frames.size();
	}
	
	public void runAnimation()
	{
		for(int i = 0; i < frameCount; i++)
		{
			parent.background(Pixel.backR, Pixel.backG, Pixel.backB);
			parent.tint(255,255);
			parent.image(frames.get(i),0,0);
			parent.println(i);
			
			if(loop = true && i >= frameCount)
			{
				i = 0;
			}
		}
		//resetting tint
		parent.tint(255, 50);
	}
}
