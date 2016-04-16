package bin_simon;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.colorchooser.*;
import javax.swing.*;

public class ColorListener implements ChangeListener
{
    public void stateChanged(ChangeEvent e)
	{
    	ColorSelectionModel source = (ColorSelectionModel)e.getSource();        
        int i = 0;
        
        for(ColorSelectionModel csm:MainWindow.colorChoosers)
        {
        if(MainWindow.colorChoosers.get(i) == source)
        {
        	PSketch.indexColorChanged = i;
        	PSketch.colorChanged = true;
        	PSketch.newColor = (((ColorSelectionModel)source).getSelectedColor());
        	break;
        }
        	i++;
        }
        
        
    }

}
