package bin_main;

import java.awt.event.*;
import javax.swing.*;

class MenuActionListener implements ActionListener 
{
	  public void actionPerformed(ActionEvent e) 
	  {
	    System.out.println("Selected: " + e.getActionCommand());
	    if(e.getActionCommand() == "Save as image")
	    {
	    	PSketch.saveImage = true;
	    }
	    else if(e.getActionCommand() == "comboBoxChanged")
	    {
	    	JComboBox cb = (JComboBox)e.getSource();
	    	String chosen = (String)cb.getSelectedItem();	    	
	    	PSketch.currentAnimation = chosen;
	    }

	  }
}