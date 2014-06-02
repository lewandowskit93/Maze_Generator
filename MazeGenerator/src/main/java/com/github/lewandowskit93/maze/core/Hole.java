package com.github.lewandowskit93.maze.core;

public class Hole {
	
	private Coordinates2D coordinates;
	private Direction direction;

	public Hole(Coordinates2D coordinates, Direction direction) {
		this.coordinates=coordinates;
		this.direction=direction;
	}

	public Coordinates2D getCoordinates() {
		return coordinates;
	}

	public Direction getRemovedWall() {
		return direction;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(!(obj instanceof Hole)) return false;
		Hole other = (Hole) obj;
		if(direction==null){
			if(other.direction!=null) return false;
		}
		else{
			if(!direction.equals(other.direction))return false;
		}
		
		if(coordinates==null){
			if(other.coordinates==null) return true;
			else return false;
		}
		else{
			return coordinates.equals(other.coordinates);
		}
		
	}

}
