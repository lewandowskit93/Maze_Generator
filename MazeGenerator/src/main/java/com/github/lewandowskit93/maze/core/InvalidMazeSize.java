package com.github.lewandowskit93.maze.core;

public class InvalidMazeSize extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4350670631298273214L;

	public InvalidMazeSize() {
		
	}

	public InvalidMazeSize(String s) {
		super(s);
	}

}
