package bin_simon;

import javax.swing.*;

public class ColorSlider extends JSlider
{
	int index;
	int color;
	int which;
	
	public ColorSlider(int wh,int ind,int col,int w,int x,int y,int z)
	{
		super(w,x,y,z);
		index = ind;
		color = col;
		which = wh;
	}
}
