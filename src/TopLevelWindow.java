//package bin;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;


public class TopLevelWindow
{
	public static void createWindow()
	{
		//System.out.println("memes");
		Font font = new Font("Serif",Font.BOLD,30);
		
		int min = 0;
		int init = 50;
		int max = 100;
		
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel textLabel_1 = new JLabel("Graphics Controls",SwingConstants.CENTER);
		textLabel_1.setFont(font);
		textLabel_1.setPreferredSize(new Dimension(1000,100));

		JSlider slider_1 = new JSlider(JSlider.HORIZONTAL,min,max,init);
		slider_1.setPreferredSize(new Dimension(1000,120));
		slider_1.setMajorTickSpacing(10);
		slider_1.setMinorTickSpacing(1);
		slider_1.setPaintTicks(true);
		slider_1.setPaintLabels(true);
		slider_1.addChangeListener(new SliderListener());

		frame.getContentPane().add(textLabel_1,BorderLayout.CENTER);
		frame.getContentPane().add(slider_1,BorderLayout.SOUTH);


		frame.setLocationRelativeTo(null); 	//relative to null puts it in the center
		frame.pack();						//Causes this Window to be sized to fit the preferred size and layouts of its subcomponents. sort of essential as far as i can tell, nothing shows otherwise
		//frame.setSize(500,500);
		frame.setVisible(true);
	}
}

class SliderListener implements ChangeListener 
{
    public void stateChanged(ChangeEvent e) 
	{
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting())
		{
            int num = (int)source.getValue();
			System.out.println(num);
        }    
    }
}