package com.github.lewandowskit93.maze.exceptions;


public class InvalidMazeWidthException extends InvalidMazeSizeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private int width;
	
	public InvalidMazeWidthException(){
		super("Invalid maze width = 0");
		width=0;
	}
	
	public InvalidMazeWidthException(int width){
		super("Invalid maze width = "+width);
		this.width=width;
	}
	
	public final int getWidth(){
		return width;
	}
}
