package com.github.lewandowskit93.maze.core;

public class InvalidCellsArrayHeightException extends InvalidCellsArraySizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private int height;

	public InvalidCellsArrayHeightException() {
		super("Invalid cells array height = 0");
		height=0;
	}
	
	public InvalidCellsArrayHeightException(int height){
		super("Invalid cells array height = "+height);
		this.height=height;
	}
	
	public final int getHeight(){
		return height;
	}
}
