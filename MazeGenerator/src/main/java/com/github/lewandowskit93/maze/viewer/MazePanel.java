package com.github.lewandowskit93.maze.viewer;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;





import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.core.Maze;

public class MazePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Maze maze;
	private HashMap<Integer, BufferedImage> tiles;

	public MazePanel() {
		super(true);
		setLayout(null);
		setSize(800,600);
	}

	public MazePanel(boolean doubleBuffered) {
		super(doubleBuffered);
		setLayout(null);
		setSize(800,600);
	}

	public MazePanel(int width, int height) {
		super(true);
		setLayout(null);
		setSize(width,height);
	}

	public MazePanel(int width, int height, boolean doubleBuffered) {
		super(doubleBuffered);
		setLayout(null);
		setSize(width,height);
	}

	public synchronized void setMaze(Maze maze) {
		this.maze=maze;
	}

	public synchronized Maze getMaze() {
		return maze;
	}

	@Override
	public void paintComponent(Graphics g) {
		paintMaze(g);
	}

	
	public synchronized void paintMaze(Graphics g) {
		double tWidth = getWidth();
		double tHeight = getHeight();
		tWidth/=(maze.getWidth());
		tHeight/=(maze.getHeight());
		tWidth=Math.floor(tWidth);
		tHeight=Math.floor(tHeight);
		if(maze!=null){
			for(int y=0;y<maze.getHeight();++y){
				for(int x=0;x<maze.getWidth();++x){
					int tileID = Direction.getIntValueOf(maze.getCell(x,y).getWalls());
					BufferedImage tile = tiles.get(tileID);
					int px = (int)(x*tWidth);
					int py = (int)(y*tHeight);			
					if(tile!=null)g.drawImage(tile, px, py, (int)tWidth, (int)tHeight, null);
					//System.out.println((int)(x*tWidth)+" "+(int)(y*tHeight)+" "+(int)tWidth+" "+(int)tHeight);
				}
			}
		}
	}

	public void setMazeTiles(HashMap<Integer, BufferedImage> tiles) {
		this.tiles=tiles;
	}

	public HashMap<Integer, BufferedImage> getMazeTiles() {
		return this.tiles;
	}


	
}
