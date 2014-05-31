package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MazeTest {
	
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
	public void shouldBeAbleToCreateMazeWithSpecifiedSize(int width, int height) {
		Maze maze = new Maze(width,height);
		assertEquals(width,maze.getWidth());
		assertEquals(height,maze.getHeight());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesWithInvalidWidth(){
		return new Object[]{
				new Object[]{0,2},
				new Object[]{-4,2},
				new Object[]{-2,1},
				new Object[]{-1,1},
				new Object[]{-157,123},
				new Object[]{-18,16},
				new Object[]{-16,16}
		};
	}
	
	@Test(expected = InvalidMazeWidth.class)
	@Parameters(method = "getSizesWithInvalidWidth")
	public void shouldNotBeAbleToCreateMazeWithSpecifiedWidth(int width, int height){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesWithInvalidHeight(){
		return new Object[]{
				new Object[]{2,0},
				new Object[]{2,-4},
				new Object[]{1,-2},
				new Object[]{1,-1},
				new Object[]{157,-123},
				new Object[]{18,-16},
				new Object[]{16,-18}
		};
	}
	
	@Test(expected = InvalidMazeHeight.class)
	@Parameters(method = "getSizesWithInvalidHeight")
	public void shouldNotBeAbleToCreateMazeWithSpecifiedHeight(int width, int height){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height);
	}

	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidSizes(){
		return new Object[]{
				new Object[]{2,-1},
				new Object[]{-1,2},
				new Object[]{0,1},
				new Object[]{1,0},
				new Object[]{0,0},
				new Object[]{-8,-3}
		};
	}
	
	
	@Test(expected = InvalidMazeSize.class)
	@Parameters(method = "getInvalidSizes")
	public void shouldNotBeAbleToCreateMazeWithSpecifiedSize(int width, int height){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height);
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void createdMazeShouldHaveCellsArrayOfGivenSize(int width, int height){
		Maze maze = new Maze(width,height);
		Cell[][] cells = maze.getCells();
		assertEquals("Cell array width doesnt equal to given.",width,cells.length);
		for(int i=0;i<width;++i){
			assertEquals("Cell array width doesnt equal to given at width "+i, height, cells[i].length);
		}
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void mazeShouldHaveAllCellsSurrounded(int width, int height){
		Maze maze = new Maze(width,height);
		Cell[][] cells = maze.getCells();
		for(int i=0;i<width;++i){
			for(int j=0;j<height;++j){
				assertTrue("Cell at : ("+i+","+j+") is not surrounded.",cells[i][j].isSurrounded());
			}
		}
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void askingMazeTwoTimesAboutItsCellsShouldReturnTheSameCells(int width, int height){
		Maze maze = new Maze(width,height);
		Cell[][] cells1 = maze.getCells();
		Cell[][] cells2 = maze.getCells();
		assertSame(cells1,cells2);
	}
	
}
