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
	}
	
    public void stateChanged(ChangeEvent e)
	{
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting() || slidingSliders)
		{
    		PSketch.num[index] = (int)source.getValue();


        	if(index>=Main.numSliders)
        	{
        		PSketch.colorChanged = true;
        		PSketch.indexColorChanged = index-Main.numSliders;
        		System.out.println(PSketch.num[PSketch.indexColorChanged]);

        	}
            
            if(index == 0)
            {
            	PSketch.frameRateChanged = true;
            }
        }
        
    }
}