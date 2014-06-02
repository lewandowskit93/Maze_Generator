package com.github.lewandowskit93.maze.viewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JApplet;

import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.generators.MazeDFSGenerator;

public class MazeApplet extends JApplet implements MazeTilesLoader{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MazeViewerPanel mazeViewerPanel;
	
	@Override
	public void init() {
		super.init();
		setBackground(Color.BLACK);
		//setSize(800,600);
		MazeDFSGenerator generator = new MazeDFSGenerator(15, 15);
		
		mazeViewerPanel = new MazeViewerPanel(getWidth(),getHeight());
		mazeViewerPanel.getMazePanel().setMaze(generator.generateMaze());
		mazeViewerPanel.getMazePanel().setMazeTiles(loadTiles());
		add(mazeViewerPanel);
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

}
