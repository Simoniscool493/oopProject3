package bin_simon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ControlWindow extends JFrame
{
	static int sliderMin = 0;
	static int sliderMax = 100;
	static int sliderInit = 50;
	static int componentWidth = 700;
	static int componentHeight = 100;
	static Font font = new Font("Serif",Font.BOLD,30);
	
	public static JCheckBox backgroundCheckbox;
	public static JCheckBox sliderCheckbox;

	
	ButtonListener b = new ButtonListener();
	MenuActionListener m = new MenuActionListener();	

	public void createWindow()
	{
		JFrame frame = new JFrame("Simple GUI");
		init();
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		
		JPanel panel1 = new JPanel();
		
		backgroundCheckbox = makeCheckBox("Background",true,KeyEvent.VK_B);
		panel1.add(backgroundCheckbox);
		
		sliderCheckbox = makeCheckBox("Holding Sliders",true,KeyEvent.VK_S);
		panel1.add(sliderCheckbox);
		
		c.gridy = 0;
		this.add(guiText("Graphics Control Menu"),c);
		c.gridy = 1;
		this.add(makeSlider(0),c);
		c.gridy = 2;
		this.add(makeSlider(1),c);
		c.gridy = 3;
		this.add(panel1, c);
 
		this.pack();
	}
	
	public void init()
	{
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		makeMenu();
	}
	
	public void makeMenu()
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

		this.setJMenuBar(menuBar);
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
	
	public JCheckBox makeCheckBox(String name,boolean selected,int k)
	{
		JCheckBox c = new JCheckBox(name);
		c.setSelected(selected);
		c.setMnemonic(KeyEvent.VK_B);
		c.addItemListener(b);
		return c;
	}
	
	public JLabel guiText(String s)
	{
		JLabel l = new JLabel(s,SwingConstants.CENTER);
		l.setFont(font);
		l.setPreferredSize(new Dimension(componentWidth,componentHeight));
		return l;
	}
	
}

