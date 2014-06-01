package com.github.lewandowskit93.maze.generators;

import com.github.lewandowskit93.maze.core.InvalidMazeHeightException;
import com.github.lewandowskit93.maze.core.InvalidMazeWidthException;
import com.github.lewandowskit93.maze.core.Maze;
import com.github.lewandowskit93.maze.core.MazeGenerator;

public class MazeDFSGenerator implements MazeGenerator {
	
	private int width,height;
	private Maze maze;

	public MazeDFSGenerator(int width, int height) {
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		this.width=width;
		this.height=height;
		this.maze=null;
	}

	@Override
	public Maze generateMaze() {
		maze = new Maze(width,height);
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

}
