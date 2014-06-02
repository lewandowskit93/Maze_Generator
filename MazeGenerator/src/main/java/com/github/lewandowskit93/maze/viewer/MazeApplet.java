package com.github.lewandowskit93.maze.viewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JPanel;

import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.generators.MazeDFSGenerator;

public class MazeApplet extends JApplet implements MazeTilesLoader, ComponentListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MazeViewerPanel mazeViewerPanel;
	private MenuPanel menuPanel;
	private JPanel panel;
	
	@Override
	public void init() {
		super.init();
		setBackground(Color.BLACK);
		//setSize(800,600);
		addComponentListener(this);
		mazeViewerPanel = new MazeViewerPanel((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight()));
		mazeViewerPanel.getMazePanel().setMaze(null);
		mazeViewerPanel.getMazePanel().setMazeTiles(loadTiles());
		
		menuPanel = new MenuPanel((int)Math.floor(getWidth()*0.2),(int)Math.floor(getHeight()));
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
		add(panel);
		panel.add(mazeViewerPanel);
		panel.add(menuPanel);
		mazeViewerPanel.setMinimumSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
		mazeViewerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		menuPanel.setMinimumSize(new Dimension(10,getHeight()));
		menuPanel.setPreferredSize(new Dimension((int)Math.min(Math.floor(getWidth()*0.2),200),(int)Math.floor(getHeight())));
		menuPanel.setMaximumSize(new Dimension(200,getHeight()));
		menuPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		menuPanel.getGenerateMazeButton().addActionListener(this);
		setVisible(true);
	}

	@Override
	public HashMap<Integer, BufferedImage> loadTiles() {
		
		
		HashMap<Integer, BufferedImage> tiles = new HashMap<Integer, BufferedImage>();
		for(int i=0;i<16;++i){
			BufferedImage tile = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D)tile.getGraphics();
			g2d.setBackground(Color.BLACK);
			g2d.clearRect(0,0,32,32);
			g2d.setColor(Color.WHITE);
			BasicStroke stroke = new BasicStroke(3);
			g2d.setStroke(stroke);
			if(Direction.getFromIntValue(i).contains(Direction.NORTH))g2d.drawRect(0, 1, 32, 0);
			if(Direction.getFromIntValue(i).contains(Direction.SOUTH))g2d.drawRect(0, 30, 32, 0);
			if(Direction.getFromIntValue(i).contains(Direction.WEST))g2d.drawRect(1, 0, 0, 32);
			if(Direction.getFromIntValue(i).contains(Direction.EAST))g2d.drawRect(30, 0, 0, 32);
			tiles.put(i, tile);
		}
		return tiles;
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
			if(mazeViewerPanel!=null){
				mazeViewerPanel.setMinimumSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
				//mazeViewerPanel.setPreferredSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
				//mazeViewerPanel.setMaximumSize(new Dimension((int)Math.floor(getWidth()*0.9),(int)Math.floor(getHeight())));
			}
			if(menuPanel!=null){
				menuPanel.setMinimumSize(new Dimension(10,getHeight()));
				menuPanel.setPreferredSize(new Dimension((int)Math.min(Math.floor(getWidth()*0.2),200),getHeight()));
				menuPanel.setMaximumSize(new Dimension(200,getHeight()));
			}
		}
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(menuPanel!=null){
			if(e.getSource()==menuPanel.getGenerateMazeButton()){
				MazeDFSGenerator generator = new MazeDFSGenerator(menuPanel.getMazeWidth(), menuPanel.getMazeHeight());
				mazeViewerPanel.getMazePanel().setMaze(generator.generateMaze());
				mazeViewerPanel.repaint();
			}
		}
	}

}
