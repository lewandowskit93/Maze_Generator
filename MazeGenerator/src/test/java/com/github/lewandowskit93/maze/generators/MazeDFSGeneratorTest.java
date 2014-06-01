package com.github.lewandowskit93.maze.generators;

import static org.junit.Assert.*;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

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
		assertNull(generator.getMaze());
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
	public void generatingMazeTwoTimesShouldGiveTwoNewMazes(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		assertNull(generator.getMaze());
		Maze generated = generator.generateMaze();
		Maze generated2 = generator.generateMaze();
		assertNotSame(generated,generated2);
	}
}
