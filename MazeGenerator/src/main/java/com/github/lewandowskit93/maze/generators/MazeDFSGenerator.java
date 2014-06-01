package com.github.lewandowskit93.maze.generators;

import com.github.lewandowskit93.maze.core.InvalidMazeHeightException;
import com.github.lewandowskit93.maze.core.InvalidMazeWidthException;
import com.github.lewandowskit93.maze.core.Maze;
import com.github.lewandowskit93.maze.core.MazeGenerator;

public class MazeDFSGenerator implements MazeGenerator {
	
	private int width,height;

	public MazeDFSGenerator(int width, int height) {
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		this.width=width;
		this.height=height;
	}

	@Override
	public Maze generateMaze() {
		return null;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
