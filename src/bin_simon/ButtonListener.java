package bin_simon;

import java.awt.event.*;

class ButtonListener implements ItemListener
{	
	public ButtonListener()
	{
	}

	public void itemStateChanged(ItemEvent e) 
	{
	    Object source = e.getItemSelectable();
	
	    if (source == ControlWindow.backgroundCheckbox) 
	    {
	    	Sketch.showBackground = !Sketch.showBackground;
	    }
	    if (source == ControlWindow.sliderCheckbox) 
	    {
	    	SliderListener.slidingSliders = !SliderListener.slidingSliders;
	    }
	    if (source == ControlWindow.drawCheckbox) 
	    {
	    	Sketch.enableDraw = !Sketch.enableDraw;
	    }

	}
}

