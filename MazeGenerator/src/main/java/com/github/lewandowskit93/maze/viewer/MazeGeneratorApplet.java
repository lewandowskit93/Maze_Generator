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
		//setBackground(Color.BLACK);
		//setSize(800,600);
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				initGUI();
			}
		});
		
	}
	
	private void initGUI(){
		//addComponentListener(this);
		panel = new MazeGeneratorPanel();
		add(panel);
	}
	
	

}
