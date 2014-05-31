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

	public void surroundCell(int x, int y) throws InvalidCellCoordinatesException{
		getCell(x, y).surround();
	}

	public void clearCell(int x, int y) throws InvalidCellCoordinatesException{
		getCell(x, y).clear();
	}

	public boolean hasNeighbour(int x, int y, Direction direction) throws InvalidCellCoordinatesException{
		switch(direction){
			case NORTH:{
				return hasNorthNeighbour(x, y);
			}
			case EAST:{
				return hasEastNeighbour(x, y);
			}
			case SOUTH:{
				return hasSouthNeighbour(x, y);
			}
			case WEST:{
				return hasWestNeighbour(x, y);
			}
			default:{
				return false;
			}
		}
	}

	public boolean hasNorthNeighbour(int x, int y) throws InvalidCellCoordinatesException{
		if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
		if(y>0)return true;
		else return false;
	}

	public boolean hasSouthNeighbour(int x, int y) throws InvalidCellCoordinatesException{
		if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
		if(y<(height-1)) return true;
		else return false;
	}

	public boolean hasEastNeighbour(int x, int y) throws InvalidCellCoordinatesException{
		if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
		if(x<(width-1))return true;
		else return false;
	}

	public boolean hasWestNeighbour(int x, int y) throws InvalidCellCoordinatesException{
		if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
		if(x>0)return true;
		else return false;
	}

	public Coordinates2D getNeighbourCoordinates(int x, int y, Direction direction) throws NoNeighbourException, InvalidCellCoordinatesException{
		switch(direction){
			case NORTH:{
				return getNorthNeighbourCoordinates(x, y);
			}
			case EAST:{
				return getEastNeighbourCoordinates(x, y);
			}
			case SOUTH:{
				return getSouthNeighbourCoordinates(x, y);
			}
			case WEST:{
				return getWestNeighbourCoordinates(x, y);
			}
			default:{
				throw new NoNeighbourException(direction);
			}
		}
	}

	public Coordinates2D getNorthNeighbourCoordinates(int x, int y) throws NoNeighbourException, InvalidCellCoordinatesException{
		if(!hasNorthNeighbour(x,y)){
			throw new NoNeighbourException(Direction.NORTH);
		}
		return new Coordinates2D(x,y-1);
	}

	public Coordinates2D getEastNeighbourCoordinates(int x, int y) throws NoNeighbourException, InvalidCellCoordinatesException{
		if(!hasEastNeighbour(x,y)){
			throw new NoNeighbourException(Direction.EAST);
		}
		return new Coordinates2D(x+1,y);
	}

	public Coordinates2D getSouthNeighbourCoordinates(int x, int y) throws NoNeighbourException, InvalidCellCoordinatesException{
		if(!hasSouthNeighbour(x,y)){
			throw new NoNeighbourException(Direction.SOUTH);
		}
		return new Coordinates2D(x,y+1);
	}

	public Coordinates2D getWestNeighbourCoordinates(int x, int y) throws NoNeighbourException, InvalidCellCoordinatesException{
		if(!hasWestNeighbour(x,y)){
			throw new NoNeighbourException(Direction.WEST);
		}
		return new Coordinates2D(x-1,y);
	}
}
