package bin_main;

import javax.swing.*;

//JFrame that contains the animation sketch
public class DrawWindow extends JFrame
{	
	public DrawWindow()
	{
	 	this.setSize((int)(AnimationInit.screenSize.getWidth()/2.8),(int)(AnimationInit.screenSize.getWidth()/2.8));

	 	addSketch();
	 	this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void addSketch()
	{
		JPanel sketch = new JPanel();
		processing.core.PApplet p = new PSketch();
		sketch.setBounds(0, 0, 0, 0);
		sketch.add(p);
		this.add(sketch);
		p.init();
	}
	
}
