package com.github.lewandowskit93.maze.core;

import java.util.EnumSet;

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
				if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
				if(y>0)return true;
				else return false;
			}
			case EAST:{
				if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
				if(x<(width-1))return true;
				else return false;
			}
			case SOUTH:{
				if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
				if(y<(height-1)) return true;
				else return false;
			}
			case WEST:{
				if(y<0 || y>=height  || x<0 || x>=width)throw new InvalidCellCoordinatesException(x, y);
				if(x>0)return true;
				else return false;
			}
			default:{
				return false;
			}
		}
	}

	public Coordinates2D getNeighbourCoordinates(int x, int y, Direction direction) throws NoNeighbourException, InvalidCellCoordinatesException{
		if(!hasNeighbour(x,y,direction)){
			throw new NoNeighbourException(direction);
		}
		switch(direction){
			case NORTH:{
				return new Coordinates2D(x,y-1);
			}
			case EAST:{
				return new Coordinates2D(x+1,y);
			}
			case SOUTH:{
				return new Coordinates2D(x,y+1);
			}
			case WEST:{
				return new Coordinates2D(x-1,y);
			}
			default:{
				throw new NoNeighbourException(direction);
			}
		}
	}

	public void removeWall(int x, int y, Direction direction) throws InvalidCellCoordinatesException{
		getCell(x,y).removeWall(direction);
		if(hasNeighbour(x, y, direction)){
			Coordinates2D neighbourCoords = getNeighbourCoordinates(x, y, direction);
			Cell neighbour = getCell(neighbourCoords.getX(),neighbourCoords.getY());
			switch(direction){
				case NORTH:{
					neighbour.removeWall(Direction.SOUTH);
					break;
				}
				case EAST:{
					neighbour.removeWall(Direction.WEST);
					break;
				}
				case WEST:{
					neighbour.removeWall(Direction.EAST);
					break;
				}
				case SOUTH:{
					neighbour.removeWall(Direction.NORTH);
					break;
				}
			}
		}
	}

	public void addWall(int x, int y, Direction direction) throws InvalidCellCoordinatesException{
		getCell(x,y).addWall(direction);
		if(hasNeighbour(x, y, direction)){
			Coordinates2D neighbourCoords = getNeighbourCoordinates(x, y, direction);
			Cell neighbour = getCell(neighbourCoords.getX(),neighbourCoords.getY());
			switch(direction){
				case NORTH:{
					neighbour.addWall(Direction.SOUTH);
					break;
				}
				case EAST:{
					neighbour.addWall(Direction.WEST);
					break;
				}
				case SOUTH:{
					neighbour.addWall(Direction.NORTH);
					break;
				}
				case WEST:{
					neighbour.addWall(Direction.EAST);
					break;
				}
			}
		}
	}

	public EnumSet<Direction> getNeighbours(int x, int y) throws InvalidCellCoordinatesException{
		EnumSet<Direction> neighbours = EnumSet.noneOf(Direction.class);
		for(Direction direction : EnumSet.allOf(Direction.class)){
			if(hasNeighbour(x, y, direction))neighbours.add(direction);
		}
		return neighbours;
	}
}
