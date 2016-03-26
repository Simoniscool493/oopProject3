package bin_simon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ControlWindow
{
	static int sliderMin = 0;
	static int sliderMax = 100;
	static int sliderInit = 50;
	static int componentWidth = 700;
	static int componentHeight = 100;
	static Font font = new Font("Serif",Font.BOLD,30);
	
	public static JCheckBox byn;
	
	ButtonListener b = new ButtonListener();
	MenuActionListener m = new MenuActionListener();	

	public void createWindow()
	{
		JFrame frame = new JFrame("Simple GUI");
		frame = init(frame);
		
		frame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
				
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
 
		frame.pack();
	}
	
	public JFrame init(JFrame f)
	{
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f = makeMenu(f);

		return f;
	}
	
	public JFrame makeMenu(JFrame j)
	{
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);
		
		JMenuItem menuItem1 = new JMenuItem("Save as image");
		menu.setMnemonic(KeyEvent.VK_I);
		menuItem1.addActionListener(m);
		menu.add(menuItem1);
		
		JMenuItem menuItem2 = new JMenuItem("Save as code");
		menu.setMnemonic(KeyEvent.VK_C);
		menuItem2.addActionListener(m);
		menu.add(menuItem2);

		j.setJMenuBar(menuBar);
		return j;
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

