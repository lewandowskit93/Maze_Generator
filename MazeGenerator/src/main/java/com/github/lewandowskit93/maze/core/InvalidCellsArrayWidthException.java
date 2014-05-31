package com.github.lewandowskit93.maze.core;

public class InvalidCellsArrayWidthException extends InvalidCellsArraySizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int width;

	public InvalidCellsArrayWidthException() {
		super("Invalid cells array width = 0");
		width=0;
	}
	
	public InvalidCellsArrayWidthException(int width){
		super("Invalid cells array width = "+width);
		this.width=width;
	}
	
	public int getWidth(){
		return width;
	}
}
