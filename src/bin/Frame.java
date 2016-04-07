package bin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.awt.event.*;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame { // implements ActionListener, KeyListener,
									// MenuListener {

	// Getting screen size to make layout
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	double screenWidth = screenSize.getWidth();
	double screenHeight = screenSize.getHeight();

	// Some fields
	// ButtonListener bListen = new ButtonListener();
	JMenuBar menuBar;
	JMenu useIt, seeItRun, viewTheCode, exit;
	JMenuItem SirOpen, sirSave, sirType, sirDir;
	JMenuItem vtcOpen, vtcSave, vtcType, vtcDir;

	// Some fields
	// ButtonListener bListen = new ButtonListener();
	// For sliders
	int sliderMin = 0;
	int sliderMax = 255;
	int sliderInit = 0;
	int componentWidth = (int) screenSize.getWidth() / 4;
	int componentHeight = 50;

	Color backgroundColor = new Color(204, 255, 255);

	public Frame() {

		Pixel.screenSize = (int) screenHeight - 100;

		this.setTitle("Pixel Art Tool");
		this.setSize(1000, 1000);
		this.setLayout(new BorderLayout());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init() {
		JPanel sketch = new JPanel();
		Pixel pixel = new Pixel();

		// sketch.setLayout(new BorderLayout());
		sketch.add(pixel);
		sketch.setPreferredSize(new Dimension(Pixel.screenSize, Pixel.screenSize));
		sketch.setBackground(backgroundColor);

		JPanel toolBar = new JPanel();
		toolBar.setLayout(new GridLayout(2, 1));
		toolBar.setBackground(backgroundColor);

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new GridLayout(4, 2, 10, 5));
		buttonsPanel.setBackground(Color.WHITE);

		// BUTTONS
		// Draw
		JButton drawButton = new JButton("Draw (D)");
		buttonsPanel.add(drawButton);

		// Erase
		JButton eraseButton = new JButton("Erase (E)");
		buttonsPanel.add(eraseButton);

		JButton gridButton = new JButton("Show Grid (T)");
		buttonsPanel.add(gridButton);

		JButton EraseallButton = new JButton("Erase all");
		buttonsPanel.add(EraseallButton);

		// Button action listeners
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

		EraseallButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Pixel.squares.clear();
			}

		});

		// Blank JLabels
		JLabel blank = new JLabel();
		buttonsPanel.add(blank);
		
		//Initial color set
		JPanel colourPre = new JPanel();
		colourPre.setBackground(new Color(Pixel.r, Pixel.g, Pixel.b));
		buttonsPanel.add(colourPre);

		// Adding sliders
		JPanel sliderPanel = new JPanel();
		sliderPanel.setBackground(Color.WHITE);

		sliderPanel.setLayout(new GridLayout(6, 1));

		JLabel redLabel = new JLabel("Red");
		JSlider red = new JSlider();
		red = makeSlider();

		JLabel greenLabel = new JLabel("Green");
		JSlider green = new JSlider();
		green = makeSlider();

		JLabel blueLabel = new JLabel("Blue");
		JSlider blue = new JSlider();
		blue = makeSlider();

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
		leftTool.setBackground(backgroundColor);

		// To control background Colour, Found out you can't reuse the same
		// labels
		JPanel sliderBackPanel = new JPanel();
		sliderBackPanel.setBackground(Color.WHITE);

		sliderBackPanel.setLayout(new GridLayout(6, 1));

		JLabel redBackLabel = new JLabel("Red");
		JSlider redBack = new JSlider();
		redBack = makeSlider();

		JLabel greenBackLabel = new JLabel("Green");
		JSlider greenBack = new JSlider();
		greenBack = makeSlider();

		JLabel blueBackLabel = new JLabel("Blue");
		JSlider blueBack = new JSlider();
		blueBack = makeSlider();

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
		add(sketch, BorderLayout.CENTER);
		add(toolBar, BorderLayout.EAST);
		add(leftTool, BorderLayout.WEST);
		leftTool.add(sliderBackPanel);
		toolBar.add((buttonsPanel));
		toolBar.add(sliderPanel);

		pixel.init();

		/*
		 * menuBar = new JMenuBar();
		 * 
		 * useIt = new JMenu("Using File explorer"); useIt.addMenuListener(new
		 * MenuListener() {
		 * 
		 * @Override public void menuSelected(MenuEvent e) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void menuDeselected(MenuEvent e) { // TODO
		 * Auto-generated method stub
		 * 
		 * }
		 * 
		 * 
		 * @Override public void menuCanceled(MenuEvent e) { // TODO
		 * Auto-generated method stub
		 * 
		 * } }); menuBar.add(useIt);
		 * 
		 * exit= new JMenu("Exit"); exit.setMnemonic(KeyEvent.VK_X);
		 * exit.addMenuListener(new thisMenuListener()); menuBar.add(exit);
		 * 
		 * seeItRun = new JMenu("Seeing it run"); seeItRun.addMenuListener(new
		 * thisMenuListener()); useIt.add(seeItRun);
		 * 
		 * viewTheCode = new JMenu("Viewing the code");
		 * viewTheCode.addMenuListener(new thisMenuListener());
		 * useIt.add(viewTheCode);
		 * 
		 * SirOpen= new JMenuItem("Open a file", new ImageIcon
		 * ("images/open.gif")); SirOpen.addActionListener(this);
		 * seeItRun.add(SirOpen);
		 * 
		 * sirSave = new JMenuItem("Save a file", new ImageIcon
		 * ("images/save.gif")); sirSave.addActionListener(this);
		 * seeItRun.add(sirSave);
		 * 
		 * sirType = new JMenuItem("select a file type", new ImageIcon
		 * ("images/type.gif")); sirType.addActionListener(this);
		 * seeItRun.add(sirType);
		 * 
		 * sirDir = new JMenuItem("select a file type", new ImageIcon
		 * ("images/dir.gif"));
		 * 
		 * sirDir.addActionListener(this); seeItRun.add(sirDir);
		 * 
		 * this.setJMenuBar(menuBar); add(lblCode);
		 */

	}

	public JSlider makeSlider() {
		JSlider slider = new JSlider(JSlider.HORIZONTAL, sliderMin, sliderMax, sliderInit);
		slider.setPreferredSize(new Dimension(componentWidth, componentHeight));
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBackground(Color.WHITE);

		Hashtable colorLabel = new Hashtable<>();
		colorLabel.put(new Integer(0), new JLabel("0"));
		colorLabel.put(new Integer(255), new JLabel("255"));

		slider.setLabelTable(colorLabel);
		return slider;
	}

	public static void main(String[] args) {
		Frame f = new Frame();
		/*
		 * MenuBar m = new MenuBar(); maximiseFrame(Frame());
		 * m.setVisible(true);
		 */
	}

	/*
	 * @Override public void keyPressed(KeyEvent arg0) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void keyReleased(KeyEvent arg0) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * @Override public void keyTyped(KeyEvent arg0) { // TODO Auto-generated
	 * method stub
	 * 
	 * }
	 * 
	 * 
	 * 
	 * public void actionPerformed(ActionEvent e) { //...Get information from
	 * the action event... //...Display it in the text area... }
	 * 
	 * public void itemStateChanged(ItemEvent e) { //...Get information from the
	 * item event... //...Display it in the text area... }
	 */
}
