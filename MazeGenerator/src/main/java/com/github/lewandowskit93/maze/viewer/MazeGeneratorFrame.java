package com.github.lewandowskit93.maze.viewer;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

public class MazeGeneratorFrame extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MazeGeneratorPanel panel;
	

	public MazeGeneratorFrame() throws HeadlessException {
		super();
		// TODO Auto-generated constructor stub
	}

	public MazeGeneratorFrame(GraphicsConfiguration gc) {
		super(gc);
		initGUI();
	}

	public MazeGeneratorFrame(String title, GraphicsConfiguration gc) {
		super(title, gc);
		initGUI();
	}

	public MazeGeneratorFrame(String title) throws HeadlessException {
		super(title);
		initGUI();
	}

	private void initGUI(){
		panel = new MazeGeneratorPanel();
		add(panel);
	}

}
