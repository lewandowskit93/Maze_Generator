package com.github.lewandowskit93.maze.viewer;


import javax.swing.JApplet;
import javax.swing.SwingUtilities;


public class MazeGeneratorApplet extends JApplet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MazeGeneratorPanel panel;
	
	@Override
	public void init() {
		super.init();
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				initGUI();
			}
		});
		
	}
	
	private void initGUI(){
		panel = new MazeGeneratorPanel();
		add(panel);
	}
	
	

}
