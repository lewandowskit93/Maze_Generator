package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;

import java.util.EnumSet;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DirectionTest {

	@Test
	public void thereShouldExactlyFourDirections(){
		assertEquals(4,Direction.values().length);
	}
	
	@Test
	@Parameters(value = {"NORTH","EAST","SOUTH","WEST"})
	public void directionShouldExist(String direction){
		assertNotNull(Direction.valueOf(direction));
	}
	
	@SuppressWarnings("unused")
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
	public void directionOrdinalShouldBeEqualToProvided(String direction,int ordinal){
		assertEquals(ordinal,Direction.valueOf(direction).ordinal());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidDirectionCombinations(){
		return new Object[]{
				new Object[]{0,EnumSet.noneOf(Direction.class)},
				new Object[]{1,EnumSet.of(Direction.NORTH)},
				new Object[]{2,EnumSet.of(Direction.EAST)},
				new Object[]{3,EnumSet.of(Direction.EAST,Direction.NORTH)},
				new Object[]{4,EnumSet.of(Direction.SOUTH)},
				new Object[]{5,EnumSet.of(Direction.SOUTH,Direction.NORTH)},
				new Object[]{6,EnumSet.of(Direction.SOUTH,Direction.EAST)},
				new Object[]{7,EnumSet.of(Direction.SOUTH,Direction.EAST,Direction.NORTH)},
				new Object[]{8,EnumSet.of(Direction.WEST)},
				new Object[]{9,EnumSet.of(Direction.WEST,Direction.NORTH)},
				new Object[]{10,EnumSet.of(Direction.WEST,Direction.EAST)},
				new Object[]{11,EnumSet.of(Direction.WEST,Direction.EAST,Direction.NORTH)},
				new Object[]{12,EnumSet.of(Direction.WEST,Direction.SOUTH)},
				new Object[]{13,EnumSet.of(Direction.WEST,Direction.SOUTH,Direction.NORTH)},
				new Object[]{14,EnumSet.of(Direction.WEST,Direction.SOUTH,Direction.EAST)},
				new Object[]{15,EnumSet.allOf(Direction.class)}
		};
	}
	
	@Test
	@Parameters(method = "getValidDirectionCombinations")
	public void shouldReturnDirectionsEnumSetRepresentationOfIntegerRepresentation(int i, EnumSet<Direction> representation){
		assertEquals(representation,Direction.getFromIntValue(i));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidDirectionIntegerRepresentations(){
		return new Object[]{-1,-2,-4,-8,-16,-17,-32,16,17,32};
	}
	
	@Test(expected = IllegalArgumentException.class)
	@Parameters(method = "getInvalidDirectionIntegerRepresentations")
	public void shouldBeUnableToGetDirectionsEnumSetRepresentationOfIntegerRepresentation(int value){
		Direction.getFromIntValue(value);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getDirectionsWithValues(){
		return new Object[]{
				new Object[]{"NORTH", 1},
				new Object[]{"EAST",2},
				new Object[]{"SOUTH",4},
				new Object[]{"WEST",8}
		};
	}
	
	@Test
	@Parameters(method = "getDirectionsWithValues")
	public void shouldReturnIntegerRepresentationOfDirection(Direction direction, int value){
		assertEquals(value,Direction.getIntValueOf(direction));
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToGetIntegerRepresentationOfDirection(){
		Direction.getIntValueOf((Direction)null);
	}
	
	@Test
	@Parameters(method = "getValidDirectionCombinations")
	public void shouldReturnIntegerRepresentationOfDirectionsEnumSet(int value, EnumSet<Direction> directions){
		assertEquals(value,Direction.getIntValueOf(directions));
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToGetIntegerRepresentationOfDirectionsEnumSet(){
		Direction.getIntValueOf((EnumSet<Direction>)null);
	}
	
}
