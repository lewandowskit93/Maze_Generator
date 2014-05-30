package com.github.lewandowskit93.maze.core;

import java.util.EnumSet;

public class Cell {

	public EnumSet<Direction> walls;

	public Cell(EnumSet<Direction> walls) {
		if(walls == null){
			this.walls=EnumSet.allOf(Direction.class);
		}
		else{
			this.walls=walls.clone();
		}
	}

	public Cell() {
		this(null);
	}

	public boolean isSurrounded() {
		return walls.size()==Direction.values().length;
	}

	public EnumSet<Direction> getWalls() {
		return walls.clone();
	}


}
