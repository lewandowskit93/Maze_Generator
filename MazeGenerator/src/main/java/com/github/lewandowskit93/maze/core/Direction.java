package com.github.lewandowskit93.maze.core;

import java.util.EnumSet;

public enum Direction {
	NORTH,EAST,SOUTH,WEST;
	
	public static EnumSet<Direction> getFromIntValue(int value) throws IllegalArgumentException{
		validateRangeFor(value);
		EnumSet<Direction> set = EnumSet.noneOf(Direction.class);
		for(Direction direction : Direction.values()){
			int mask = getIntValueOf(direction);
			if((value & mask) == mask){
				set.add(direction);
			}
		}
		return set;
	}
	
	private static void validateRangeFor(int value) throws IllegalArgumentException{
		int begin = getRangeBegin();
		int end = getRangeEnd();
		if(value < 0 || value > end){
			throw new IllegalArgumentException(
					"Illegal integer representation of direction values. "+
					"Should belong to range <"+begin+","+end+">.");
		}
	}
	
	private static int getRangeBegin(){
		return 0;
	}
	
	private static int getRangeEnd(){
		return (int)Math.pow(2, Direction.values().length)-1;
	}

	public static int getIntValueOf(Direction direction) throws NullPointerException{
		return 1<<direction.ordinal();
	}

	public static int getIntValueOf(EnumSet<Direction> directions) throws NullPointerException{
		int value = 0;
		for(Direction direction : directions){
			value+=getIntValueOf(direction);
		}
		return value;
	}
}
