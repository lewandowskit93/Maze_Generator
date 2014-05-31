package com.github.lewandowskit93.maze.core;

import java.util.EnumSet;

public class Cell implements Cloneable{

	private EnumSet<Direction> walls;

	public Cell(EnumSet<Direction> walls) throws NullPointerException{
		setWalls(walls);
	}

	public Cell() {
		setWalls(EnumSet.allOf(Direction.class));
	}

	public boolean isSurrounded() {
		return walls.size()==Direction.values().length;
	}

	public EnumSet<Direction> getWalls() {
		return walls;
	}

	public void setWalls(EnumSet<Direction> walls) throws NullPointerException{
		if(walls==null)throw new NullPointerException("Unable to set cell walls to null.");
		this.walls=walls;
	}

	public boolean hasWall(Direction wall) throws NullPointerException{
		if(wall==null)throw new NullPointerException("Wall cannot be null.");
		return walls.contains(wall);
	}

	public void clear(){
		setWalls(EnumSet.noneOf(Direction.class));
	}

	public void surround(){
		setWalls(EnumSet.allOf(Direction.class));
	}

	public void addWall(Direction wall) throws NullPointerException{
		walls.add(wall);
	}

	public void removeWall(Direction wall) throws NullPointerException{
		if(wall==null)throw new NullPointerException("Wall cannot be null.");
		walls.remove(wall);
	}

	public EnumSet<Direction> getPossibleDirections() {
		return EnumSet.complementOf(walls);
	}

	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(!(obj instanceof Cell))return false;
		Cell other = (Cell) obj;
		return walls.equals(other.walls);
	}
	
	@Override
	public Cell clone(){
		return new Cell(walls.clone());
	}

}
