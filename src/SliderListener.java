import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SliderListener implements ChangeListener 
{
    public void stateChanged(ChangeEvent e) 
	{
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting())
		{
            Main.num1 = (int)source.getValue();
        }    
    }
}