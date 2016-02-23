package bin;

import java.awt.*;
import javax.swing.*;

public class TopLevelWindow
{
	public static void createWindow()
	{
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel textLabel = new JLabel("I'm a label in the window",SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(1000,1000));
		
		frame.getContentPane().add(textLabel,BorderLayout.CENTER);

		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
	}
}
