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
	
	    if (source == ControlWindow.byn) 
	    {
	    	Main.showBackground = !Main.showBackground;
	    }
	}
}

