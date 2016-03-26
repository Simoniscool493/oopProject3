package bin_simon;

import javax.swing.*;

public class PFrame extends JFrame
{
	public PFrame()
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
	
}
