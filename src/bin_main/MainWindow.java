package bin_main;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;

import java.util.ArrayList;

//main control window for the animation sketch. contains all buttons, 
//sliders and checkboxes.
public class MainWindow extends JFrame
{
	static int sliderMin = 0;
	static int sliderMax = 100;
	static int sliderInit = 50;
	
	static int componentWidth = (int)((AnimationInit.screenSize.getWidth())/5);
	static int componentHeight = (int)((AnimationInit.screenSize.getHeight())/12);
	
	static Dimension componentSize = new Dimension(componentWidth,componentHeight);
	
	static Font font = new Font("Serif",Font.BOLD,30);
	
	public static JCheckBox backgroundCheckbox;
	public static JCheckBox sliderCheckbox;
	public static JCheckBox drawCheckbox;
	
	public static ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
	public static ArrayList<CycleBox> cycles = new ArrayList<CycleBox>();
	public static ArrayList<CycleBox> reversers = new ArrayList<CycleBox>();
	public static ArrayList<ColorSelectionModel> colorChoosers = new ArrayList<ColorSelectionModel>();
	
	public static GridBagConstraints c = new GridBagConstraints();
		
	ButtonListener b = new ButtonListener();
	MenuActionListener m = new MenuActionListener();	
	ColorListener cl = new ColorListener();
		
	JPanel titlePanel = new JPanel();
	JPanel controlPanel = new JPanel();
	
	int numControlBoxes = 0;

	public void createWindow()
	{
		init();
		
		this.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 0.5;
		
		titlePanel.add(guiText("Graphics Control Menu"));
		
		String[] animationTypes = { "Circle", "Star", "Polygon", "Face", "Pendulum", "Orbit","Spiral","Square"};
		JComboBox animationList = new JComboBox(animationTypes);
		animationList.setSelectedIndex(0);
		animationList.addActionListener(m);
		
		makeCheckBox("Background",false,KeyEvent.VK_B);
		makeCheckBox("Pause",false,KeyEvent.VK_P);
		makeCheckBox("Holding Sliders",true,KeyEvent.VK_S);
		makeCheckBox("Random Stroke",false,KeyEvent.VK_R);
		makeCheckBox("Random Fill",false,KeyEvent.VK_F);
		
		JButton r = new JButton("Main Menu");
		
		r.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e) 
            {
            	Main.f.setVisible(true);
            	AnimationInit.d.dispose();
            	AnimationInit.c.dispose();
            }
        }
        );
		
		controlPanel.add(r);

		controlPanel.add(animationList);
		addControlBoxes();
		
		controlPanel.setPreferredSize(new Dimension(componentWidth,componentHeight/2));

		c.gridx = 1;
		c.gridy = 0;
		this.add(titlePanel,c);
		
		c.gridy = 1;
		this.add(controlPanel,c);
		
		addSliders();
		addColorSliders();
		
		this.pack();
	}
	
	public void addControlBoxes()
	{
		for(int i=0;i<numControlBoxes;i++)
		{
			controlPanel.add(boxes.get(i));
		}
	}
	
	public void addColorSliders()
	{		
		makeChooser("Background",2,2);
		makeChooser("Fill",2,3);
		makeChooser("Stroke",2,4);
	}
	
	public void makeChooser (String name,int w,int h)
	{
		JColorChooser j = new JColorChooser();
		
		c.gridx = w;
		c.gridy = h;

		j.setPreferredSize(new Dimension((int)(componentWidth*0.75),componentHeight));
		
		AbstractColorChooserPanel[] panels = j.getChooserPanels();
		
		for (AbstractColorChooserPanel accp : panels) 
		{
		   if(!accp.getDisplayName().equals("Swatches")) 
		   {
		      j.removeChooserPanel(accp);
		   } 
		}
		
		j.setPreviewPanel(new JPanel());
		j.getSelectionModel().addChangeListener(cl);
		colorChoosers.add(j.getSelectionModel());
		
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),name);		
		j.setBorder(title);

		this.add(j,c);
	}
		
	public JSlider makeSlider(int n)
	{
		JSlider slider = new JSlider(JSlider.HORIZONTAL,sliderMin,sliderMax,sliderInit);
		slider = initializeSlider(slider,10,1,true,true,AnimationInit.sliderNames[n],componentWidth,componentHeight);
		
		slider.addChangeListener(new SliderListener(n));
		
		return slider;
	}

	public JSlider initializeSlider(JSlider s,int majTick,int minTick,boolean paintTicks,boolean paintLabels,String name,int width,int height)
	{
		s.setPreferredSize(new Dimension(width,height));
		s.setMajorTickSpacing(majTick);
		s.setMinorTickSpacing(minTick);
		s.setPaintTicks(paintTicks);
		s.setPaintLabels(paintLabels);
		TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),name);		
		s.setBorder(title);
		return s;
	}
	
	public void addSliders()
	{
		for(int i=0;i<AnimationInit.numSliders;i++)
		{
			c.gridy++;
			
			JPanel j = new JPanel();
			j.add(makeSlider(i));

			makeCycleBox(i,"Repeat");
			makeCycleBox(i,"Reverse");
			
			JPanel k = new JPanel(new BorderLayout());
			k.add(cycles.get(i),BorderLayout.NORTH);
			k.add(reversers.get(i),BorderLayout.CENTER);
			
			j.add(k);
			this.add(j,c);
		}
	}

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
	}
	
	public void init()
	{
		//this.setResizable(false);
		this.setVisible(true);
		this.setLocation((int)(AnimationInit.screenSize.getWidth()/2),0);
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
		
		this.setJMenuBar(menuBar);
	}
	
	public void makeCheckBox(String name,boolean selected,int k)
	{
		JCheckBox c = new JCheckBox(name);
		c.setSelected(selected);
		c.setMnemonic(KeyEvent.VK_B);
		c.addItemListener(b);
		boxes.add(c);
		numControlBoxes++;
	}
	
	public JLabel guiText(String s)
	{
		JLabel l = new JLabel(s,SwingConstants.CENTER);
		l.setFont(font);
		l.setPreferredSize(new Dimension(componentWidth,componentHeight/2));
		return l;
	}
}

