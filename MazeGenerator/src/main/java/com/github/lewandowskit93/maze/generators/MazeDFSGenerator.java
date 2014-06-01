package com.github.lewandowskit93.maze.generators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import com.github.lewandowskit93.maze.core.Cell;
import com.github.lewandowskit93.maze.core.InvalidMazeHeightException;
import com.github.lewandowskit93.maze.core.InvalidMazeWidthException;
import com.github.lewandowskit93.maze.core.Maze;
import com.github.lewandowskit93.maze.core.MazeGenerator;

public class MazeDFSGenerator implements MazeGenerator {
	
	private int width,height;
	private Maze maze;
	private HashSet<Cell> unvisitedCells;

	public MazeDFSGenerator(int width, int height) {
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		this.width=width;
		this.height=height;
		reset();
	}

	@Override
	public Maze generateMaze() {
		return maze;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Maze getMaze() {
		return maze;
	}

	public boolean wasCellVisited(int x, int y) {
		return !unvisitedCells.contains(maze.getCell(x,y));
	}

	public void visitCell(int x, int y) {
		if(!wasCellVisited(x,y))unvisitedCells.remove(maze.getCell(x,y));
	}

	public void reset() {
		setMaze(new Maze(width,height));
	}

	public void setMaze(Maze maze) {
		if(maze.getWidth()!=width)throw new InvalidMazeWidthException(maze.getWidth());
		if(maze.getHeight()!=height)throw new InvalidMazeWidthException(maze.getHeight());
		this.maze=maze;
		unvisitedCells = new HashSet<Cell>();
		for(int x=0;x<maze.getWidth();++x){
			for(int y=0;y<maze.getHeight();++y){
				unvisitedCells.add(maze.getCell(x,y));
			}
		}
	}

}
