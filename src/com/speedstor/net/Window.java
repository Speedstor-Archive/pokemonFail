package com.speedstor.net;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas{
	private static final long serialVersionUID = -9103274694006854698L;
	
	public Window(int width, int height, String title, Core core) {
		JFrame frame = new JFrame();
		
		Dimension dimension = new Dimension(width, height);
		frame.setMaximumSize(dimension);
		frame.setMinimumSize(dimension);
		frame.setPreferredSize(dimension);
		
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(3);
		frame.add(core);
		frame.setTitle(title);
		frame.setLocationRelativeTo(null);
		
		core.start();
	}
}
