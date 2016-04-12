package bin_simon;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class SliderListener implements ChangeListener 
{
	int index;
	public static boolean slidingSliders = true;
	
	public SliderListener(int n)
	{
		index = n;
		System.out.println(index);
	}
	
    public void stateChanged(ChangeEvent e)
	{
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting() || slidingSliders)
		{
        	PSketch.num[index] = (int)source.getValue();
            System.out.println(index);
            
            if(index == 0)
            {
            	PSketch.frameRateChanged = true;
            }
        }
        
    }
}