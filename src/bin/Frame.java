package bin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.awt.event.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import javax.swing.*;
import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Frame extends JFrame  implements ActionListener, KeyListener, MenuListener {
	
	//Some fields
	//ButtonListener bListen = new ButtonListener();
	JMenuBar menuBar;
	JMenu useIt, seeItRun,viewTheCode,exit;
	JMenuItem SirOpen,sirSave, sirType, sirDir;
	JMenuItem vtcOpen, vtcSave, vtcType,vtcDir;

	
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
		
		menuBar = new JMenuBar();
		
		useIt = new JMenu("Using File explorer");
		useIt.addMenuListener(new thisMenuListener());
		menuBar.add(useIt);
		
		exit= new JMenu("Exit");
		exit.setMnemonic(KeyEvent.VK_X);
		exit.addMenuListener(new thisMenuListener());
		menuBar.add(exit);
		
		seeItRun = new JMenu("Seeing it run");
		seeItRun.addMenuListener(new thisMenuListener());
		useIt.add(seeItRun);
		
		viewTheCode = new JMenu("Viewing the code");
		viewTheCode.addMenuListener(new thisMenuListener());
		useIt.add(viewTheCode);
		
		SirOpen= new JMenuItem("Open a file", new ImageIcon ("images/open.gif"));
		SirOpen.addActionListener(this);
		seeItRun.add(SirOpen);
		
		sirSave = new JMenuItem("Save a file", new ImageIcon ("images/save.gif"));
		sirSave.addActionListener(this);
		seeItRun.add(sirSave);
		
		sirType = new JMenuItem("select a file type", new ImageIcon ("images/type.gif"));
		sirType.addActionListener(this);
		seeItRun.add(sirType);
		
		sirDir = new JMenuItem("select a file type", new ImageIcon ("images/dir.gif"));
		
		sirDir.addActionListener(this);
		seeItRun.add(sirDir);
		
		this.setJMenuBar(menuBar);
		add(lblCode);
	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		MenuBar m = new MenuBar();
		maximiseFrame(Frame());
		m.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

    public void actionPerformed(ActionEvent e) {
        //...Get information from the action event...
        //...Display it in the text area...
    }

    public void itemStateChanged(ItemEvent e) {
        //...Get information from the item event...
        //...Display it in the text area...
    }
}
