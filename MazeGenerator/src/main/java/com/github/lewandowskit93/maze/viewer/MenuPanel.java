package com.github.lewandowskit93.maze.viewer;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton generateMazeButton;

	public MenuPanel() {
		super(true);
		//setLayout(null);
		setSize(800,600);
		add(generateMazeButton);
	}
	
	public MenuPanel(int width, int height) {
		super(true);
		//setLayout(null);
		setSize(width,height);
		generateMazeButton = new JButton("Generate maze",null);
		add(generateMazeButton);
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

	public final JButton getGenerateMazeButton() {
		return generateMazeButton;
	}
	
}
