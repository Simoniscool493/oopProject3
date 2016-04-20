package bin_main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Frame extends JFrame {

	// Getting screen size to make layout
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double screenWidth = screenSize.getWidth();
	double screenHeight = screenSize.getHeight();

	// Some fields
	// ButtonListener bListen = new ButtonListener();

	public JButton open = new JButton("Open"), save = new JButton("Save");

	// Some fields
	// ButtonListener bListen = new ButtonListener();
	// For sliders
	int backGroundInit = 200;
	int colorInit = 0;
	int sliderMin = 0;
	int sliderMax = 255;
	int componentWidth = (int) (screenSize.getWidth() * 0.2);
	int componentHeight = 50;

	GridBagConstraints gridC = new GridBagConstraints();

	public Frame() {

		Pixel.screenSize = (int) screenHeight - 100;

		this.setTitle("Pixel Art Tool");
		this.setSize(1000, 1000);
		init();
		this.setVisible(true);
		this.setExtendedState(JFrame.ICONIFIED);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}

	public void init() {

		JPanel wrap = new JPanel(new GridBagLayout());
		wrap.setBackground(Color.WHITE);

		// Adding sketch as a component
		JPanel sketch = new JPanel();
		Pixel pixel = new Pixel();
		sketch.add(pixel);
		sketch.setBackground(Color.WHITE);

		// Creating the toolBar on the RHS
		JPanel toolBar = new JPanel();
		toolBar.setLayout(new GridLayout(2, 1));

		// ButtonPanel will be nested inside toolbar
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(7, 2, 6, 5));
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

		JButton magicPen = new JButton("Magic Pen (M)");
		buttonsPanel.add(magicPen);

		JButton flip = new JButton("Flip (F)");
		buttonsPanel.add(flip);

		JButton run = new JButton("Start/Stop Animation");
		buttonsPanel.add(run);

		JButton prev = new JButton("Previous Frame");
		buttonsPanel.add(prev);

		JButton next = new JButton("Next Frame");
		buttonsPanel.add(next);

		JButton add = new JButton("Add into Frame");
		buttonsPanel.add(add);

		JButton removeFrame = new JButton("Remove last Frame");
		buttonsPanel.add(removeFrame);

		JButton EraseallButton = new JButton("Remove all squares");
		buttonsPanel.add(EraseallButton);
		
		JButton copy = new JButton("Copy Last");
		buttonsPanel.add(copy);
		
		JButton bg = new JButton("Save with background");
		//If color is grey it means background will not be saved
		bg.setBackground(new Color(224,224,224));
		buttonsPanel.add(bg);
		
		//Lets user know what frame they're on
		JLabel textFrame = new JLabel("You're on frame " + (Pixel.frameNum+1) + " Out of " + Pixel.data.size());

		// All Button action listeners
		bg.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				Pixel.savebg =!Pixel.savebg;
				
				if(Pixel.savebg)
				{
					bg.setBackground(new Color(153,204,255));
				}
				else
				{
					bg.setBackground(new Color(224,224,224));
				}
				bg.repaint();
			}
		});
		
		//Copy last frame if button is pressed
		copy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(Pixel.data.size() > 0)
				{
					pixel.copyLastFrame();
				}
			}
		});

		//Set draw mode
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.mode = 1;
			}
		});

		//Set erase mode
		eraseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.mode = 2;
			}
		});

		//Show grid
		gridButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.showLines = !Pixel.showLines;
			}

		});

		//Set magic pen tool
		magicPen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.mode = 3;

			}
		});

		//Flips the pixels in the sketch
		flip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pixel.flip();

			}
		});

		//Runs animation
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Pixel.stages == 2) {
					Pixel.stages = 1;
					Pixel.frameCount = 0;
				} else if (Pixel.stages == 0) {
					// Do nothing
				} else if (Pixel.stages == 1) {
					Pixel.stages = 2;
				}

			}
		});

		//Add frame
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Pixel.stages == 1)
				{
					pixel.addFrame();
					textFrame.setText("You're on frame " + (Pixel.frameNum+1) + " Out of " + Pixel.data.size());
				}
			}
		});

		//Go back a frame
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pixel.prevFrame();
				textFrame.setText("You're on frame " + (Pixel.frameNum+1) + " Out of " + Pixel.data.size());
			}
		});

		//Traverse next frame
		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pixel.nextFrame();
				textFrame.setText("You're on frame " + (Pixel.frameNum+1) + " Out of " + Pixel.data.size());
			}
		});

		//Delete last frame
		removeFrame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(Pixel.data.size() > 0)
				{
					pixel.removeLastFrame();
					textFrame.setText("You're on frame " + (Pixel.frameNum+1) + " Out of " + Pixel.data.size());
				}

			}
		});

		//Erase all current pixels drawn
		EraseallButton.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Delete all pixels",  JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					Pixel.squares.clear();
				}
			}

		});


		// Adding sliders
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.WHITE);

		sliderPanel.setLayout(new GridLayout(6, 2));
		
		sliderPanel.add(textFrame);

		JLabel redLabel = new JLabel("Red");
		JSlider red = new JSlider();
		red = makeSlider(colorInit);

		JLabel greenLabel = new JLabel("Green");
		JSlider green = new JSlider();
		green = makeSlider(colorInit);

		JLabel blueLabel = new JLabel("Blue");
		JSlider blue = new JSlider();
		blue = makeSlider(colorInit);

		// Initial color set for colour Preview
		JPanel colourPre = new JPanel();
		colourPre.setBackground(new Color(Pixel.r, Pixel.g, Pixel.b));
		buttonsPanel.add(colourPre);

		// Slider listeners for the pen
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

		pixel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				colourPre.setBackground(new Color(pixel.userC));
				colourPre.repaint();
			}
		});

		JPanel leftTool = new JPanel();
		leftTool.setLayout(new GridLayout(1, 1));

		// To control background Colour, Found out you can't reuse the same
		// labels
		JPanel sliderBackPanel = new JPanel();
		sliderBackPanel.setBackground(Color.WHITE);

		sliderBackPanel.setLayout(new GridLayout(10, 1));

		JLabel bgcLabel = new JLabel("Background Colour");

		JLabel redBackLabel = new JLabel("Red");
		JSlider redBack = new JSlider();
		redBack = makeSlider(backGroundInit);

		JLabel greenBackLabel = new JLabel("Green");
		JSlider greenBack = new JSlider();
		greenBack = makeSlider(backGroundInit);

		JLabel blueBackLabel = new JLabel("Blue");
		JSlider blueBack = new JSlider();
		blueBack = makeSlider(backGroundInit);

		// Slider for frameRate
		JLabel frameLabel = new JLabel("Frame Rate");
		JSlider frameSlider = new JSlider(JSlider.HORIZONTAL, 1, 59, 59);

		frameSlider.setPreferredSize(new Dimension(componentWidth, componentHeight));
		frameSlider.setMajorTickSpacing(10);
		frameSlider.setMinorTickSpacing(1);
		frameSlider.setPaintTicks(true);
		frameSlider.setPaintLabels(true);
		frameSlider.setBackground(Color.WHITE);
		Hashtable<Object, Object> frameRLabel = new Hashtable<>();
		frameRLabel.put(new Integer(0), new JLabel("1"));
		frameRLabel.put(new Integer(60), new JLabel("59"));
		frameSlider.setLabelTable(frameRLabel);

		
		//Sets sliders for background color
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

		frameSlider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.frameRate = (int) source.getValue();
			}
		});

		// Add menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem open = new JMenuItem("Open file");
		JMenuItem saveimg = new JMenuItem("Save as image");
		JMenuItem savefile = new JMenuItem("Save as .pix");
		JMenuItem saveframes = new JMenuItem("Save all frames");
		JMenuItem exit = new JMenuItem("Back to menu");

		open.addActionListener(new Open());
		savefile.addActionListener(new Save());
		saveimg.addActionListener(new Saveimg());
		saveframes.addActionListener(new savef());

		menu.add(open);
		menu.add(saveimg);
		menu.add(saveframes);
		menu.add(savefile);
		menu.add(exit);
		
		//selecting exit
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				int reply = JOptionPane.showConfirmDialog(null, "Are you sure?", "Return to menu",  JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION)
				{
					Pixel.squares.clear();
					Main.f.setVisible(true);
					//Closes the window and opens the main menu again
					Frame.this.dispose();	
					pixel.dispose();
				}
			}
		});
		
		//This is a new jmenu item for changing the mode of drawing
		JMenu edit = new JMenu("Edit");
		JMenuItem sixTeen = new JMenuItem("16 x 16");
		JMenuItem thirtyTwo = new JMenuItem("32 x 32");
		JMenuItem sixtyFour = new JMenuItem("64 x 64");
		

		
		sixTeen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (Pixel.pixNum != 16) {
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to change modes? \n All unsaved work will be lost!", "Change modes",  JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION)
				{
						Pixel.frameNum = 0;
						Pixel.squares.clear();
						Pixel.data.clear();
						Pixel.pixNum = 16;
					}
				}
			}
		});

		thirtyTwo.addActionListener(new ActionListener() 
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if (Pixel.pixNum != 32) {
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to change modes? \n All unsaved work will be lost!", "Change modes",  JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION)
					{
						Pixel.frameNum = 0;
						Pixel.squares.clear();
						Pixel.data.clear();
						Pixel.pixNum = 32;
					}
				
				}

			}
		});

		sixtyFour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				if (Pixel.pixNum != 64) {
					int reply = JOptionPane.showConfirmDialog(null, "Are you sure you want to change modes? \n All unsaved work will be lost!", "Change",  JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION)
					{
						Pixel.frameNum = 0;
						Pixel.squares.clear();
						Pixel.data.clear();
						Pixel.pixNum = 64;
					}
				}

			}
		});

		edit.add(sixTeen);
		edit.add(thirtyTwo);
		edit.add(sixtyFour);
		menuBar.add(edit);

		this.setJMenuBar(menuBar);

		// Adding Sliders into Toolbar
		sliderPanel.add(redBackLabel);
		sliderPanel.add(red);

		sliderPanel.add(greenBackLabel);
		sliderPanel.add(green);

		sliderPanel.add(blueBackLabel);
		sliderPanel.add(blue);

		// Adding sliders into leftToolbar
		sliderBackPanel.add(bgcLabel);

		sliderBackPanel.add(redLabel);
		sliderBackPanel.add(redBack);

		sliderBackPanel.add(greenLabel);
		sliderBackPanel.add(greenBack);

		sliderBackPanel.add(blueLabel);
		sliderBackPanel.add(blueBack);

		sliderBackPanel.add(frameLabel);
		sliderBackPanel.add(frameSlider);
		
		sliderBackPanel.add(textFrame);

		// Add things into frames
		gridC.weightx = 1;

		gridC.gridx = 1;
		wrap.add(leftTool, gridC);

		gridC.gridx = 2;
		wrap.add(sketch, gridC);

		gridC.gridx = 3;
		wrap.add(toolBar, gridC);

		leftTool.add(sliderBackPanel);
		toolBar.add((buttonsPanel));
		toolBar.add(sliderPanel);

		add(wrap);

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
 
	// Opens a new .pix file. Uses the file explorer to select the path to the file 
	class Open implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Pixel.stages == 1) {

				FileNameExtensionFilter f = new FileNameExtensionFilter("Pixel", "pix");
				JFileChooser c = new JFileChooser("./data");
				c.setFileFilter(f);

				int opp = c.showOpenDialog(Frame.this);

				if (opp == JFileChooser.APPROVE_OPTION) {

					File o = c.getSelectedFile();
					Pixel.file = o;
					Pixel.loads = true;

				}
			}
		}
	}

	// Saves a new .pix file with the .pix extension if the user hasn't specified
	class Save implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Pixel.stages == 1) {
				JFileChooser c = new JFileChooser();
				c.setCurrentDirectory(new File("./data"));

				int sv = c.showSaveDialog(Frame.this);

				if (sv == JFileChooser.APPROVE_OPTION) {
					String s = c.getSelectedFile().getAbsolutePath();
					String p = ".pix";

					if (s.toLowerCase().indexOf(p.toLowerCase()) != -1) {

						File fi = new File(s);

						Pixel.filen = fi;
						Pixel.save = true;
					} else {
						s += ".pix";

						File fi = new File(s);

						Pixel.filen = fi;
						Pixel.save = true;

					}
				}
			}

		}
	}

	//Uses the file explorer to save the image to wherever the user wishes
	class Saveimg implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (Pixel.stages == 1) {
				File Dir = new File("./data/img");
				JFileChooser c = new JFileChooser();

				c.setCurrentDirectory(new File("./data/img"));

				int sv = c.showSaveDialog(Frame.this);

				if (sv == JFileChooser.APPROVE_OPTION) {
					String s = c.getSelectedFile().getAbsolutePath();
					String p = ".png";

					if (s.toLowerCase().indexOf(p.toLowerCase()) != -1) {
						Pixel.png = s;
						Pixel.svimg = true;
					} else {
						s += ".png";
						Pixel.png = s;
						Pixel.svimg = true;
					}
				}

				if (!Dir.exists()) {
					Dir.mkdir();

				}
			}

		}
	}

	//Uses the file explorer to save the frames inside a folder of the same name
	class savef implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			File Dir = new File("./data/Frame");
			JFileChooser c = new JFileChooser();

			if (!Dir.exists()) {
				Dir.mkdir();
			}

			c.setCurrentDirectory(new File("./data/Frame"));

			int sv = c.showSaveDialog(Frame.this);

			if (sv == JFileChooser.APPROVE_OPTION) {
				File s = c.getSelectedFile();

				Pixel.Dir = s;
				Pixel.fram = true;
			}

		}
	}

	public static void begin() {
		Frame f = new Frame();
	}

}

