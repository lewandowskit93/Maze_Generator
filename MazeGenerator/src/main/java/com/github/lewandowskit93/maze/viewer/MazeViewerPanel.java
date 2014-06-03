package com.github.lewandowskit93.maze.viewer;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
		panel.setSize((int)(getWidth()*0.95), (int)(getHeight()*0.95));
		panel.setLocation((int)Math.floor((getWidth()*0.05)/2),(int)Math.floor((getHeight()*0.05)/2));
		panel.setBackground(Color.BLACK);
		mazePanel = new MazePanel((int)(panel.getWidth()*0.95), (int)(panel.getHeight()*0.95), true);
		panel.add(mazePanel);
		mazePanel.setLocation((int)Math.floor((panel.getWidth()*0.05)/2),(int)Math.floor((panel.getHeight()*0.05)/2));
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
			if(panel!=null){
				panel.setSize((int)(getWidth()*0.95), (int)(getHeight()*0.95));
				panel.setLocation((int)Math.floor((getWidth()*0.05)/2),(int)Math.floor((getHeight()*0.05)/2));
			}
			if(mazePanel!=null){
				mazePanel.setSize((int)(panel.getWidth()*0.95), (int)(panel.getHeight()*0.95));
				mazePanel.setLocation((int)Math.floor((panel.getWidth()*0.05)/2),(int)Math.floor((panel.getHeight()*0.05)/2));
			}
			//validate();
			//repaint();
		}
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Color color = getBackground();
		Color color2 = color.darker().darker();
		GradientPaint gradientPaint = new GradientPaint(0,0,color,0,getHeight(),color2);
		g2d.setPaint(gradientPaint);
		g2d.fillRect(0,0,getWidth(),getHeight());
	}
}
