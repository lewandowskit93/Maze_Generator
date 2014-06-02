package com.github.lewandowskit93.maze.viewer;

import java.awt.Color;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JPanel;

public class MazeViewerPanel extends JPanel implements ComponentListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private MazePanel mazePanel;
	private JPanel panel;

	public MazeViewerPanel() {
		super(true);
		setLayout(null);
		setSize(800,600);
		addComponentListener(this);
		createMazePanel();
	}
	
	public MazeViewerPanel(int width, int height) {
		super(true);
		setLayout(null);
		setSize(width,height);
		addComponentListener(this);
		createMazePanel();
	}
	
	private void createMazePanel(){
		panel = new JPanel(null, true);
		panel.setSize((int)(getWidth()*0.98), (int)(getHeight()*0.98));
		panel.setLocation(5,5);
		panel.setBackground(Color.BLACK);
		mazePanel = new MazePanel((int)(panel.getWidth()*0.98), (int)(panel.getHeight()*0.98), true);
		panel.add(mazePanel);
		mazePanel.setLocation(5,5);
		add(panel);
	}

	public final MazePanel getMazePanel() {
		return mazePanel;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		if(arg0.getComponent()==this){
			panel.setSize((int)(getWidth()*0.98), (int)(getHeight()*0.98));
			mazePanel.setSize((int)(panel.getWidth()*0.98), (int)(panel.getHeight()*0.98));
			repaint();
		}
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
