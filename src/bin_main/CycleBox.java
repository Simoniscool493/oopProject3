package bin_main;

import javax.swing.JCheckBox;

//class for the special checkboxes that animate the sketch,
//allowing the user to activate different variables in the 
//shape being displayed.
public class CycleBox extends JCheckBox
{
	int id;
	
	public CycleBox(String s,int i)
	{
		super(s);
		id = i;
	}
}
