package bin_main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

import javax.swing.*;

public class Main
{	
	public static JFrame f;
	
	public static void main(String[] args)
	{		
		f = new JFrame();
		f.setSize(new Dimension(1000,1000));
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p = new JPanel();
		f.add(p);
		
		
		JButton b1 = new JButton("Animation Window");
		b1.setPreferredSize(new Dimension(500,300));
		b1.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				AnimationInit.begin();
				f.setVisible(false); 
				//f.dispose();
			}
		});
		p.add(b1);
		
		JButton b2 = new JButton("Pixel Art");
		b2.setPreferredSize(new Dimension(500,300));
		b2.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Frame.begin();
				f.setVisible(false); 
				f.dispose(); 
			}
		});
		p.add(b2);
		
		f.pack();

	}	
} 