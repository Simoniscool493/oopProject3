package bin_simon;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

class ButtonListener implements ItemListener
{	
	public ButtonListener()
	{
	}

	public void itemStateChanged(ItemEvent e) 
	{
	    Object source = e.getItemSelectable();
	    String s = ((JCheckBox)source).getText();
	    System.out.println(s);
	
	    if (s == "Background") 
	    {
	    	Sketch.showBackground = !Sketch.showBackground;
	    }
	    else if (s == "Holding Sliders") 
	    {
	    	SliderListener.slidingSliders = !SliderListener.slidingSliders;
	    }
	    else if (s == "Pause Animation") 
	    {
	    	Sketch.enableDraw = !Sketch.enableDraw;
	    }
	    else if (s == "Random Stroke") 
	    {
	    	Sketch.randomStroke = !Sketch.randomStroke;
	    }
	    else if (s == "Random Fill") 
	    {
	    	Sketch.randomFill = !Sketch.randomFill;
	    }
	    /*
	    else if(s == "Repeat")
	    {
	    	int num = ((CycleBox)source).id;
	    	Sketch.repeating[num] = !(Sketch.repeating[num]);
	    }
	    else if(s == "Reverse")
	    {
	    	int num = ((CycleBox)source).id;
	    	Sketch.reversed[num] = !(Sketch.reversed[num]);
	    }
	    */

	}
}

