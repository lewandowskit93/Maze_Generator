package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class MazeCellsTest {

	@SuppressWarnings("unused")
	private static final Object[] getValidMazeSizes(){
		return new Object[]{
				new Object[]{2,2},
				new Object[]{1,2},
				new Object[]{2,1},
				new Object[]{157,123},
				new Object[]{18,16}
		};
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void askingMazeTwoTimesAboutItsCellsShouldReturnTheSameCells(int width, int height){
		Maze maze = new Maze(width,height);
		Cell[][] cells1 = maze.getCells();
		Cell[][] cells2 = maze.getCells();
		assertSame(cells1,cells2);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsWithValidCoordinates(){
		Cell[][] cells = new Cell[][]{
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,3, cells, 0, 0},
				new Object[]{3,3, cells, 1, 0},
				new Object[]{3,3, cells, 2, 0},
				new Object[]{3,3, cells, 0, 1},
				new Object[]{3,3, cells, 1, 1},
				new Object[]{3,3, cells, 2, 1},
				new Object[]{3,3, cells, 0, 2},
				new Object[]{3,3, cells, 1, 2},
				new Object[]{3,3, cells, 2, 2}
		};
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsWithValidCoordinates")
	public void shouldReturnCell(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Cell original = cells[y][x];
		Cell returned = maze.getCell(x,y);
		assertSame(original,returned);
	}

	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsWithInvalidCoordinates(){
		Cell[][] cells = new Cell[][]{
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,3, cells, -1, 0},
				new Object[]{3,3, cells, 0, -1},
				new Object[]{3,3, cells, -1, -1},
				new Object[]{3,3, cells, -12, -9},
				new Object[]{3,3, cells, 2, 3},
				new Object[]{3,3, cells, 3, 1},
				new Object[]{3,3, cells, 3, 2},
				new Object[]{3,3, cells, 4, 2},
				new Object[]{3,3, cells, 2, 4},
				new Object[]{3,3, cells, 3, 3},
				new Object[]{3,3, cells, 7, 8}
		};
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetCell(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.getCell(x, y);
	}
	
	@Test(expected = MazeException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetCell2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.getCell(x, y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsMocksWithValidCoordinates(){
		Cell[][] cells = new Cell[][]{
			{mock(Cell.class),mock(Cell.class), mock(Cell.class)},
			{mock(Cell.class),mock(Cell.class), mock(Cell.class)},
			{mock(Cell.class),mock(Cell.class), mock(Cell.class)}
		};
		return new Object[]{
				new Object[]{3,3, cells, 0, 0},
				new Object[]{3,3, cells, 1, 0},
				new Object[]{3,3, cells, 2, 0},
				new Object[]{3,3, cells, 0, 1},
				new Object[]{3,3, cells, 1, 1},
				new Object[]{3,3, cells, 2, 1},
				new Object[]{3,3, cells, 0, 2},
				new Object[]{3,3, cells, 1, 2},
				new Object[]{3,3, cells, 2, 2}
		};
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsMocksWithValidCoordinates")
	public void shouldBeAbleToSurroundCell(int width, int height, Cell[][] cellsMocks, int x, int y){
		Maze maze = new Maze(width,height,cellsMocks);
		maze.surroundCell(x,y);
		verify(cellsMocks[y][x]).surround();
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsMocksWithInvalidCoordinates(){
		Cell[][] cells = new Cell[][]{
			{mock(Cell.class),mock(Cell.class), mock(Cell.class)},
			{mock(Cell.class),mock(Cell.class), mock(Cell.class)},
			{mock(Cell.class),mock(Cell.class), mock(Cell.class)}
		};
		return new Object[]{
				new Object[]{3,3, cells, -1, 0},
				new Object[]{3,3, cells, 0, -1},
				new Object[]{3,3, cells, -1, -1},
				new Object[]{3,3, cells, -12, -9},
				new Object[]{3,3, cells, 2, 3},
				new Object[]{3,3, cells, 3, 1},
				new Object[]{3,3, cells, 3, 2},
				new Object[]{3,3, cells, 4, 2},
				new Object[]{3,3, cells, 2, 4},
				new Object[]{3,3, cells, 3, 3},
				new Object[]{3,3, cells, 7, 8}
		};
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsMocksWithInvalidCoordinates")
	public void shouldBeUnableToSurroundCell(int width, int height, Cell[][] cellsMocks, int x, int y){
		Maze maze = new Maze(width,height,cellsMocks);
		maze.surroundCell(x,y);
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsMocksWithValidCoordinates")
	public void shouldBeAbleToClearCell(int width, int height, Cell[][] cellsMocks, int x, int y){
		Maze maze = new Maze(width,height,cellsMocks);
		maze.clearCell(x, y);
		verify(cellsMocks[y][x]).clear();
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsMocksWithInvalidCoordinates")
	public void shouldBeUnableToClearCell(int width, int height, Cell[][] cellsMocks, int x, int y){
		Maze maze = new Maze(width,height,cellsMocks);
		maze.clearCell(x, y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithNorthNeighbour(){
		Cell[][] cells = new Cell[][]{
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()}
			};
		return new Object[]{
					new Object[]{3,4, cells, 0, 1},
					new Object[]{3,4, cells, 1, 1},
					new Object[]{3,4, cells, 2, 1},
					new Object[]{3,4, cells, 0, 2},
					new Object[]{3,4, cells, 1, 2},
					new Object[]{3,4, cells, 2, 2},
					new Object[]{3,4, cells, 0, 3},
					new Object[]{3,4, cells, 1, 3},
					new Object[]{3,4, cells, 2, 3}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithNorthNeighbour")
	public void cellShouldHaveNorthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertTrue(maze.hasNeighbour(x,y,Direction.NORTH));
		assertTrue(maze.hasNorthNeighbour(x,y));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithoutNorthNeighbour(){
		Cell[][] cells = new Cell[][]{
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()}
			};
		return new Object[]{
				new Object[]{3,4, cells, 0, 0},
				new Object[]{3,4, cells, 1, 0},
				new Object[]{3,4, cells, 2, 0}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithoutNorthNeighbour")
	public void cellShouldNotHaveNorthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertFalse(maze.hasNeighbour(x,y,Direction.NORTH));
		assertFalse(maze.hasNorthNeighbour(x, y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasNorthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.NORTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasNorthNeighbour2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNorthNeighbour(x, y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithSouthNeighbour(){
		Cell[][] cells = new Cell[][]{
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,4, cells, 0, 0},
				new Object[]{3,4, cells, 1, 0},
				new Object[]{3,4, cells, 2, 0},
				new Object[]{3,4, cells, 0, 1},
				new Object[]{3,4, cells, 1, 1},
				new Object[]{3,4, cells, 2, 1},
				new Object[]{3,4, cells, 0, 2},
				new Object[]{3,4, cells, 1, 2},
				new Object[]{3,4, cells, 2, 2}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithSouthNeighbour")
	public void cellShouldHaveSouthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertTrue(maze.hasNeighbour(x,y,Direction.SOUTH));
		assertTrue(maze.hasSouthNeighbour(x,y));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithoutSouthNeighbour(){
		Cell[][] cells = new Cell[][]{
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,4, cells, 0, 3},
				new Object[]{3,4, cells, 1, 3},
				new Object[]{3,4, cells, 2, 3}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithoutSouthNeighbour")
	public void cellShouldNotHaveSouthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertFalse(maze.hasNeighbour(x,y,Direction.SOUTH));
		assertFalse(maze.hasSouthNeighbour(x,y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasSouthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.SOUTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasSouthNeighbour2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasSouthNeighbour(x,y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithEastNeighbour(){
		Cell[][] cells = new Cell[][]{
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()}
			};
		return new Object[]{
				new Object[]{3,4, cells, 0, 0},
				new Object[]{3,4, cells, 1, 0},
				new Object[]{3,4, cells, 0, 1},
				new Object[]{3,4, cells, 1, 1},
				new Object[]{3,4, cells, 0, 2},
				new Object[]{3,4, cells, 1, 2},
				new Object[]{3,4, cells, 0, 3},
				new Object[]{3,4, cells, 1, 3}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithEastNeighbour")
	public void cellShouldHaveEasthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertTrue(maze.hasNeighbour(x,y,Direction.EAST));
		assertTrue(maze.hasEastNeighbour(x,y));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithoutEastNeighbour(){
		Cell[][] cells = new Cell[][]{
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()}
			};
		return new Object[]{
				new Object[]{3,4, cells, 2, 0},
				new Object[]{3,4, cells, 2, 1},
				new Object[]{3,4, cells, 2, 2},
				new Object[]{3,4, cells, 2, 3}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithoutEastNeighbour")
	public void cellShouldNotHaveEastNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertFalse(maze.hasNeighbour(x,y,Direction.EAST));
		assertFalse(maze.hasEastNeighbour(x,y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasEastNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.EAST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasEastNeighbour2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasEastNeighbour(x, y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithWestNeighbour(){
		Cell[][] cells = new Cell[][]{
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()},
				{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,4, cells, 1, 0},
				new Object[]{3,4, cells, 2, 0},
				new Object[]{3,4, cells, 1, 1},
				new Object[]{3,4, cells, 2, 1},
				new Object[]{3,4, cells, 1, 2},
				new Object[]{3,4, cells, 2, 2},
				new Object[]{3,4, cells, 1, 3},
				new Object[]{3,4, cells, 2, 3}
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithWestNeighbour")
	public void cellShouldHaveWestNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertTrue(maze.hasNeighbour(x,y,Direction.WEST));
		assertTrue(maze.hasWestNeighbour(x,y));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getCellsWithoutWestNeighbour(){
		Cell[][] cells = new Cell[][]{
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,4, cells, 0, 0},
				new Object[]{3,4, cells, 0, 1},
				new Object[]{3,4, cells, 0, 2},
				new Object[]{3,4, cells, 0, 3},
		};
	}
	
	@Test
	@Parameters(method = "getCellsWithoutWestNeighbour")
	public void cellShouldNotHaveWestNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertFalse(maze.hasNeighbour(x,y,Direction.WEST));
		assertFalse(maze.hasWestNeighbour(x,y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasWestNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.WEST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellHasWestNeighbour2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasWestNeighbour(x,y);
	}
	
	
	@Test
	@Parameters(method = "getCellsWithNorthNeighbour")
	public void shouldBeAbleToGetNorthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.NORTH);
		Coordinates2D neighbour2 = maze.getNorthNeighbourCoordinates(x,y);
		assertEquals(x,neighbour1.getX());
		assertEquals(y-1,neighbour1.getY());
		assertEquals(x,neighbour2.getX());
		assertEquals(y-1,neighbour2.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutNorthNeighbour")
	public void shouldBeUnableToGetNorthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.NORTH);
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutNorthNeighbour")
	public void shouldBeUnableToGetNorthNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNorthNeighbourCoordinates(x,y);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetNorthNeighbourCoordinates3(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.NORTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetNorthNeighbourCoordinates4(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNorthNeighbourCoordinates(x,y);
	}
	
	@Test
	@Parameters(method = "getCellsWithEastNeighbour")
	public void shouldBeAbleToGetEastNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.EAST);
		Coordinates2D neighbour2 = maze.getEastNeighbourCoordinates(x,y);
		assertEquals(x+1,neighbour1.getX());
		assertEquals(y,neighbour1.getY());
		assertEquals(x+1,neighbour2.getX());
		assertEquals(y,neighbour2.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutEastNeighbour")
	public void shouldBeUnableToGetEastNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.EAST);
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutEastNeighbour")
	public void shouldBeUnableToGetEastNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getEastNeighbourCoordinates(x,y);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetEastNeighbourCoordinates3(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.EAST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetEastNeighbourCoordinates4(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getEastNeighbourCoordinates(x,y);
	}
	
	@Test
	@Parameters(method = "getCellsWithSouthNeighbour")
	public void shouldBeAbleToGetSouthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.SOUTH);
		Coordinates2D neighbour2 = maze.getSouthNeighbourCoordinates(x,y);
		assertEquals(x,neighbour1.getX());
		assertEquals(y+1,neighbour1.getY());
		assertEquals(x,neighbour2.getX());
		assertEquals(y+1,neighbour2.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutSouthNeighbour")
	public void shouldBeUnableToGetSouthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.SOUTH);
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutSouthNeighbour")
	public void shouldBeUnableToGetSouthNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getSouthNeighbourCoordinates(x,y);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetSouthNeighbourCoordinates3(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.SOUTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetSouthNeighbourCoordinates4(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getSouthNeighbourCoordinates(x,y);
	}
	
	@Test
	@Parameters(method = "getCellsWithWestNeighbour")
	public void shouldBeAbleToGetWestNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.WEST);
		Coordinates2D neighbour2 = maze.getWestNeighbourCoordinates(x,y);
		assertEquals(x-1,neighbour1.getX());
		assertEquals(y,neighbour1.getY());
		assertEquals(x-1,neighbour2.getX());
		assertEquals(y,neighbour2.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutWestNeighbour")
	public void shouldBeUnableToGetWestNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.WEST);
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutWestNeighbour")
	public void shouldBeUnableToGetWestNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getWestNeighbourCoordinates(x,y);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetWestNeighbourCoordinates3(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.WEST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetWestNeighbourCoordinates4(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getWestNeighbourCoordinates(x,y);
	}
}
