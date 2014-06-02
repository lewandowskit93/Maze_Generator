package com.github.lewandowskit93.maze.exceptions;

public class InvalidCellsArrayWidthException extends InvalidCellsArraySizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private int width;

	public InvalidCellsArrayWidthException() {
		super("Invalid cells array width = 0");
		width=0;
	}
	
	public InvalidCellsArrayWidthException(int width){
		super("Invalid cells array width = "+width);
		this.width=width;
	}
	
	public final int getWidth(){
		return width;
	}
}
