package bin_main;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

//listener class for checkboxes. flips booleans in the animation sketch.
class ButtonListener implements ItemListener
{	
	public ButtonListener()
	{
	}

	public void itemStateChanged(ItemEvent e) 
	{
	    Object source = e.getItemSelectable();
	    String s = ((JCheckBox)source).getText();
	
	    if (s == "Background") 
	    {
	    	PSketch.showBackground = !PSketch.showBackground;
	    }
	    else if (s == "Holding Sliders") 
	    {
	    	SliderListener.slidingSliders = !SliderListener.slidingSliders;
	    }
	    else if (s == "Pause") 
	    {
	    	PSketch.enableDraw = !PSketch.enableDraw;
	    }
	    else if (s == "Random Stroke") 
	    {
	    	PSketch.randomStroke = !PSketch.randomStroke;
	    }
	    else if (s == "Random Fill") 
	    {
	    	PSketch.randomFill = !PSketch.randomFill;
	    }
	    else if(s == "Repeat")
	    {
	    	int num = ((CycleBox)source).id;
	    	PSketch.repeating[num] = !(PSketch.repeating[num]);
	    }
	    else if(s == "Reverse")
	    {
	    	int num = ((CycleBox)source).id;
	    	PSketch.reversed[num] = !(PSketch.reversed[num]);
	    }
	}
}

