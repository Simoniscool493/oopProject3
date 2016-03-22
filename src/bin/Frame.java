package bin;

import java.awt.GridLayout;

import javax.swing.*;

public class Frame extends JFrame{
	
	
	public Frame()
	{
		this.setSize(600, 600);
		addSketch();
		this.setVisible(true);
	}
	
	public void addSketch()
	{
		JPanel sketch = new JPanel();
		processing.core.PApplet pixel = new Pixel();
		sketch.setLayout(null);
		sketch.add(pixel);
		this.add(sketch);
		addButtons();
		pixel.init();
	}
	
	public void addButtons()
	{
		JPanel buttons = new JPanel();
		buttons.setBounds(500, 0 ,150,100);
		buttons.setLayout(new GridLayout());
		
		/*2 buttons for now... just a test
		Color palet and sliders should be a color of its own anway*/
		JButton drawButton = new JButton("Draw");
		buttons.add(drawButton);
		
		JButton eraseButton = new JButton("Erase");
		buttons.add(eraseButton);
		
		//Add buttons into frame
		this.add(buttons);
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
	}

}
