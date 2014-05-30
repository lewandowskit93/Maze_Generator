package com.github.lewandowskit93.maze.core;

import java.util.EnumSet;

public class Cell {

	private EnumSet<Direction> walls;

	public Cell(EnumSet<Direction> walls) {
		setWalls(walls);
	}

	public Cell() {
		setWalls(EnumSet.allOf(Direction.class));
	}

	public boolean isSurrounded() {
		return walls.size()==Direction.values().length;
	}

	public EnumSet<Direction> getWalls() {
		return walls.clone();
	}

	public void setWalls(EnumSet<Direction> walls) {
		if(walls==null)throw new NullPointerException("Unable to set cell walls to null.");
		this.walls=walls.clone();
	}

	public boolean hasWall(Direction wall) {
		if(wall==null)throw new NullPointerException("Wall cannot be null.");
		return walls.contains(wall);
	}

	public void clear() {
		setWalls(EnumSet.noneOf(Direction.class));
	}

	public void surround() {
		setWalls(EnumSet.allOf(Direction.class));
	}

	public void addWall(Direction wall) {
		walls.add(wall);
	}

	public void removeWall(Direction wall) {
		if(wall==null)throw new NullPointerException("Wall cannot be null.");
		walls.remove(wall);
	}

	public EnumSet<Direction> getPossibleDirections() {
		return EnumSet.complementOf(walls);
	}


}
