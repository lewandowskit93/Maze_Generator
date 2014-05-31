package com.github.lewandowskit93.maze.core;

public class NoNeighbourException extends MazeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Direction direction;
	
	public NoNeighbourException(Direction direction){
		super("Cell doesnt have "+direction+" neighbour");
		this.direction=direction;
	}

	public final Direction getDirection() {
		return direction;
	}
	
	
}
