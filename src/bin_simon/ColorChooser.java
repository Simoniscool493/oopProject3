package bin_simon;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class ColorChooser extends JPanel
{
	static int colorSliderMin = 0;
	static int colorSliderMax = 255;
	static int colorSliderInit = 255;
	
	int index;
	int which;

	public ColorChooser(int wh,JFrame container,int sliderIndex,int x,int y)
	{
		which = wh;
		
		this.setPreferredSize(new Dimension(MainWindow.componentWidth/2,MainWindow.componentHeight));
		this.setBackground(new Color(0,0,0));
		
		MainWindow.c.gridx = x;
		MainWindow.c.gridy = y;
		
		container.add(this,MainWindow.c);
		
		index = sliderIndex;
		
		for(int i=0;i<3;i++)
		{
			MainWindow.c.gridy++;
			JSlider slider = new ColorSlider(which,index+i,i,JSlider.HORIZONTAL,colorSliderMin,colorSliderMax,colorSliderInit);

			slider = initializeSlider(slider,Main.colorNames[i]);
			
			slider.addChangeListener(new SliderListener(index+i));
			container.add(slider,MainWindow.c);
		}
		
	}
	
	public JSlider initializeSlider(JSlider s,String name)
	{
		s.setPreferredSize(new Dimension(MainWindow.componentWidth/2,MainWindow.componentHeight));
		s.setMajorTickSpacing(5);
		s.setMinorTickSpacing(1);
		s.setPaintTicks(false);
		s.setPaintLabels(false);
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),name);		
		s.setBorder(title);
		return s;
	}


}
