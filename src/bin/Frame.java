package bin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Frame extends JFrame{
	
	//Some fields
	//ButtonListener bListen = new ButtonListener();
	//For sliders
	static int sliderMin = 0;
	static int sliderMax = 255;
	static int sliderInit = 0;
	static int componentWidth = 700;
	static int componentHeight = 100;
	
	public Frame()
	{
		this.setTitle("Pixel Art Tool");
		this.setSize(1000, 1000);
		init();
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void init()
	{
		JPanel sketch = new JPanel();
		Pixel pixel = new Pixel();
		
		//sketch.setLayout(new BorderLayout());
		sketch.add(pixel);
		sketch.setPreferredSize(new Dimension(500, 500));
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.WHITE);
		//buttonsPanel.setLayout();
		
		/*2 buttons for now... just a test
		Color palet and sliders should be a color of its own anyway*/
		
		//Draw
		JButton drawButton = new JButton("Draw");
		setPreferredSize(new Dimension(100,50));
		buttonsPanel.add(drawButton);
		
		//Erase
		JButton eraseButton = new JButton("Erase");
		buttonsPanel.add(eraseButton);
		
		//Button action listeners
		drawButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Draw mode");
				Pixel.mode = 1;
			}
		});
		
		eraseButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Erase mode");
				Pixel.mode = 2;
			}
		});
		
		//Add things into frames
		add(sketch, BorderLayout.WEST);
		add(buttonsPanel);
		pixel.init();
		
		//Adding sliders
		JPanel sliderPanel = new JPanel();
		
	}
	
	public JSlider makeSlider(int n)
	{
		JSlider slider = new JSlider(JSlider.HORIZONTAL,sliderMin,sliderMax,sliderInit);
		slider.setPreferredSize(new Dimension(componentWidth,componentHeight));
		slider.setMajorTickSpacing(10);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		//slider.addChangeListener(new SliderListener(n));
		return slider;
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
	}

}
