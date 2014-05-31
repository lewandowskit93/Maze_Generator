package com.github.lewandowskit93.maze.core;

public class InvalidMazeHeight extends InvalidMazeSize {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1418313683084892466L;
	private int height;
	
	public InvalidMazeHeight(){
		super("Invalid maze height = 0");
		height=0;
	}
	
	public InvalidMazeHeight(int height){
		super("Invalid maze height = "+height);
		this.height=height;
	}
	
	public int getHeight(){
		return height;
	}
}
