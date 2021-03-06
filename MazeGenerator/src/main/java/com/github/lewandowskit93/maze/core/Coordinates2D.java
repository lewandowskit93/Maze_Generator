package com.github.lewandowskit93.maze.core;

public class Coordinates2D implements Cloneable{
	
	private int x,y;
	
	public Coordinates2D(){
		this(0,0);
	}
	
	public Coordinates2D(int x, int y){
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null)return false;
		if(!(obj instanceof Coordinates2D))return false;
		Coordinates2D other = (Coordinates2D) obj;
		if(x!=other.x || y!=other.y)return false;
		return true;
	}
	
	@Override
	public Coordinates2D clone(){
		return new Coordinates2D(x,y);
	}
}
