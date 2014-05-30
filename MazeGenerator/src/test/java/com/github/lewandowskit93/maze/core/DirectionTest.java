package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DirectionTest {

	@Test
	public void thereShouldBeFourDirections(){
		assertEquals(4,Direction.values().length);
	}
	
	@Test
	@Parameters(value = {"NORTH","EAST","SOUTH","WEST"})
	public void directionShouldExist(String direction){
		assertNotNull(Direction.valueOf(direction));
	}
	
	private static final Object[] getDirectionsWithItsOrdinals(){
		return new Object[]{
				new Object[]{"NORTH", 0},
				new Object[]{"EAST",1},
				new Object[]{"SOUTH",2},
				new Object[]{"WEST",3}
		};
	}
	
	
	@Test
	@Parameters(method = "getDirectionsWithItsOrdinals")
	public void directionOrdinalShouldBeEqualToProvided(String direction,int value){
		assertEquals(value,Direction.valueOf(direction).ordinal());
	}
	
}
