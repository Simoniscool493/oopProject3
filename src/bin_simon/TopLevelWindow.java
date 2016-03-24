package bin_simon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class TopLevelWindow
{
	static int sliderMin = 0;
	static int sliderMax = 100;
	static int sliderInit = 50;
	static int componentWidth = 700;
	static int componentHeight = 100;
	static Font font = new Font("Serif",Font.BOLD,30);
	
	public static JCheckBox byn;

	public void createWindow()
	{
		JFrame frame = new JFrame("Simple GUI");
		ButtonListener b = new ButtonListener();
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		frame = init(frame);
				
		byn = new JCheckBox("background");
		byn.setSelected(true);
		byn.setMnemonic(KeyEvent.VK_B);
		byn.addItemListener(b);
		
		c.gridy = 0;
		frame.add(guiText("Graphics Control Menu"),c);
		c.gridy = 1;
		frame.add(makeSlider(0),c);
		c.gridy = 2;
		frame.add(makeSlider(1),c);
		c.gridy = 3;
		frame.add(byn, c);


		frame.setSize(componentWidth,1000);
		frame.pack();

	}
	
	public JFrame init(JFrame f)
	{
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		return f;
	}

	public JSlider makeSlider(int n)
	{
		JSlider slider = new JSlider(JSlider.HORIZONTAL,sliderMin,sliderMax,sliderInit);
		slider.setPreferredSize(new Dimension(componentWidth,componentHeight));
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new SliderListener(n));
		return slider;
	}
	
	public JLabel guiText(String s)
	{
		JLabel l = new JLabel(s,SwingConstants.CENTER);
		l.setFont(font);
		l.setPreferredSize(new Dimension(componentWidth,componentHeight));
		return l;
	}
	
}

