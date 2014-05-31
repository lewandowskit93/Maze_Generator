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
}
