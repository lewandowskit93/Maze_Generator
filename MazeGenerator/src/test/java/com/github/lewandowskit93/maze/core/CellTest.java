package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;

import java.util.EnumSet;
import java.util.Vector;

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
	public void givenNotAllWallsCellShouldNotBeSurrounded(EnumSet<Direction> walls){
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
	public void givenAllWallsCellShouldBeSurrounded(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertTrue(cell.isSurrounded());
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToCreateCell(){
		@SuppressWarnings("unused")
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
	public void afterCreatingCellWithWallsCellShouldHaveTheSameWalls(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertSame(walls,cell.getWalls());
	}
	
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void afterSettingWallsCellShouldHaveTheSameWalls(EnumSet<Direction> walls){
		Cell cell = new Cell();
		cell.setWalls(walls);
		assertSame(walls,cell.getWalls());
	}

	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToSetCellWalls(){
		Cell cell = new Cell();
		cell.setWalls(null);
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
	public void afterClearingCellShouldHaveNoWalls(){
		Cell cell = new Cell(EnumSet.allOf(Direction.class));
		cell.clear();
		assertEquals(EnumSet.noneOf(Direction.class),cell.getWalls());
	}
	
	@Test
	public void afterSurroundingCellShouldHaveAllWalls(){
		Cell cell = new Cell(EnumSet.noneOf(Direction.class));
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
	public void shouldBeUnableToAddWall(){
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
	public void shouldBeUnableToRemoveWall(){
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
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void whenCellsHaveTheSameWallsCellsShouldBeEqual(EnumSet<Direction> walls){
		Cell cell1 = new Cell(walls);
		Cell cell2 = new Cell(walls);
		assertEquals(cell1,cell2);
		assertEquals(cell2,cell1);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getAllNotEqual(){
		Vector<Object> vect = new Vector<Object>();
		for(int i=0;i<16;++i){
			for(int j=i+1;j<16;++j){
				vect.add(new Object[]{Direction.getFromIntValue(i),Direction.getFromIntValue(j)});
			}
		}
		return vect.toArray();
	}
	
	@Test
	@Parameters(method = "getAllNotEqual")
	public void whenCellsDoesntHaveTheSameWallsCellsShouldNotBeEqual(EnumSet<Direction> walls1, EnumSet<Direction> walls2){
		Cell cell1 = new Cell(walls1);
		Cell cell2 = new Cell(walls2);
		assertNotEquals(cell1,cell2);
		assertNotEquals(cell2,cell1);
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void clonedCellShouldBeEqualToOriginal(EnumSet<Direction> walls){
		Cell original = new Cell(walls);
		Cell cloned = original.clone();
		assertEquals(original,cloned);
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void clonedCellShouldNotBeTheSameAsOriginal(EnumSet<Direction> walls){
		Cell original = new Cell(walls);
		Cell cloned = original.clone();
		assertNotSame(original,cloned);
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void clonedCellWallsShouldNotBeTheSameAsOriginal(EnumSet<Direction> walls){
		Cell original = new Cell(walls);
		Cell cloned = original.clone();
		assertNotSame(original.getWalls(),cloned.getWalls());
	}
}
