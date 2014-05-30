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
	public void byDefaultCellShouldBeSurrounded(){
		Cell cell = new Cell();
		assertTrue(cell.isSurrounded());
	}
	
	private static final Object[] getUnsurroundedCellWalls(){
		Object wallSets[] = new Object[15];
		for(int i=0;i<15;++i){
			wallSets[i]=Direction.getFromIntValue(i);
		}
		return wallSets;
	}
	
	@Test
	@Parameters(method = "getUnsurroundedCellWalls")
	public void createdCellShouldNotBeSurrounded(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertFalse(cell.isSurrounded());
	}
	
	private static final Object[] getSurroundedCellWalls(){
		return new Object[]{
				Direction.getFromIntValue(15),
				new Object[]{null}
		};
	}
	
	@Test
	@Parameters(method = "getSurroundedCellWalls")
	public void createdCellShouldBeSurrounded(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertTrue(cell.isSurrounded());
	}
	
	private static final Object[] getAllWallsCombinations(){
		Object wallSets[] = new Object[16];
		for(int i=0;i<16;++i){
			wallSets[i]=Direction.getFromIntValue(i);
		}
		return wallSets;
	}
	
	@Test
	@Parameters(method = "getAllWallsCombinations")
	public void shouldReturnWallsTheCellWasCreatedWith(EnumSet<Direction> walls){
		Cell cell = new Cell(walls);
		assertEquals(walls,cell.getWalls());
	}
	
	private static Object[] getAllDirections(){
		return Direction.values();
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void removingWallFromOriginalWallsShouldHaveNoImpactOnCellWalls(Direction wall){
		EnumSet<Direction> walls = EnumSet.allOf(Direction.class);
		Cell cell = new Cell(walls);
		walls.remove(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void addingWallToOriginalWallsShouldHaveNoImpactOnCellWalls(Direction wall){
		EnumSet<Direction> walls = EnumSet.noneOf(Direction.class);
		Cell cell = new Cell(walls);
		walls.add(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void removingWallFromWallsProvidedByCellShouldHaveNoImpactOnCellWalls(Direction wall){
		Cell cell = new Cell(EnumSet.allOf(Direction.class));
		EnumSet<Direction> walls = cell.getWalls();
		walls.remove(wall);
		assertNotEquals(walls, cell.getWalls());
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void addinggWallToWallsProvidedByCellShouldHaveNoImpactOnCellWalls(Direction wall){
		Cell cell = new Cell(EnumSet.noneOf(Direction.class));
		EnumSet<Direction> walls = cell.getWalls();
		walls.add(wall);
		assertNotEquals(walls, cell.getWalls());
	}

}
