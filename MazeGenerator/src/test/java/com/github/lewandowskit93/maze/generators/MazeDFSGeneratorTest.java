package com.github.lewandowskit93.maze.generators;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Set;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.lewandowskit93.maze.core.Cell;
import com.github.lewandowskit93.maze.core.InvalidCellCoordinatesException;
import com.github.lewandowskit93.maze.core.InvalidMazeSizeException;
import com.github.lewandowskit93.maze.core.Maze;

@RunWith(JUnitParamsRunner.class)
public class MazeDFSGeneratorTest {
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizes(){
		return new Object[]{
			new Object[]{1,1},
			new Object[]{1,3},
			new Object[]{3,1},
			new Object[]{16,16}
		};
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void shouldBeAbleToCreateGeneratorOfSpecifiedSize(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width,height);
		assertEquals(width,generator.getWidth());
		assertEquals(height,generator.getHeight());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getInvalidSizes(){
		return new Object[]{
			new Object[]{-1,1},
			new Object[]{1,-3},
			new Object[]{0,1},
			new Object[]{16,0},
			new Object[]{0,0},
			new Object[]{-16,-16}
		};
	}

	@Test(expected = InvalidMazeSizeException.class)
	@Parameters(method = "getInvalidSizes")
	public void shouldBeUnableToCreateGeneratorOfSpecifiedSize(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width,height);
		assertEquals(width,generator.getWidth());
		assertEquals(height,generator.getHeight());
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void shouldBeAbleToGenerateMazeOfSpecifiedSize(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		Maze generated = generator.generateMaze();
		assertEquals(width,generated.getWidth());
		assertEquals(height,generated.getHeight());
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void shouldReturnPreviouslyGeneratedMaze(int width,int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		Maze generated = generator.generateMaze();
		assertEquals(width,generated.getWidth());
		assertEquals(height,generated.getHeight());
		assertSame(generated,generator.getMaze());
		generated = generator.generateMaze();
		assertEquals(width,generated.getWidth());
		assertEquals(height,generated.getHeight());
		assertSame(generated,generator.getMaze());
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void generatingMazeTwoTimesWithoutResettingShouldGiveTheSameMazes(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		Maze generated = generator.generateMaze();
		Maze generated2 = generator.generateMaze();
		assertSame(generated,generated2);
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void generatingMazeTwoTimesWithResettingShouldGiveTwoNewMazes(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width,height);
		Maze generated = generator.generateMaze();
		generator.reset();
		Maze generated2 = generator.generateMaze();
		assertNotSame(generated,generated2);
	}
	
	@Test
	public void cellsShouldBeUnvisited(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3,4);
		for(int y=0;y<4;++y){
			for(int x=0;x<3;++x){
				assertFalse(generator.wasCellVisited(x,y));
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizesWithInvalidCoordinates(){
		return new Object[]{
			new Object[]{2,3,0,3},
			new Object[]{3,3,3,0},
			new Object[]{7,6,7,3},
			new Object[]{7,6,3,6},
			new Object[]{6,6,-1,4},
			new Object[]{5,5,3,-1},
			new Object[]{4,4,-1,-1},
			new Object[]{8,8,8,8},
			new Object[]{8,8,10,10}
		};
	}
	
	@Test
	public void resettingGeneratorShouldCreateEmptyMaze(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3, 4);
		Maze maze = generator.generateMaze();
		generator.reset();
		assertNotSame(maze,generator.getMaze());
		assertEquals(3,generator.getMaze().getWidth());
		assertEquals(4,generator.getMaze().getHeight());
	}
	
	@Test
	public void afterCreatingGeneratorShouldHaveEmptyMaze(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3,4);
		assertNotNull(generator.getMaze());
		assertEquals(3,generator.getMaze().getWidth());
		assertEquals(4,generator.getMaze().getHeight());
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getValidSizesWithInvalidCoordinates")
	public void shouldNotBeAbleToCheckIfCellWasVisited(int width, int height, int x, int y){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		generator.wasCellVisited(x, y);
	}
	
	@Test
	public void afterVisitingCellItShouldBeVisited(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3,4);
		for(int y=0;y<4;++y){
			for(int x=0;x<3;++x){
				assertFalse(generator.wasCellVisited(x,y));
				generator.visitCell(x,y);
				assertTrue(generator.wasCellVisited(x, y));
			}
		}
	}
	
	@Test(expected = InvalidCellCoordinatesException.class)
	@Parameters(method = "getValidSizesWithInvalidCoordinates")
	public void shouldNotBeAbleToVisitCell(int width, int height, int x, int y){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		generator.visitCell(x, y);
	}
	
	@Test
	public void afterResettingThereShouldBeNewMaze(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3,4);
		Maze maze1 = generator.getMaze();
		generator.reset();
		Maze maze2 = generator.getMaze();
		assertNotSame(maze1, maze2);
	}
	
	@Test
	public void afterResettingCellsShouldNotBeVisited(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3,4);
		for(int y=0;y<4;++y){
			for(int x=0;x<3;++x){
				generator.visitCell(x,y);
			}
		}
		generator.reset();
		for(int y=0;y<4;++y){
			for(int x=0;x<3;++x){
				assertFalse(generator.wasCellVisited(x,y));
			}
		}
	}
	
	@Test
	public void shouldBeAbleToSetMaze(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 7);
		Maze maze = new Maze(4,7);
		generator.setMaze(maze);
		assertSame(maze,generator.getMaze());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getMazesWithWrongSizes(){
		return new Object[]{
			new Object[]{0,3,new Maze(3,7)},
			new Object[]{0,9,new Maze(3,7)},
			new Object[]{0,0,new Maze(3,7)},
			new Object[]{6,3,new Maze(5,4)},
			new Object[]{4,0,new Maze(5,4)},
			new Object[]{4,10,new Maze(5,4)},
			new Object[]{-1,-1,new Maze(5,4)},
			new Object[]{10,10,new Maze(5,4)},
			new Object[]{12,14,new Maze(11,13)}
		};
	}
	
	@Test(expected = InvalidMazeSizeException.class)
	@Parameters(method = "getMazesWithWrongSizes")
	public void shouldNotBeAbleToSetMaze(int width, int height, Maze maze){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 7);
		generator.setMaze(maze);
	}
	
	@Test
	public void settingNewMazeShouldResetVisitedCells(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4,3);
		for(int y = 0; y<3;++y){
			for(int x=0;x<4;++x){
				generator.visitCell(x, y);
			}
		}
		generator.setMaze(new Maze(4,3));
		for(int y=0;y<3;++y){
			for(int x=0;x<4;++x){
				assertFalse(generator.wasCellVisited(x, y));
			}
		}
	}
	
	@Test
	public void shouldReturnNumberOfUnvisitedCells(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 3);
		assertEquals(12,generator.getNumberOfUnvisitedCells());
		generator.visitCell(1, 1);
		assertEquals(11,generator.getNumberOfUnvisitedCells());
		generator.visitCell(2,0);
		assertEquals(10,generator.getNumberOfUnvisitedCells());
		generator.visitCell(0,1);
		assertEquals(9,generator.getNumberOfUnvisitedCells());
		generator.visitCell(0,1);
		assertEquals(9,generator.getNumberOfUnvisitedCells());
		generator.reset();
		assertEquals(12,generator.getNumberOfUnvisitedCells());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizesWithValidCoordinates(){
		return new Object[]{
			new Object[]{1,1,0,0},
			new Object[]{1,3,0,1},
			new Object[]{3,1,2,0},
			new Object[]{16,16,7,7}
		};
	}
	
	@Test
	@Parameters(method = "getValidSizesWithValidCoordinates")
	public void shouldBeAbleToGetUnvisitedCells(int width, int height, int x1, int y1){
		MazeDFSGenerator generator = new MazeDFSGenerator(width,height);
		Set<Cell> unvisitedCells = generator.getUnvisitedCells();
		for(int x=0;x<width;++x){
			for(int y=0;y<height;++y){
				assertTrue(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
			}
		}
		generator.visitCell(x1,y1);
		unvisitedCells = generator.getUnvisitedCells();
		for(int x=0;x<width;++x){
			for(int y=0;y<height;++y){
				if(x==x1 && y==y1){
					assertFalse(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
				}
				else assertTrue(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizesWith2ValidCoordinates(){
		return new Object[]{
			new Object[]{1,3,0,1,0,0},
			new Object[]{3,1,2,0,1,0},
			new Object[]{16,16,7,7,15,15}
		};
	}
	
	@Test
	@Parameters(method = "getValidSizesWith2ValidCoordinates")
	public void shouldBeAbleToGetUnvisitedCells2(int width, int height, int x1, int y1, int x2,int y2){
		MazeDFSGenerator generator = new MazeDFSGenerator(width,height);
		Set<Cell> unvisitedCells = generator.getUnvisitedCells();
		for(int x=0;x<width;++x){
			for(int y=0;y<height;++y){
				assertTrue(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
			}
		}
		generator.visitCell(x1,y1);
		generator.visitCell(x2,y2);
		unvisitedCells = generator.getUnvisitedCells();
		for(int x=0;x<width;++x){
			for(int y=0;y<height;++y){
				if(x==x1 && y==y1){
					assertFalse(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
				}
				else if(x==x2 && y==y2){
					assertFalse(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
				}
				else assertTrue(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
			}
		}
		generator.reset();
		unvisitedCells = generator.getUnvisitedCells();
		for(int x=0;x<width;++x){
			for(int y=0;y<height;++y){
				assertTrue(unvisitedCells.contains(generator.getMaze().getCell(x,y)));
			}
		}
	}
	
	@Test
	public void afterResettingShouldReturnNewUnvisitedCells(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 7);
		Set<Cell> unvisitedCells1 = generator.getUnvisitedCells();
		generator.reset();
		Set<Cell> unvisitedCells2 = generator.getUnvisitedCells();
		assertNotSame(unvisitedCells1,unvisitedCells2);
	}
	
	@Test
	public void shouldReturnTheSameUnvisitedCellsIfNotResetted(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 7);
		Set<Cell> unvisitedCells1 = generator.getUnvisitedCells();
		Set<Cell> unvisitedCells2 = generator.getUnvisitedCells();
		assertSame(unvisitedCells1,unvisitedCells2);
		generator.visitCell(0, 0);
		Set<Cell> unvisitedCells3 = generator.getUnvisitedCells();
		assertSame(unvisitedCells1,unvisitedCells3);
		
	}
	
	@Test
	public void generatingMazeShouldNotResetIt(){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(3,3));
		generator.generateMaze();
		verify(generator,never()).reset();
	}
	
	@Test
	public void resettingMazeShouldSetNewMaze(){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(4,4));
		generator.reset();
		verify(generator).setMaze(any(Maze.class));
	}
	
}
