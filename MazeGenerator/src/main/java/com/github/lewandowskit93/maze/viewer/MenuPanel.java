package com.github.lewandowskit93.maze.viewer;

import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MenuPanel() {
		super(true);
		setLayout(null);
		setSize(800,600);
	}
	
	public MenuPanel(int width, int height) {
		super(true);
		setLayout(null);
		setSize(width,height);
	}
}
