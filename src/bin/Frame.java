package bin;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Frame extends JFrame{
	
	//Some fields
	ButtonListener bListen = new ButtonListener();
	
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
		processing.core.PApplet pixel = new Pixel();
		
		//sketch.setLayout(new BorderLayout());
		sketch.add(pixel);
		sketch.setPreferredSize(new Dimension(500, 500));
		
		JPanel buttonsPanel = new JPanel();
		//buttonsPanel.setLayout();
		
		/*2 buttons for now... just a test
		Color palet and sliders should be a color of its own anyway*/
		
		//Draw
		JButton Button = new JButton("Draw");
		setPreferredSize(new Dimension(100,50));
		buttonsPanel.add(Button);
		
		//Erase
		Button = new JButton("Erase");
		buttonsPanel.add(Button);
		
		//Add things into frames
		add(sketch, BorderLayout.WEST);
		add(buttonsPanel);
		pixel.init();
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
	}

}
