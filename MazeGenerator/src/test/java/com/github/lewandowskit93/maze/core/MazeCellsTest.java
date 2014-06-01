package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;

import java.util.EnumSet;

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
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToCheckIfCellHasNorthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.NORTH);
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
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToCheckIfCellHasSouthNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.SOUTH);
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
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToCheckIfCellHasEastNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.EAST);
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
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToCheckIfCellHasWestNeighbour(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.hasNeighbour(x, y, Direction.WEST);
	}
	
	
	
	@Test
	@Parameters(method = "getCellsWithNorthNeighbour")
	public void shouldBeAbleToGetNorthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.NORTH);
		assertEquals(x,neighbour1.getX());
		assertEquals(y-1,neighbour1.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutNorthNeighbour")
	public void shouldBeUnableToGetNorthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.NORTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetNorthNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.NORTH);
	}
	
	
	@Test
	@Parameters(method = "getCellsWithEastNeighbour")
	public void shouldBeAbleToGetEastNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.EAST);
		assertEquals(x+1,neighbour1.getX());
		assertEquals(y,neighbour1.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutEastNeighbour")
	public void shouldBeUnableToGetEastNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.EAST);
	}
	
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetEastNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.EAST);
	}
	
	
	@Test
	@Parameters(method = "getCellsWithSouthNeighbour")
	public void shouldBeAbleToGetSouthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.SOUTH);
		assertEquals(x,neighbour1.getX());
		assertEquals(y+1,neighbour1.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutSouthNeighbour")
	public void shouldBeUnableToGetSouthNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.SOUTH);
	}
	
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetSouthNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.SOUTH);
	}
	
	@Test
	@Parameters(method = "getCellsWithWestNeighbour")
	public void shouldBeAbleToGetWestNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		Coordinates2D neighbour1 = maze.getNeighbourCoordinates(x,y,Direction.WEST);
		assertEquals(x-1,neighbour1.getX());
		assertEquals(y,neighbour1.getY());
	}
	
	@Test(expected = NoNeighbourException.class)
	@Parameters(method = "getCellsWithoutWestNeighbour")
	public void shouldBeUnableToGetWestNeighbourCoordinates(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.WEST);
	}
	
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetWestNeighbourCoordinates2(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		@SuppressWarnings("unused")
		Coordinates2D neighbour = maze.getNeighbourCoordinates(x,y,Direction.WEST);
	}
	
	private static final Cell[][] getCellMocks(){
		return new Cell[][]{
				{mock(Cell.class),mock(Cell.class),mock(Cell.class)},
				{mock(Cell.class),mock(Cell.class),mock(Cell.class)},
				{mock(Cell.class),mock(Cell.class),mock(Cell.class)}
		};
	}
	
	@Test
	public void shouldRemoveNorthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.NORTH);
		maze.removeWall(1,1,Direction.NORTH);
		verify(cellMocks[1][1]).removeWall(Direction.NORTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				}
			}
			
		}
	}
	
	@Test
	public void shouldRemoveNorthWallOfCellAndSouthWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.NORTH);
		maze.removeWall(1,1,Direction.NORTH);
		verify(cellMocks[1][1]).removeWall(Direction.NORTH);
		verify(cellMocks[0][1]).removeWall(Direction.SOUTH);
		verify(maze).getNeighbourCoordinates(1,1, Direction.NORTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				if(i!=0 || j!=1)verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				}
			}
			
		}
	}
	
	@Test
	public void shouldRemoveSouthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.SOUTH);
		maze.removeWall(1,1,Direction.SOUTH);
		verify(cellMocks[1][1]).removeWall(Direction.SOUTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				}
			}
			
		}
	}
	
	
	@Test
	public void shouldRemoveSouthWallOfCellAndNorthWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.SOUTH);
		maze.removeWall(1,1,Direction.SOUTH);
		verify(cellMocks[1][1]).removeWall(Direction.SOUTH);
		verify(cellMocks[2][1]).removeWall(Direction.NORTH);
		verify(maze).getNeighbourCoordinates(1,1, Direction.SOUTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				if(i!=2 || j!=1)verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				}
			}
			
		}
	}
	
	@Test
	public void shouldRemoveEastWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.EAST);
		maze.removeWall(1,1,Direction.EAST);
		verify(cellMocks[1][1]).removeWall(Direction.EAST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				}
			}
			
		}
	}
	
	
	@Test
	public void shouldRemoveEastWallOfCellAndWestWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.EAST);
		maze.removeWall(1,1,Direction.EAST);
		verify(cellMocks[1][1]).removeWall(Direction.EAST);
		verify(cellMocks[1][2]).removeWall(Direction.WEST);
		verify(maze).getNeighbourCoordinates(1,1, Direction.EAST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				if(i!=1 || j!=2)verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				}
			}
			
		}
	}
	
	@Test
	public void shouldRemoveWestWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.WEST);
		maze.removeWall(1,1,Direction.WEST);
		verify(cellMocks[1][1]).removeWall(Direction.WEST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				}
			}
			
		}
		
	}
	
	
	@Test
	public void shouldRemoveWestWallOfCellAndEastWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.WEST);
		maze.removeWall(1,1,Direction.WEST);
		verify(cellMocks[1][1]).removeWall(Direction.WEST);
		verify(cellMocks[1][0]).removeWall(Direction.EAST);
		verify(maze).getNeighbourCoordinates(1,1, Direction.WEST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).removeWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).removeWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).removeWall(Direction.WEST);
				}
				if(i!=1 || j!=0){
					verify(cellMocks[i][j],never()).removeWall(Direction.EAST);
				}
			}
		}
	}
	
	@Test
	public void shouldAddNorthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.NORTH);
		maze.addWall(1,1,Direction.NORTH);
		verify(cellMocks[1][1]).addWall(Direction.NORTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				}
			}
			
		}
	}
	
	@Test
	public void shouldAddNorthWallOfCellAndSouthWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.NORTH);
		maze.addWall(1,1,Direction.NORTH);
		verify(cellMocks[1][1]).addWall(Direction.NORTH);
		verify(cellMocks[0][1]).addWall(Direction.SOUTH);
		verify(maze).getNeighbourCoordinates(1,1, Direction.NORTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				if(i!=0 || j!=1)verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				}
			}
			
		}
	}
	
	@Test
	public void shouldAddSouthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.SOUTH);
		maze.addWall(1,1,Direction.SOUTH);
		verify(cellMocks[1][1]).addWall(Direction.SOUTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				}
			}
			
		}
	}
	
	
	@Test
	public void shouldAddSouthWallOfCellAndNorthWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.SOUTH);
		maze.addWall(1,1,Direction.SOUTH);
		verify(cellMocks[1][1]).addWall(Direction.SOUTH);
		verify(cellMocks[2][1]).addWall(Direction.NORTH);
		verify(maze).getNeighbourCoordinates(1,1, Direction.SOUTH);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				if(i!=2 || j!=1)verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				}
			}
			
		}
	}
	
	@Test
	public void shouldAddEastWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.EAST);
		maze.addWall(1,1,Direction.EAST);
		verify(cellMocks[1][1]).addWall(Direction.EAST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				}
			}
			
		}
	}
	
	
	@Test
	public void shouldAddEastWallOfCellAndWestWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.EAST);
		maze.addWall(1,1,Direction.EAST);
		verify(cellMocks[1][1]).addWall(Direction.EAST);
		verify(cellMocks[1][2]).addWall(Direction.WEST);
		verify(maze).getNeighbourCoordinates(1,1, Direction.EAST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				if(i!=1 || j!=2)verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				}
			}
			
		}
	}
	
	@Test
	public void shouldAddWestWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(false).when(maze).hasNeighbour(1, 1, Direction.WEST);
		maze.addWall(1,1,Direction.WEST);
		verify(cellMocks[1][1]).addWall(Direction.WEST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				}
			}
			
		}
		
	}
	
	
	@Test
	public void shouldAddWestWallOfCellAndEastWallOfNeighbour(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doReturn(true).when(maze).hasNeighbour(1, 1, Direction.WEST);
		maze.addWall(1,1,Direction.WEST);
		verify(cellMocks[1][1]).addWall(Direction.WEST);
		verify(cellMocks[1][0]).addWall(Direction.EAST);
		verify(maze).getNeighbourCoordinates(1,1, Direction.WEST);
		for(int i=0;i<3;++i){
			for(int j=0;j<3;++j){
				verify(cellMocks[i][j],never()).addWall(Direction.NORTH);
				verify(cellMocks[i][j],never()).addWall(Direction.SOUTH);
				if(i!=j){
					verify(cellMocks[i][j],never()).addWall(Direction.WEST);
				}
				if(i!=1 || j!=0){
					verify(cellMocks[i][j],never()).addWall(Direction.EAST);
				}
			}
		}
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToRemoveNorthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.removeWall(1,1,Direction.NORTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToRemoveEastWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.removeWall(1,1,Direction.EAST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToRemoveSouthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.removeWall(1,1,Direction.SOUTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToRemoveWestWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.removeWall(1,1,Direction.WEST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToAddNorthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.addWall(1,1,Direction.NORTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToAddEastWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.addWall(1,1,Direction.EAST);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToAddSouthWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.addWall(1,1,Direction.SOUTH);
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	public void shouldBeUnableToAddWestWall(){
		Cell[][] cellMocks = getCellMocks();
		Maze maze = spy(new Maze(3,3,cellMocks));
		doThrow(new InvalidCellCoordinatesException()).when(maze).getCell(1, 1);
		maze.addWall(1,1,Direction.WEST);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsWithValidCoordinatesAndNeighbours(){
		Cell[][] cells = new Cell[][]{
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,3, cells, 0, 0, EnumSet.of(Direction.SOUTH,Direction.EAST)},
				new Object[]{3,3, cells, 1, 0, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.WEST)},
				new Object[]{3,3, cells, 2, 0, EnumSet.of(Direction.SOUTH,Direction.WEST)},
				new Object[]{3,3, cells, 0, 1, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.NORTH)},
				new Object[]{3,3, cells, 1, 1, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 2, 1, EnumSet.of(Direction.SOUTH, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 0, 2, EnumSet.of(Direction.EAST, Direction.NORTH)},
				new Object[]{3,3, cells, 1, 2, EnumSet.of(Direction.EAST, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 2, 2, EnumSet.of(Direction.WEST, Direction.NORTH)}
		};
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsWithValidCoordinatesAndNeighbours")
	public void shouldReturnNeighbours(int width,int height,Cell[][] cells, int x, int y, EnumSet<Direction> neighbours){
		Maze maze = new Maze(width,height,cells);
		assertEquals(neighbours, maze.getNeighbours(x,y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetNeighbours(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.getNeighbours(x, y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getAllDirections(){
		return Direction.values();
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void shouldBeConnected(Direction direction){
		Cell[][] cells = getCellMocks();
		Maze maze = new Maze(3,3,cells);
		when(cells[1][1].hasWall(direction)).thenReturn(false);
		assertTrue(maze.isConnected(1,1,direction));
	}
	
	@Test
	@Parameters(method = "getAllDirections")
	public void shouldNotBeConnected(Direction direction){
		Cell[][] cells = getCellMocks();
		Maze maze = new Maze(3,3,cells);
		when(cells[1][1].hasWall(direction)).thenReturn(true);
		assertFalse(maze.isConnected(1,1,direction));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToCheckIfCellIsConnected(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.isConnected(x,y,Direction.NORTH);
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsWithValidCoordinates")
	public void shouldHaveNoConnectedNeighbours(int width,int height,Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertEquals(EnumSet.noneOf(Direction.class),maze.getConnectedNeighbours(x,y));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsWithValidCoordinatesAndConnectedNeighbours(){
		Cell[][] cells = new Cell[][]{
			{new Cell(EnumSet.noneOf(Direction.class)),new Cell(EnumSet.noneOf(Direction.class)), new Cell(EnumSet.noneOf(Direction.class))},
			{new Cell(EnumSet.noneOf(Direction.class)),new Cell(EnumSet.noneOf(Direction.class)), new Cell(EnumSet.noneOf(Direction.class))},
			{new Cell(EnumSet.noneOf(Direction.class)),new Cell(EnumSet.noneOf(Direction.class)), new Cell(EnumSet.noneOf(Direction.class))}
		};
		return new Object[]{
				new Object[]{3,3, cells, 0, 0, EnumSet.of(Direction.SOUTH,Direction.EAST)},
				new Object[]{3,3, cells, 1, 0, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.WEST)},
				new Object[]{3,3, cells, 2, 0, EnumSet.of(Direction.SOUTH,Direction.WEST)},
				new Object[]{3,3, cells, 0, 1, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.NORTH)},
				new Object[]{3,3, cells, 1, 1, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 2, 1, EnumSet.of(Direction.SOUTH, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 0, 2, EnumSet.of(Direction.EAST, Direction.NORTH)},
				new Object[]{3,3, cells, 1, 2, EnumSet.of(Direction.EAST, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 2, 2, EnumSet.of(Direction.WEST, Direction.NORTH)}
		};
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsWithValidCoordinatesAndConnectedNeighbours")
	public void shouldHaveConnectedNeighbours(int width,int height,Cell[][] cells, int x, int y, EnumSet<Direction> neighbours){
		Maze maze = new Maze(width, height, cells);
		assertEquals(neighbours,maze.getConnectedNeighbours(x, y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetConnectedNeighbours(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.getConnectedNeighbours(x, y);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsWithValidCoordinatesWithAllConnectedNeighbours(){
		Cell[][] cells = new Cell[][]{
			{new Cell(EnumSet.noneOf(Direction.class)),new Cell(EnumSet.noneOf(Direction.class)), new Cell(EnumSet.noneOf(Direction.class))},
			{new Cell(EnumSet.noneOf(Direction.class)),new Cell(EnumSet.noneOf(Direction.class)), new Cell(EnumSet.noneOf(Direction.class))},
			{new Cell(EnumSet.noneOf(Direction.class)),new Cell(EnumSet.noneOf(Direction.class)), new Cell(EnumSet.noneOf(Direction.class))}
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
	
	@SuppressWarnings("unused")
	private static final Object[] getSizeAndCellsWithValidCoordinatesAndDisconnectedNeighbours(){
		Cell[][] cells = new Cell[][]{
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()},
			{new Cell(),new Cell(), new Cell()}
		};
		return new Object[]{
				new Object[]{3,3, cells, 0, 0, EnumSet.of(Direction.SOUTH,Direction.EAST)},
				new Object[]{3,3, cells, 1, 0, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.WEST)},
				new Object[]{3,3, cells, 2, 0, EnumSet.of(Direction.SOUTH,Direction.WEST)},
				new Object[]{3,3, cells, 0, 1, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.NORTH)},
				new Object[]{3,3, cells, 1, 1, EnumSet.of(Direction.SOUTH,Direction.EAST, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 2, 1, EnumSet.of(Direction.SOUTH, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 0, 2, EnumSet.of(Direction.EAST, Direction.NORTH)},
				new Object[]{3,3, cells, 1, 2, EnumSet.of(Direction.EAST, Direction.WEST, Direction.NORTH)},
				new Object[]{3,3, cells, 2, 2, EnumSet.of(Direction.WEST, Direction.NORTH)}
		};
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsWithValidCoordinatesWithAllConnectedNeighbours")
	public void shouldHaveNoDisconnectedNeighbours(int width,int height,Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		assertEquals(EnumSet.noneOf(Direction.class),maze.getDisconnectedNeighbours(x,y));
	}
	
	@Test
	@Parameters(method = "getSizeAndCellsWithValidCoordinatesAndDisconnectedNeighbours")
	public void shouldHaveDisconnectedNeighbours(int width, int height,Cell[][] cells, int x, int y, EnumSet<Direction> disconnectedNeighbours){
		Maze maze = new Maze(width,height,cells);
		assertEquals(disconnectedNeighbours,maze.getDisconnectedNeighbours(x, y));
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getSizeAndCellsWithInvalidCoordinates")
	public void shouldBeUnableToGetDisconnectedNeighbours(int width, int height, Cell[][] cells, int x, int y){
		Maze maze = new Maze(width,height,cells);
		maze.getDisconnectedNeighbours(x, y);
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void createdMazesShouldBeEqualButShouldNotBeTheSame(int width, int height){
		Maze maze1 = new Maze(width,height);
		Maze maze2 = new Maze(width,height);
		assertEquals(maze1,maze2);
		assertEquals(maze2,maze1);
		assertNotSame(maze1,maze2);
	}
	
	@Test
	public void createdMazesShouldBeEqualButShouldNotBeTheSame2(){
		Maze maze1 = new Maze(3,3);
		maze1.clearCell(1, 1);
		Maze maze2 = new Maze(3,3);
		maze2.clearCell(1, 1);
		assertEquals(maze1,maze2);
		assertEquals(maze2,maze1);
		assertNotSame(maze1,maze2);
	}
	
	@Test
	public void createdMazesShouldNotBeEqualButShouldNotBeTheSame(){
		Maze maze1 = new Maze(3,3);
		maze1.clearCell(1, 1);
		Maze maze2 = new Maze(3,3);
		assertNotEquals(maze1,maze2);
		assertNotEquals(maze2,maze1);
		assertNotSame(maze1,maze2);
	}
	
	@Test
	public void shouldNotBeEqual(){
		Maze maze = new Maze(2,3);
		Object other=new Object();
		assertNotEquals(maze, other);
	}
	
	@Test
	public void shouldNotBeEqual2(){
		Maze maze = new Maze(2,3);
		Maze maze2 = new Maze(3,2);
		assertNotEquals(maze, maze2);
		assertNotEquals(maze2, maze);
	}
	
	@Test
	public void shouldNotBeEqual3(){
		Maze maze = new Maze(2,3);
		Maze maze2 = new Maze(3,2);
		assertNotEquals(maze, null);
		assertNotEquals(maze2, null);
	}
}
