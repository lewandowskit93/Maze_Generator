package com.github.lewandowskit93.maze.core;

public class InvalidMazeHeightException extends InvalidMazeSizeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int height;
	
	public InvalidMazeHeightException(){
		super("Invalid maze height = 0");
		height=0;
	}
	
	public InvalidMazeHeightException(int height){
		super("Invalid maze height = "+height);
		this.height=height;
	}
	
	public final int getHeight(){
		return height;
	}
}
