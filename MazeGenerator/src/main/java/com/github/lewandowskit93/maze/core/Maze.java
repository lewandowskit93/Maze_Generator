package com.github.lewandowskit93.maze.core;

public class Maze {

	private int width,height;
	private Cell[][] cells;
	
	public Maze(int width, int height) throws
	InvalidMazeWidthException, InvalidMazeHeightException
	{
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		this.width=width;
		this.height=height;
		cells=createCellsArray(width,height);
	}

	public Maze(int width, int height, Cell[][] cells) throws 
	InvalidMazeWidthException, InvalidMazeHeightException,
	InvalidCellsArrayHeightException,InvalidCellsArrayWidthException
	{	
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		if(height!=cells.length)throw new InvalidCellsArrayHeightException(height);
		for(int i=0;i<height;++i){
			if(width!=cells[i].length)throw new InvalidCellsArrayWidthException(width);
		}
		this.width=width;
		this.height=height;
		this.cells=cells;
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
		Cell[][] array = new Cell[height][width];
		for(int i =0; i<height;++i){
			for(int j=0;j<width;++j){
				array[i][j] = new Cell();
			}
		}
		return array;
	}

	public Cell getCell(int x, int y) throws InvalidCellCoordinatesException{
		if(x<0 || x>=width || y<0 || y>=height)throw new InvalidCellCoordinatesException(x,y);
		return cells[y][x];
	}
}
