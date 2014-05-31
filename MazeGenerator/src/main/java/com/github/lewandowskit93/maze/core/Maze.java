package com.github.lewandowskit93.maze.core;

public class Maze {

	private int width,height;
	private Cell[][] cells;
	
	public Maze(int width, int height) {
		if(width<=0)throw new InvalidMazeWidth(width);
		if(height<=0)throw new InvalidMazeHeight(height);
		this.width=width;
		this.height=height;
		cells=createCellsArray(width,height);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}


	public Cell[][] getCells() {
		return cells;
	}
	

	private static final Cell[][] createCellsArray(int width, int height){
		Cell[][] array = new Cell[width][height];
		for(int i =0; i<width;++i){
			for(int j=0;j<height;++j){
				array[i][j] = new Cell();
			}
		}
		return array;
	}
}
