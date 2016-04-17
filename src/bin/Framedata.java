package bin;

import java.util.ArrayList;

import processing.core.PImage;

public class Framedata {
	
	ArrayList<Square> pixels = new ArrayList<Square>();
	PImage lastFrame;

	Framedata(ArrayList<Square> pixels, PImage lastFrame)
	{
		this.pixels = pixels;
		this.lastFrame = lastFrame;
	}
	
	public void showArray()
	{
		System.out.println(pixels);
	}
}
