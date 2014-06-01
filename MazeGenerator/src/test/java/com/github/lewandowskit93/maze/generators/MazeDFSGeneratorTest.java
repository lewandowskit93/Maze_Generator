package com.github.lewandowskit93.maze.generators;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

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
	public void resetingGeneratorShouldCreateEmptyMaze(){
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
}
