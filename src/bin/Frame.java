package bin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame { // implements ActionListener, KeyListener,
									// MenuListener {

	// Getting screen size to make layout
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double screenWidth = screenSize.getWidth();
	double screenHeight = screenSize.getHeight();

	// Some fields
	// ButtonListener bListen = new ButtonListener();
	
	public JTextField filename = new JTextField();
	 

	public JTextField dir = new JTextField();
	public  JButton open = new JButton("Open"), save = new JButton("Save");


	// Some fields
	// ButtonListener bListen = new ButtonListener();
	// For sliders
	int backGroundInit = 200;
	int colorInit = 0;
	
	int sliderMin = 0;
	int sliderMax = 255;
	int componentWidth = (int) (screenSize.getWidth()*0.2);
	int componentHeight = 50;

	Color backgroundColor = new Color(204, 255, 255);
	
	GridBagConstraints gridC = new GridBagConstraints();

	public Frame() {

		Pixel.screenSize = (int) screenHeight - 100;

		this.setTitle("Pixel Art Tool");
		this.setSize(1000, 1000);
		this.setLayout(new GridBagLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public void init() {

		//Adding sketch as a component
		JPanel sketch = new JPanel();
		Pixel pixel = new Pixel();
		sketch.add(pixel);
		sketch.setLayout(new GridLayout());
		sketch.setBackground(backgroundColor);

		//Creating the toolBar on the RHS
		JPanel toolBar = new JPanel();
		toolBar.setLayout(new GridLayout(2, 1));
		toolBar.setBackground(backgroundColor);

		//ButtonPanel will be nested inside toolbar
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(5, 4, 6, 5));
		buttonsPanel.setBackground(Color.WHITE);

		// Creating BUTTONS for Button Panel
		// Draw
		JButton drawButton = new JButton("Draw (D)");
		buttonsPanel.add(drawButton);

		// Erase
		JButton eraseButton = new JButton("Erase (E)");
		buttonsPanel.add(eraseButton);

		JButton gridButton = new JButton("Show Grid (T)");
		buttonsPanel.add(gridButton);

		JButton magicPen = new JButton("Magic Pen");
		buttonsPanel.add(magicPen);
		
		JButton EraseallButton = new JButton("Erase all");
		buttonsPanel.add(EraseallButton);
		
		JButton op = new JButton("Open");
		buttonsPanel.add(op);
		
		JButton sv = new JButton("Save");
		buttonsPanel.add(sv);

	
		
		// All Button action listeners
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.mode = 1;
			}
		});

		eraseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.mode = 2;
			}
		});

		gridButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.showLines = !Pixel.showLines;
			}

		});
		
		magicPen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.mode = 3;
				
			}
		});
		
		//Check if a color is selected on the screen
		sketch.addMouseListener(pixel);

		EraseallButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.squares.clear();
			}

		});
		
		//file explorer action listeners
	    op.addActionListener(new OpenL());

	    sv.addActionListener(new SaveL());

		// Adding sliders
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.WHITE);

		sliderPanel.setLayout(new GridLayout(6, 2));

		JLabel redLabel = new JLabel("Red");
		JSlider red = new JSlider();
		red = makeSlider(colorInit);

		JLabel greenLabel = new JLabel("Green");
		JSlider green = new JSlider();
		green = makeSlider(colorInit);

		JLabel blueLabel = new JLabel("Blue");
		JSlider blue = new JSlider();
		blue = makeSlider(colorInit);
		
		//File explorer

		 dir.setEditable(false);
		 filename.setEditable(false);
		

	    
		//Initial color set for colour Preview
		JPanel colourPre = new JPanel();
		colourPre.setBackground(new Color(Pixel.r, Pixel.g, Pixel.b));
		buttonsPanel.add(colourPre);

		// Slider listeners
		red.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.r = (int) source.getValue();
				pixel.setColour();
				colourPre.setBackground(new Color(Pixel.r, Pixel.g, Pixel.b));
				colourPre.repaint();
			}
		});

		green.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.g = (int) source.getValue();
				pixel.setColour();
				colourPre.setBackground(new Color(Pixel.r, Pixel.g, Pixel.b));
				colourPre.repaint();
			}
		});

		blue.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.b = (int) source.getValue();
				pixel.setColour();
				colourPre.setBackground(new Color(Pixel.r, Pixel.g, Pixel.b));
				colourPre.repaint();
			}
		});

		JPanel leftTool = new JPanel();
		leftTool.setLayout(new GridLayout(1,1));
		leftTool.setBackground(backgroundColor);

		// To control background Colour, Found out you can't reuse the same
		// labels
		JPanel sliderBackPanel = new JPanel();
		sliderBackPanel.setBackground(Color.WHITE);

		sliderBackPanel.setLayout(new GridLayout(6, 1));

		JLabel redBackLabel = new JLabel("Red");
		JSlider redBack = new JSlider();
		redBack = makeSlider(backGroundInit);

		JLabel greenBackLabel = new JLabel("Green");
		JSlider greenBack = new JSlider();
		greenBack = makeSlider(backGroundInit);

		JLabel blueBackLabel = new JLabel("Blue");
		JSlider blueBack = new JSlider();
		blueBack = makeSlider(backGroundInit);
		
		redBack.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.backR = (int) source.getValue();
			}
		});
		
		greenBack.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.backG = (int) source.getValue();
				
			}
		});
		
		blueBack.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.backB = (int) source.getValue();
				
			}
		});
		
		// Adding Sliders into Toolbar
		sliderPanel.add(redBackLabel);
		sliderPanel.add(red);

		sliderPanel.add(greenBackLabel);
		sliderPanel.add(green);

		sliderPanel.add(blueBackLabel);
		sliderPanel.add(blue);

		// Adding sliders into leftToolbar
		sliderBackPanel.add(redLabel);
		sliderBackPanel.add(redBack);

		sliderBackPanel.add(greenLabel);
		sliderBackPanel.add(greenBack);

		sliderBackPanel.add(blueLabel);
		sliderBackPanel.add(blueBack);

		// Add things into frames
		gridC.weightx = 1;
		
		gridC.gridx = 0;
		add(leftTool, gridC);
		gridC.gridx = 1;
		add(sketch, gridC);
		gridC.gridx = 2;
		add(toolBar, gridC);
		
		leftTool.add(sliderBackPanel);
		toolBar.add((buttonsPanel));
		toolBar.add(sliderPanel);

		pixel.init();


	}

	public JSlider makeSlider(int sliderInit) {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);

		slider.setPreferredSize(new Dimension(componentWidth, componentHeight));
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBackground(Color.WHITE);

		Hashtable<Object, Object> colorLabel = new Hashtable<>();
		colorLabel.put(new Integer(0), new JLabel("0"));
		colorLabel.put(new Integer(255), new JLabel("255"));

		slider.setLabelTable(colorLabel);
		return slider;
	}
	
	//Opens a new .pix file
	  class OpenL implements ActionListener 
	  {
	    public void actionPerformed(ActionEvent e) 
	    {
	    FileNameExtensionFilter f = new FileNameExtensionFilter("Pixel", "pix");
	     JFileChooser c = new JFileChooser("./data");
	     c.setFileFilter(f);
	      
	      int opp = c.showOpenDialog(Frame.this);
	      
	      if (opp == JFileChooser.APPROVE_OPTION) 
	      {
	    	
	    	File o = c.getSelectedFile();
	    	Pixel.file=o;
	    	Pixel.loads=true;
	        System.out.println(Pixel.file.getParent());
	        System.out.println(Pixel.file.getName());
	      
	      }
	    }
	  }

	  //Saves a new .pix file with the .pix extension
	  class SaveL implements ActionListener 
	  {
	    public void actionPerformed(ActionEvent e)
	    {
	      JFileChooser c = new JFileChooser();
	      c.setCurrentDirectory(new File("./data"));

	      int sv = c.showSaveDialog(Frame.this);
	      
	      if (sv == JFileChooser.APPROVE_OPTION) 
	      {
		    String s = c.getSelectedFile().getAbsolutePath() +".pix";

		    File fi = new File(s);
		    
		    Pixel.filen=fi;
		    Pixel.save=true;
		    System.out.println(Pixel.filen.getParent());
            System.out.println(Pixel.filen.getName());

	      }

	    }
	  }

	public static void main(String[] args) {
		Frame f = new Frame();
	}
	
	
}
