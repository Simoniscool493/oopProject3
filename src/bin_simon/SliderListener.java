package bin_simon;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SliderListener implements ChangeListener 
{
	int index;
	
	public SliderListener(int n)
	{
		index = n;
		System.out.println(index);
	}
	
    public void stateChanged(ChangeEvent e) 
	{
        JSlider source = (JSlider)e.getSource();
        //if (!source.getValueIsAdjusting()) if commented out, will only change the variable when the mouse button is released
		{
            Main.num[index] = (int)source.getValue();
            System.out.println(index);
        }    
    }
}