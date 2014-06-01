package com.github.lewandowskit93.maze.core;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class MazeConstructorsTest {
	
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
	
	@Test(expected = InvalidMazeWidthException.class)
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
	
	@Test(expected = InvalidMazeHeightException.class)
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
	
	
	@Test(expected = InvalidMazeSizeException.class)
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
		assertEquals("Cell array height doesnt equal to given.",height,cells.length);
		for(int i=0;i<height;++i){
			assertEquals("Cell array width doesnt equal to given at height "+i, width, cells[i].length);
		}
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void mazeShouldHaveAllCellsSurrounded(int width, int height){
		Maze maze = new Maze(width,height);
		Cell[][] cells = maze.getCells();
		for(int i=0;i<height;++i){
			for(int j=0;j<width;++j){
				assertTrue("Cell at (x,y) : ("+j+","+i+") is not surrounded.",cells[i][j].isSurrounded());
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizeAndCells(){
		return new Object[]{
				new Object[]{1,1,new Cell[][]{
						{new Cell()}
					}
				},
				new Object[]{2,2,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{3,3,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,2,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,1,new Cell[][]{
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{1,3,new Cell[][]{
						{new Cell()},
						{new Cell()},
						{new Cell()}
					}
				},
				new Object[]{2,3,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				}
		};
	}
	
	@Test
	@Parameters(method = "getValidSizeAndCells")
	public void shouldBeAbleToCreateMazeWithValidSizeAndCells(int width,int height,Cell[][] cells){
		Maze maze = new Maze(width,height,cells);
		assertEquals(width,maze.getWidth());
		assertEquals(height,maze.getHeight());
		assertArrayEquals(cells,maze.getCells());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidWidthAndValidCells(){
		return new Object[]{
				new Object[]{0,1,new Cell[][]{
						{new Cell()}
					}
				},
				new Object[]{-1,2,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{-3,3,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				}
		};
	}
	
	@Test(expected = InvalidMazeWidthException.class)
	@Parameters(method = "getInvalidWidthAndValidCells")
	public void shouldBeUnableToCreateMazeWithInvalidWidthAndValidCells(int width,int height,Cell[][] cells){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height,cells);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidHeightAndValidCells(){
		return new Object[]{
				new Object[]{1,0,new Cell[][]{
						{new Cell()}
					}
				},
				new Object[]{2,-1,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{3,0,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				}
		};
	}
	
	@Test(expected = InvalidMazeHeightException.class)
	@Parameters(method = "getInvalidHeightAndValidCells")
	public void shouldBeUnableToCreateMazeWithInvalidHeightAndValidCells(int width,int height,Cell[][] cells){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height,cells);
	}
	
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizeAndInvalidCellsWidth(){
		return new Object[]{
				new Object[]{1,1,new Cell[][]{
						{}
					}
				},
				new Object[]{2,2,new Cell[][]{
						{new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{3,3,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,2,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,1,new Cell[][]{
						{}
					}
				},
				new Object[]{1,3,new Cell[][]{
						{new Cell()},
						{new Cell()},
						{}
					}
				},
				new Object[]{2,3,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()},
						{new Cell()}
					}
				}
		};
	}
	
	@Test(expected = InvalidCellsArrayWidthException.class)
	@Parameters(method = "getValidSizeAndInvalidCellsWidth")
	public void shouldBeUnableToCreateMazeWithValidSizeAndInvalidCellsWidth(int width, int height, Cell[][] cells){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height,cells);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizeAndInvalidCellsHeight(){
		return new Object[]{
				new Object[]{1,1,new Cell[][]{
					}
				},
				new Object[]{2,2,new Cell[][]{
						{new Cell(), new Cell()}
					}
				},
				new Object[]{3,3,new Cell[][]{
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,2,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,1,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{1,3,new Cell[][]{
						{new Cell()},
						{new Cell()}
					}
				},
				new Object[]{2,3,new Cell[][]{
						
					}
				}
		};
	}
	
	@Test(expected = InvalidCellsArrayHeightException.class)
	@Parameters(method = "getValidSizeAndInvalidCellsHeight")
	public void shouldBeUnableToCreateMazeWithValidSizeAndInvalidCellsHeight(int width, int height, Cell[][] cells){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height,cells);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizeAndInvalidCellsSize(){
		return new Object[]{
				new Object[]{1,1,new Cell[][]{
						
					}
				},
				new Object[]{2,2,new Cell[][]{
						{new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{3,3,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,2,new Cell[][]{
						{new Cell()}
					}
				},
				new Object[]{3,1,new Cell[][]{
						{new Cell(), new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{1,3,new Cell[][]{
						{new Cell()},
						{new Cell()},
						{new Cell()},
						{new Cell()}
					}
				},
				new Object[]{2,3,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()},
						{new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				}
		};
	}
	
	@Test(expected = InvalidCellsArraySizeException.class)
	@Parameters(method = "getValidSizeAndInvalidCellsSize")
	public void shouldBeUnableToCreateMazeWithValidSizeAndInvalidCellsSize(int width, int height, Cell[][] cells){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height,cells);
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldNotBeAbleToCreateMaze(){
		@SuppressWarnings("unused")
		Maze maze = new Maze(10,10,null);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidSizeOrInvalidCellsSize(){
		return new Object[]{
				new Object[]{0,0,new Cell[][]{
						
					}
				},
				new Object[]{0,0,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{-3,2,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				},
				new Object[]{3,-2,new Cell[][]{
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()},
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,2,new Cell[][]{
						{new Cell(), new Cell(), new Cell()}
					}
				},
				new Object[]{3,1,new Cell[][]{
						{new Cell(), new Cell()}
					}
				},
				new Object[]{1,2,new Cell[][]{
						{new Cell()},
						{new Cell()},
						{new Cell()}
					}
				},
				new Object[]{1,3,new Cell[][]{
						{new Cell(), new Cell()},
						{new Cell(), new Cell()},
						{new Cell(), new Cell()}
					}
				}
		};
	}
	
	@Test(expected = MazeException.class)
	@Parameters(method = "getInvalidSizeOrInvalidCellsSize")
	public void shouldBeUnableToCreateMazeWithInvalidSizeOrInvalidCellsSize(int width, int height, Cell[][] cells){
		@SuppressWarnings("unused")
		Maze maze = new Maze(width,height,cells);
	}
	
	@Test
	public void shouldHaveDefaultStartCoordinates(){
		Maze maze = new Maze(2,3);
		assertEquals(new Coordinates2D(0,0),maze.getStartCoordinates());
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void shouldHaveDefaultStartCoordinates2(int width, int height){
		Maze maze = new Maze(width,height);
		assertEquals(new Coordinates2D(0,0),maze.getStartCoordinates());
	}
	
	@Test
	@Parameters(method = "getValidSizeAndCells")
	public void shouldHaveDefaultStartCoordinates2(int width, int height, Cell[][] cells){
		Maze maze = new Maze(width,height,cells);
		assertEquals(new Coordinates2D(0,0),maze.getStartCoordinates());
	}
	
	@Test
	public void shouldHaveDefaultFinishCoordinates(){
		Maze maze = new Maze(2,3);
		assertEquals(new Coordinates2D(1,2),maze.getFinishCoordinates());
	}
	
	@Test
	@Parameters(method = "getValidMazeSizes")
	public void shouldHaveDefaultFinishCoordinates2(int width, int height){
		Maze maze = new Maze(width,height);
		assertEquals(new Coordinates2D(width-1,height-1),maze.getFinishCoordinates());
	}
	
	@Test
	@Parameters(method = "getValidSizeAndCells")
	public void shouldHaveDefaultFinishCoordinates2(int width, int height, Cell[][] cells){
		Maze maze = new Maze(width,height,cells);
		assertEquals(new Coordinates2D(width-1,height-1),maze.getFinishCoordinates());
	}
}
