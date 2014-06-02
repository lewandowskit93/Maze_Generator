package com.github.lewandowskit93.maze.viewer;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class MenuPanel extends JPanel implements AdjustmentListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton generateMazeButton;
	private JScrollBar mazeWidthScrollBar;
	private JScrollBar mazeHeightScrollBar;
	private JLabel mazeWidthLabel;
	private JLabel mazeHeightLabel;

	public MenuPanel() {
		super(true);	
		setSize(800,600);
		initialize();
	}
	
	public MenuPanel(int width, int height) {
		super(true);
		setSize(width,height);
		initialize();
	}
	
	private void initialize(){
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		generateMazeButton = new JButton("Generate maze",null);
		generateMazeButton.setAlignmentX(JButton.CENTER_ALIGNMENT);
		add(generateMazeButton);
		
		mazeWidthLabel = new JLabel("Maze width:");
		mazeWidthLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(mazeWidthLabel);
		
		mazeWidthScrollBar = new JScrollBar();
		mazeWidthScrollBar.setMinimum(1);
		mazeWidthScrollBar.setMaximum(80);
		mazeWidthScrollBar.addAdjustmentListener(this);
		mazeWidthScrollBar.setValue(15);
		mazeWidthScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		mazeWidthScrollBar.setAlignmentX(JScrollBar.CENTER_ALIGNMENT);
		add(mazeWidthScrollBar);
		
		mazeHeightLabel = new JLabel("Maze height:");
		mazeHeightLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		add(mazeHeightLabel);
		
		mazeHeightScrollBar = new JScrollBar();
		mazeHeightScrollBar.setMinimum(1);
		mazeHeightScrollBar.setMaximum(80);
		mazeHeightScrollBar.addAdjustmentListener(this);
		mazeHeightScrollBar.setValue(15);
		mazeHeightScrollBar.setOrientation(JScrollBar.HORIZONTAL);
		mazeHeightScrollBar.setAlignmentX(JScrollBar.CENTER_ALIGNMENT);
		add(mazeHeightScrollBar);
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

	public final int getMazeWidth() {
		if(mazeWidthScrollBar!=null) return mazeWidthScrollBar.getValue();
		else return 1;
	}
	
	public final int getMazeHeight() {
		if(mazeHeightScrollBar!=null)return mazeHeightScrollBar.getValue();
		else return 1;
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		if(e.getSource()==mazeWidthScrollBar && mazeWidthScrollBar!=null){
			mazeWidthLabel.setText("Maze width: " + mazeWidthScrollBar.getValue());
		}
		if(e.getSource()==mazeHeightScrollBar && mazeHeightScrollBar!=null){
			mazeHeightLabel.setText("Maze height: "+mazeHeightScrollBar.getValue());
		}
	}
	
}
