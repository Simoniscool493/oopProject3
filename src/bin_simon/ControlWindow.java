package bin_simon;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.util.ArrayList;

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
	public static JCheckBox drawCheckbox;
	
	public static ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	//public static ArrayList<CycleBox> cycles = new ArrayList<CycleBox>();
	//public static ArrayList<CycleBox> reversers = new ArrayList<CycleBox>();
		
	ButtonListener b = new ButtonListener();
	MenuActionListener m = new MenuActionListener();	
	
	GridBagConstraints c = new GridBagConstraints();
	
	JPanel titlePanel = new JPanel();
	JPanel controlPanel = new JPanel();

	int numControlBoxes = 0;

	public void createWindow()
	{
		init();
		
		this.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		
		titlePanel.add(guiText("Graphics Control Menu"));
		
		String[] animationTypes = { "Circle", "Star", "Square", "Bouncing", "Pendulum", "Orbit"};
		JComboBox animationList = new JComboBox(animationTypes);
		animationList.setSelectedIndex(0);
		animationList.addActionListener(m);
		
		makeCheckBox("Background",false,KeyEvent.VK_B);
		makeCheckBox("Pause Animation",false,KeyEvent.VK_P);
		makeCheckBox("Holding Sliders",true,KeyEvent.VK_S);
		makeCheckBox("Random Stroke",false,KeyEvent.VK_R);
		makeCheckBox("Random Fill",false,KeyEvent.VK_F);

		controlPanel.add(animationList);
		addControlBoxes();
		
		controlPanel.setPreferredSize(new Dimension(componentWidth,componentHeight/2));

		c.gridy = 0;
		this.add(titlePanel,c);
		
		c.gridy = 1;
		this.add(controlPanel,c);
		
		addSliders();
		
		this.pack();
	}
	
	public void addControlBoxes()
	{
		for(int i=0;i<numControlBoxes;i++)
		{
			controlPanel.add(boxes.get(i));
		}
	}
	
	public void addSliders()
	{
		for(int i=0;i<Main.numSliders;i++)
		{
			c.gridy++;
			//makeCycleBox(i,"Repeat");
			//makeCycleBox(i,"Reverse");
			this.add(makeSlider(i),c);
			//this.add(cycles.get(i),c);
			//this.add(reversers.get(i),c);

		}
	}
	
	/*
	public void makeCycleBox(int i,String s)
	{
		CycleBox c = new CycleBox(s,i);
		c.setSelected(false);
		c.setMnemonic(i+48);
		c.addItemListener(b);
		if(s == "Repeat")
		{
			cycles.add(c);
		}
		else if(s == "Reverse")
		{
			reversers.add(c);
		}
		//return c;
	}
*/
	
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
	
	public void makeCheckBox(String name,boolean selected,int k)
	{
		JCheckBox c = new JCheckBox(name);
		c.setSelected(selected);
		c.setMnemonic(KeyEvent.VK_B);
		c.addItemListener(b);
		boxes.add(c);
		numControlBoxes++;
		//return c;
	}
	
	
	public JLabel guiText(String s)
	{
		JLabel l = new JLabel(s,SwingConstants.CENTER);
		l.setFont(font);
		l.setPreferredSize(new Dimension(componentWidth,componentHeight/2));
		return l;
	}
	
}

