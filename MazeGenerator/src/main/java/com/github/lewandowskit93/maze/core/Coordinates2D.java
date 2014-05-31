package com.github.lewandowskit93.maze.core;

public class Coordinates2D {
	
	private int x,y;
	
	Coordinates2D(){
		this(0,0);
	}
	
	Coordinates2D(int x, int y){
		this.x=x;
		this.y=y;
	}

	public final int getX() {
		return x;
	}

	public final void setX(int x) {
		this.x = x;
	}

	public final int getY() {
		return y;
	}

	public final void setY(int y) {
		this.y = y;
	}
	
}
