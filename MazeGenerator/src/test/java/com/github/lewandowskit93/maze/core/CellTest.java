package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;

import java.util.EnumSet;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class CellTest {

	@Test
	public void byDefaultCellShouldBeSurroundedByWalls(){
		Cell cell = new Cell();
		assertTrue(cell.isSurrounded());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getNotSurroundingWalls(){
		Object wallSets[] = new Object[15];
		for(int i=0;i<15;++i){
			wallSets[i]=Direction.getFromIntValue(i);
		}
		return wallSets;
	}
	
	@Test
	@Parameters(method = "getNotSurroundingWalls")
	public void afterCreationCellShouldNotBeSurrounded(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertFalse(cell.isSurrounded());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSurroundingWalls(){
		return new Object[]{
				Direction.getFromIntValue(15),
		};
	}
	
	@Test
	@Parameters(method = "getSurroundingWalls")
	public void afterCreationCellShouldBeSurrounded(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertTrue(cell.isSurrounded());
	}
	
	@SuppressWarnings("unused")
	@Test(expected = NullPointerException.class)
	public void shouldNotBeUnableToCreateCell(){
		Cell cell = new Cell(null);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getAllWallsCombinations(){
		Object wallSets[] = new Object[16];
		for(int i=0;i<16;++i){
			wallSets[i]=Direction.getFromIntValue(i);
		}
		return wallSets;
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void shouldReturnTheWallsThatTheCellWasCreatedWith(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertEquals(walls,cell.getWalls());
	}
	
	@SuppressWarnings("unused")
	private static Object[] getAllDirections(){
		return Direction.values();
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void removingWallFromConstructingSetShouldHaveNoImpactOnTheCellWalls(Direction wall){
		EnumSet<Direction> walls = EnumSet.allOf(Direction.class);
		Cell cell = new Cell(walls);
		walls.remove(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void addingWallToConstructingSetShouldHaveNoImpactOnTheCellWalls(Direction wall){
		EnumSet<Direction> walls = EnumSet.noneOf(Direction.class);
		Cell cell = new Cell(walls);
		walls.add(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void removingWallShouldHaveNoImpactOnTheCellWalls(Direction wall){
		Cell cell = new Cell(EnumSet.allOf(Direction.class));
		EnumSet<Direction> walls = cell.getWalls();
		walls.remove(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void addingWallShouldHaveNoImpactOnTheCellWalls(Direction wall){
		Cell cell = new Cell(EnumSet.noneOf(Direction.class));
		EnumSet<Direction> walls = cell.getWalls();
		walls.add(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void shouldBeAbleToSetWalls(EnumSet<Direction> walls){
		Cell cell = new Cell();
		cell.setWalls(walls);
		assertEquals(walls,cell.getWalls());
	}

	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToSetCellWalls(){
		Cell cell = new Cell();
		cell.setWalls(null);
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void removingWallFromWallsUsedToSetCellWallsShouldHaveNoImpactOnCellWalls(Direction wall){
		EnumSet<Direction> walls = EnumSet.allOf(Direction.class);
		Cell cell = new Cell();
		cell.setWalls(walls);
		walls.remove(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void addingWallFromWallsUsedToSetCellWallsShouldHaveNoImpactOnCellWalls(Direction wall){
		EnumSet<Direction> walls = EnumSet.noneOf(Direction.class);
		Cell cell = new Cell();
		cell.setWalls(walls);
		walls.add(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidCombinations(){
		return new Object[]{
			new Object[]{EnumSet.of(Direction.NORTH),Direction.NORTH},
			new Object[]{EnumSet.of(Direction.EAST),Direction.EAST},
			new Object[]{EnumSet.of(Direction.SOUTH),Direction.SOUTH},
			new Object[]{EnumSet.of(Direction.WEST),Direction.WEST},
			new Object[]{EnumSet.allOf(Direction.class),Direction.NORTH},
			new Object[]{EnumSet.allOf(Direction.class),Direction.EAST},
			new Object[]{EnumSet.allOf(Direction.class),Direction.SOUTH},
			new Object[]{EnumSet.allOf(Direction.class),Direction.WEST}
		};
	}
	
	@Test
	@Parameters(method = "getValidCombinations")
	public void cellShouldHaveWall(EnumSet<Direction> walls, Direction wall){
		Cell cell = new Cell(walls);
		assertTrue(cell.hasWall(wall));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidCombinations(){
		return new Object[]{
			new Object[]{EnumSet.noneOf(Direction.class),Direction.NORTH},
			new Object[]{EnumSet.noneOf(Direction.class),Direction.EAST},
			new Object[]{EnumSet.noneOf(Direction.class),Direction.SOUTH},
			new Object[]{EnumSet.noneOf(Direction.class),Direction.WEST},
			new Object[]{EnumSet.of(Direction.NORTH,Direction.EAST,Direction.SOUTH),Direction.WEST},
			new Object[]{EnumSet.of(Direction.NORTH,Direction.EAST,Direction.WEST),Direction.SOUTH},
			new Object[]{EnumSet.of(Direction.NORTH,Direction.WEST,Direction.SOUTH),Direction.EAST},
			new Object[]{EnumSet.of(Direction.WEST,Direction.EAST,Direction.SOUTH),Direction.NORTH}
		};
	}
	
	@Test
	@Parameters(method = "getInvalidCombinations")
	public void cellShouldNotHaveWall(EnumSet<Direction> walls, Direction wall){
		Cell cell = new Cell(walls);
		assertFalse(cell.hasWall(wall));
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldBeImpossibleToCheckIfCellHaveWall(){
		Cell cell = new Cell(EnumSet.allOf(Direction.class));
		cell.hasWall(null);
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void afterClearingCellShouldHaveNoWalls(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		cell.clear();
		assertEquals(EnumSet.noneOf(Direction.class),cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void afterSurroundingCellShouldHaveAllWalls(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		cell.surround();
		assertEquals(EnumSet.allOf(Direction.class),cell.getWalls());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getAllWallsAndDirectionsCombinations(){
		Object combinations[] = new Object[16*4];
		for(int j=0;j<Direction.values().length;++j){
			for(int i=0;i<16;++i){
				combinations[j*16+i]=new Object[]{Direction.getFromIntValue(i),Direction.values()[j]};
			}	
		}
		return combinations;
	}
	
	@Test
	@Parameters(method = "getAllWallsAndDirectionsCombinations")
	public void afterAddingAWallCellShouldHaveWall(EnumSet<Direction> walls, Direction wall){
		Cell cell = new Cell(walls);
		cell.addWall(wall);
		assertTrue(cell.hasWall(wall));
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotBeAbleToAddWall(){
		Cell cell = new Cell();
		cell.addWall(null);
	}
	
	@Test
	@Parameters(method = "getAllWallsAndDirectionsCombinations")
	public void afterRemovingAWallCellShouldNotHaveWall(EnumSet<Direction> walls, Direction wall){
		Cell cell = new Cell(walls);
		cell.removeWall(wall);
		assertFalse(cell.hasWall(wall));
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotBeAbleToRemoveWall(){
		Cell cell = new Cell();
		cell.removeWall(null);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getAllWallsAndPossibleDirections(){
		Object wallSets[] = new Object[16];
		for(int i=0;i<16;++i){
			wallSets[i]=new Object[]{Direction.getFromIntValue(i),Direction.getFromIntValue(15-i)};
		}
		return wallSets;
	}
	
	@Test
	@Parameters(method = "getAllWallsAndPossibleDirections")
	public void shouldReturnDirectionWhereAreNoWalls(EnumSet<Direction> walls, EnumSet<Direction> possibleDirections){
		Cell cell = new Cell(walls);
		assertEquals(possibleDirections,cell.getPossibleDirections());
	}
	
}
