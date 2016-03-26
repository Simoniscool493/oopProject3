package bin_simon;

import java.awt.event.*;

class MenuActionListener implements ActionListener 
{
	  public void actionPerformed(ActionEvent e) 
	  {
	    System.out.println("Selected: " + e.getActionCommand());
	    if(e.getActionCommand() == "Save as image")
	    {
			Sketch.saveImage = true;
	    }
	  }
}