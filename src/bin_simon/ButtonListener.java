package bin_simon;

import java.awt.event.*;
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
	    else if(s == "Repeat")
	    {
	    	int num = ((CycleBox)source).id;
	    	Sketch.repeating[num] = !(Sketch.repeating[num]);
	    }
	}
}

