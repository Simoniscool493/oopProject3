package bin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

	// Some fields
	// ButtonListener bListen = new ButtonListener();
	JMenuBar menuBar;
	JMenu useIt, seeItRun, viewTheCode, exit;
	JMenuItem SirOpen, sirSave, sirType, sirDir;
	JMenuItem vtcOpen, vtcSave, vtcType, vtcDir;

	// Some fields
	// ButtonListener bListen = new ButtonListener();
	// For sliders
	static int sliderMin = 0;
	static int sliderMax = 255;
	static int sliderInit = 0;
	static int componentWidth = 400;
	static int componentHeight = 50;

	public Frame() {
		this.setTitle("Pixel Art Tool");
		this.setSize(1000, 1000);
		this.setLayout(new BorderLayout());
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void init() {
		JPanel sketch = new JPanel();
		Pixel pixel = new Pixel();

		// sketch.setLayout(new BorderLayout());
		sketch.add(pixel);
		sketch.setPreferredSize(new Dimension(500, 500));

		JPanel toolBar = new JPanel();
		toolBar.setLayout(new GridLayout(2, 1));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.WHITE);

		/*
		 * 2 buttons for now... just a test Color palet and sliders should be a
		 * color of its own anyway
		 */

		// Draw
		JButton drawButton = new JButton("Draw (D)");
		setPreferredSize(new Dimension(100, 50));
		buttonsPanel.add(drawButton);

		// Erase
		JButton eraseButton = new JButton("Erase (E)");
		buttonsPanel.add(eraseButton);

		JButton gridButton = new JButton("Show Grid (T)");
		buttonsPanel.add(gridButton);

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

		// Adding sliders
		JPanel sliderPanel = new JPanel();

		sliderPanel.setLayout(new GridLayout(3, 1));

		JSlider red = new JSlider();
		red = makeSlider();
		JSlider green = new JSlider();
		green = makeSlider();
		JSlider blue = new JSlider();
		blue = makeSlider();

		// Slider listeners
		red.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.r = (int) source.getValue();
				pixel.setColour();
			}
		});

		green.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.g = (int) source.getValue();
				pixel.setColour();
			}
		});

		blue.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();

				Pixel.b = (int) source.getValue();
				pixel.setColour();
			}
		});

		sliderPanel.add(red);

		sliderPanel.add(green);

		sliderPanel.add(blue);

		// Add things into frames
		add(sketch, BorderLayout.CENTER);
		add(toolBar, BorderLayout.EAST);
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
