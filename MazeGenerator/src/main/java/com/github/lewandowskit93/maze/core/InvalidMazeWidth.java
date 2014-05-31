package com.github.lewandowskit93.maze.core;

public class InvalidMazeWidth extends InvalidMazeSize{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6886682188509278432L;
	private int width;
	
	public InvalidMazeWidth(){
		super("Invalid maze width = 0");
		width=0;
	}
	
	public InvalidMazeWidth(int width){
		super("Invalid maze width = "+width);
		this.width=width;
	}
	
	public int getWidth(){
		return width;
	}
}
