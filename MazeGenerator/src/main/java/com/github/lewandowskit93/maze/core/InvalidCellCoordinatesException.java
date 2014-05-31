package com.github.lewandowskit93.maze.core;

public class InvalidCellCoordinatesException extends MazeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int x,y;

	public InvalidCellCoordinatesException(){
		super("Invalid cell coordinates (0;0).");
		this.x=0;
		this.y=0;
	}
	
	public InvalidCellCoordinatesException(int x, int y){
		super("Invalid cell coordinates ("+x+";"+y+").");
		this.x=0;
		this.y=0;
	}
	
	public final int getX() {
		return x;
	}

	public final int getY() {
		return y;
	}
	
}
