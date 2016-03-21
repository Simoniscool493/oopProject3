package bin;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		sketch.setBounds(0, 0, 0, 0);
		sketch.add(pixel);
		this.add(sketch);
		pixel.init();
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
	}

}
