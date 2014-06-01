package com.github.lewandowskit93.maze.generators;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.EnumSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;



import com.github.lewandowskit93.maze.core.Cell;
import com.github.lewandowskit93.maze.core.Coordinates2D;
import com.github.lewandowskit93.maze.core.Direction;
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
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
		Maze generated = generator.generateMaze();
		assertEquals(width,generated.getWidth());
		assertEquals(height,generated.getHeight());
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void shouldReturnPreviouslyGeneratedMaze(int width,int height){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
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
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
		Maze generated = generator.generateMaze();
		Maze generated2 = generator.generateMaze();
		assertSame(generated,generated2);
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void generatingMazeTwoTimesWithResettingShouldGiveTwoNewMazes(int width, int height){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
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
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(3, 4));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
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
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
		generator.generateMaze();
		verify(generator,never()).reset();
	}
	
	@Test
	public void resettingMazeShouldSetNewMaze(){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(4,4));
		generator.reset();
		verify(generator).setMaze(any(Maze.class));
	}
	
	@Test
	public void shouldHaveRandomNumberGenerator(){
		MazeDFSGenerator generator = new MazeDFSGenerator(10,10);
		assertNotNull(generator.getRandomNumberGenerator());
	}
	
	@Test
	public void askingTwoTimesAboutNumberGeneratorShouldGiveTheSameGenerator(){
		MazeDFSGenerator generator = new MazeDFSGenerator(10, 10);
		Random r1 = generator.getRandomNumberGenerator();
		Random r2 = generator.getRandomNumberGenerator();
		assertSame(r1,r2);
	}
	
	@Test
	public void shouldBeAbleToSetRandomNumberGenerator(){
		MazeDFSGenerator generator = new MazeDFSGenerator(10, 10);
		Random r = new Random();
		generator.setRandomNumberGenerator(r);
		assertSame(r,generator.getRandomNumberGenerator());
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToSetRandomNumberGenerator(){
		MazeDFSGenerator generator = new MazeDFSGenerator(10, 10);
		generator.setRandomNumberGenerator(null);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesWithTwoCellsCoordinates(){
		return new Object[]{
				new Object[]{10,12,7,3,9,0},
				new Object[]{1,1,0,0,0,0},
				new Object[]{10,12,2,9,9,9},
				new Object[]{3,4,2,3,0,0},
				new Object[]{3,4,2,1,1,1}
		};
	}
	
	@Test
	public void shouldBeAbleToGetRandomCellCoordinates(){
		MazeDFSGenerator generator = new MazeDFSGenerator(10,10);
		assertNotNull(generator.getRandomCellCoordinates());
	}
	
	@Test
	@Parameters(method = "getSizesWithTwoCellsCoordinates")
	public void shouldBeAbleToGetRandomCellCoordinatesWithEqualCoordinates(int width, int height, int x1, int y1, int x2, int y2){
		MazeDFSGenerator generator = new MazeDFSGenerator(width,height);
		Random rng = mock(Random.class);
		when(rng.nextInt(width)).thenReturn(x1).thenReturn(x2);
		when(rng.nextInt(height)).thenReturn(y1).thenReturn(y2);
		generator.setRandomNumberGenerator(rng);
		Coordinates2D coords1 = generator.getRandomCellCoordinates();
		assertEquals(new Coordinates2D(x1,y1),coords1);
		Coordinates2D coords2 = generator.getRandomCellCoordinates();
		assertEquals(new Coordinates2D(x2,y2),coords2);
	}
	
	@Test
	public void askingTwoTimesAboutRandomCellShouldReturnTwoNewCells(){
		MazeDFSGenerator generator = new MazeDFSGenerator(10,10);
		Coordinates2D coords1 = generator.getRandomCellCoordinates();
		Coordinates2D coords2 = generator.getRandomCellCoordinates();
		assertNotSame(coords1,coords2);
	}
	
	@Test
	public void shouldBeAbleToGetUnvisitedNeighbours(){
		Maze maze = spy(new Maze(3,3));
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(3,3));
		generator.setMaze(maze);
		generator.visitCell(1, 0);

		
		EnumSet<Direction> unvisitedNeighbours = generator.getUnvisitedNeighbours(1, 1);
		
		verify(maze).getNeighbours(1, 1);
		verify(generator, times(2)).wasCellVisited(1,0);
		verify(generator).wasCellVisited(1, 2);
		verify(generator).wasCellVisited(0,1);
		verify(generator).wasCellVisited(2,1);
		assertNotNull(unvisitedNeighbours);
		assertEquals(EnumSet.of(Direction.SOUTH,Direction.EAST,Direction.WEST),unvisitedNeighbours);
	}
	
	@Test
	public void shouldHaveNoUnvisitedNeighbours(){
		Maze maze = spy(new Maze(7,7));
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(7,7));
		generator.setMaze(maze);
		generator.visitCell(2, 3);
		generator.visitCell(3, 4);
		generator.visitCell(2, 5);
		generator.visitCell(1, 4);
		
		EnumSet<Direction> unvisitedNeighbours = generator.getUnvisitedNeighbours(2, 4);
		
		verify(maze).getNeighbours(2, 4);
		verify(generator, times(2)).wasCellVisited(2,3);
		verify(generator, times(2)).wasCellVisited(3, 4);
		verify(generator, times(2)).wasCellVisited(2,5);
		verify(generator, times(2)).wasCellVisited(1,4);
		assertNotNull(unvisitedNeighbours);
		assertEquals(EnumSet.noneOf(Direction.class),unvisitedNeighbours);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesWithCoordinatesRandomAndAnswer(){
		return new Object[]{
				new Object[]{4,4,2,1,2,2,0,Direction.NORTH},
				new Object[]{4,4,1,1,1,2,0,Direction.NORTH},
				new Object[]{3,3,1,1,1,2,0,Direction.NORTH},
				new Object[]{3,3,1,1,1,2,1,Direction.EAST},
				new Object[]{3,3,1,1,1,2,2,Direction.WEST},
				new Object[]{8,8,3,4,3,3,0,Direction.EAST},
				new Object[]{8,8,3,4,3,3,1,Direction.SOUTH},
				new Object[]{8,8,3,4,3,3,2,Direction.WEST}
		};
	}
	
	@Test
	@Parameters(method = "getSizesWithCoordinatesRandomAndAnswer")
	public void shouldReturnUnvisitedNeighbour(int width, int height, int x, int y, int vx, int vy, int rand, Direction answer){
		Maze maze = spy(new Maze(width,height));
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Random rng = mock(Random.class);
		generator.setRandomNumberGenerator(rng);
		generator.setMaze(maze);
		generator.visitCell(vx, vy);
		when(rng.nextInt(3)).thenReturn(rand);
		Direction unvisitedNeighbour = generator.getRandomUnvisitedNeighbours(x, y);
		verify(generator,times(1)).getUnvisitedNeighbours(x, y);
		assertEquals(answer,unvisitedNeighbour);
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesWithCoordinatesRandomAndAnswer2(){
		return new Object[]{
				new Object[]{4,4,2,1,2,2,1,1,0,Direction.NORTH},
				new Object[]{4,4,1,1,1,2,2,1,0,Direction.NORTH},
				new Object[]{3,3,1,1,1,2,0,1,0,Direction.NORTH},
				new Object[]{3,3,1,1,1,2,2,1,1,Direction.WEST},
				new Object[]{3,3,1,1,1,2,1,0,0,Direction.EAST},
				new Object[]{3,3,1,1,1,2,1,0,1,Direction.WEST},
				new Object[]{3,3,1,1,1,0,0,1,1,Direction.SOUTH},
				new Object[]{3,3,1,1,1,0,0,1,0,Direction.EAST}
		};
	}
	
	@Test
	@Parameters(method = "getSizesWithCoordinatesRandomAndAnswer2")
	public void shouldReturnUnvisitedNeighbour(int width, int height, int x, int y, int vx1, int vy1,int vx2,int vy2, int rand, Direction answer){
		Maze maze = spy(new Maze(width,height));
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Random rng = mock(Random.class);
		generator.setRandomNumberGenerator(rng);
		generator.setMaze(maze);
		generator.visitCell(vx1, vy1);
		generator.visitCell(vx2, vy2);
		when(rng.nextInt(2)).thenReturn(rand);
		Direction unvisitedNeighbour = generator.getRandomUnvisitedNeighbours(x, y);
		verify(generator,times(1)).getUnvisitedNeighbours(x, y);
		assertEquals(answer,unvisitedNeighbour);
	}

	@Test
	public void shouldBeUnableToGetUnvisitedNeighbour(){
		Maze maze = spy(new Maze(9,9));
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(9,9));
		Random rng = mock(Random.class);
		generator.setRandomNumberGenerator(rng);
		generator.setMaze(maze);
		generator.visitCell(7, 4);
		generator.visitCell(8, 5);
		generator.visitCell(7, 6);
		generator.visitCell(6, 5);
		verify(rng,never()).nextInt(anyInt());
		Direction unvisitedNeighbour = generator.getRandomUnvisitedNeighbours(7,5);
		verify(generator,times(1)).getUnvisitedNeighbours(7, 5);
		assertNull(unvisitedNeighbour);
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void shouldHaveRouteCoordinatesStack(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		assertNotNull(generator.getRouteCoordinatesStack());
	}
	
	@Test
	@Parameters(method = "getValidSizes")
	public void askingTwoTimesAboutStackShouldGiveTheSameStack(int width, int height){
		MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
		assertSame(generator.getRouteCoordinatesStack(),generator.getRouteCoordinatesStack());
	}
	
	@Test
	public void resettingShouldHaveTheSameStack(){
		MazeDFSGenerator generator = new MazeDFSGenerator(7,8);
		Stack<Coordinates2D> stack1 = generator.getRouteCoordinatesStack();
		generator.reset();
		Stack<Coordinates2D> stack2 = generator.getRouteCoordinatesStack();
		assertSame(stack1,stack2);
	}
	
	@Test
	public void stackShouldBeEmpty(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3, 3);
		assertTrue(generator.getRouteCoordinatesStack().isEmpty());
	}
	
	@Test
	public void stackShouldBeEmptyAfterResetting(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3, 3);
		generator.getRouteCoordinatesStack().add(new Coordinates2D(3, 3));
		generator.reset();
		assertTrue(generator.getRouteCoordinatesStack().isEmpty());
	}
	
	@Test
	public void stackShouldBeEmptyAfterSettingNewMaze(){
		MazeDFSGenerator generator = new MazeDFSGenerator(3, 3);
		generator.getRouteCoordinatesStack().add(new Coordinates2D(3, 3));
		generator.setMaze(new Maze(3,3));
		assertTrue(generator.getRouteCoordinatesStack().isEmpty());
	}
	
	@Test
	public void shouldBeAbleToSetRouteCoordinatesStack(){
		MazeDFSGenerator generator = new MazeDFSGenerator(8, 8);
		Stack<Coordinates2D> stack = new Stack<Coordinates2D>();
		generator.setRouteCoordinatesStack(stack);
		assertSame(stack,generator.getRouteCoordinatesStack());
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldBeUnableToSetRouteCoordinatesStack(){
		MazeDFSGenerator generator = new MazeDFSGenerator(2, 2);
		generator.setRouteCoordinatesStack(null);
	}
	
	@Test
	public void shouldPopLastCoordinatesFromStack(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 4);
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		stack.add(new Coordinates2D(3,3));
		generator.setRouteCoordinatesStack(stack);
		generator.nextStep();
		verify(stack).empty();
		verify(stack).pop();
	}
	
	@Test
	public void shouldNotPopLastCoordinatesFromStack(){
		MazeDFSGenerator generator = new MazeDFSGenerator(4, 4);
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		stack.add(new Coordinates2D(3,3));
		generator.setRouteCoordinatesStack(stack);
		doReturn(true).when(stack).empty();
		generator.nextStep();
		verify(stack).empty();
		verify(stack,never()).pop();
	}
	
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizesWithValidCoordinates2(){
		return new Object[]{
			new Object[]{1,3,0,1},
			new Object[]{3,1,2,0},
			new Object[]{16,16,7,7}
		};
	}
	
	@Test
	@Parameters(method ="getValidSizesWithValidCoordinates2")
	public void shouldAskForRandomNeighbourOfCell(int width, int height, int x, int y){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		stack.add(new Coordinates2D(x,y));
		generator.setRouteCoordinatesStack(stack);
		generator.nextStep();
		verify(stack).empty();
		verify(generator).getRandomUnvisitedNeighbours(x, y);
	}
	
	@Test
	@Parameters(method ="getValidSizesWithValidCoordinates2")
	public void shouldNotAskForRandomNeighbourOfCell(int width, int height, int x, int y){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		@SuppressWarnings("unchecked")
		Stack<Coordinates2D> stack = mock(Stack.class);
		when(stack.empty()).thenReturn(true);
		generator.setRouteCoordinatesStack(stack);
		generator.nextStep();
		verify(stack).empty();
		verify(generator, never()).getRandomUnvisitedNeighbours(anyInt(), anyInt());
	}
	
	@Test
	@Parameters(method ="getValidSizesWithValidCoordinates2")
	public void nextStepShouldBeCalledOnce(int width, int height, int x, int y){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(generator).nextStep();
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesAndCoordinatesWithNeighboursToVisit(){
		return new Object[]{
			new Object[]{7,7,3,2,2,2,Direction.WEST},
			new Object[]{8,4,1,1,2,1,Direction.EAST},
			new Object[]{7,7,3,3,3,2,Direction.NORTH},
			new Object[]{7,7,3,3,3,4,Direction.SOUTH}
		};
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithNeighboursToVisit")
	public void shoulAskForNeighbourCoordinates(int width, int height, int x, int y, int nx, int ny, Direction direction){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		generator.setMaze(maze);
		doReturn(direction).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(maze, atLeastOnce()).getNeighbourCoordinates(x, y, direction);
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithNeighboursToVisit")
	public void shouldNotAskForNeighbourCoordinates(int width, int height, int x, int y, int nx, int ny, Direction direction){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		generator.setMaze(maze);
		doReturn(null).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(maze,never()).getNeighbourCoordinates(x, y, direction);
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithNeighboursToVisit")
	public void shouldPushNeighbourCoordinates(int width, int height, int x, int y, int nx, int ny, Direction direction){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		generator.setRouteCoordinatesStack(stack);
		generator.setMaze(maze);
		doReturn(direction).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(stack).push(new Coordinates2D(nx,ny));
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithNeighboursToVisit")
	public void shouldNotPushNeighbourCoordinates(int width, int height, int x, int y, int nx, int ny, Direction direction){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		generator.setRouteCoordinatesStack(stack);
		generator.setMaze(maze);
		doReturn(null).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(stack,times(1)).push(any(Coordinates2D.class));
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizesAndCoordinatesWithPrevious(){
		return new Object[]{
			new Object[]{7,7,3,2,2,2},
			new Object[]{8,4,1,1,2,1},
			new Object[]{7,7,3,3,3,2},
			new Object[]{7,7,3,3,3,4}
		};
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithPrevious")
	public void shouldPushPreviousCoordinates(int width, int height, int x, int y, int px, int py){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		generator.setRouteCoordinatesStack(stack);
		generator.setMaze(maze);
		doReturn(null).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(px,py));
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(stack,times(2)).push(new Coordinates2D(px,py));
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithNeighboursToVisit")
	public void shouldRemoveWall(int width, int height, int x, int y, int nx, int ny, Direction direction){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		generator.setMaze(maze);
		doReturn(direction).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(maze).removeWall(x, y, direction);
	}
	
	@Test
	@Parameters(method = "getSizesAndCoordinatesWithNeighboursToVisit")
	public void shouldSetCellAsVisited(int width, int height, int x, int y, int nx, int ny, Direction direction){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width,height));
		Maze maze = spy(new Maze(width,height));
		generator.setMaze(maze);
		doReturn(null).when(generator).getRandomUnvisitedNeighbours(x, y);
		generator.getRouteCoordinatesStack().push(new Coordinates2D(x,y));
		generator.nextStep();
		verify(generator, atLeastOnce()).visitCell(x, y);
	}
	
	@Test
	public void shouldInvokeNextStepTwoTimes(){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(8, 8));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(2).thenReturn(1).thenReturn(0);
		generator.generateMaze();
		verify(generator,times(2)).nextStep();
	}
	
	@Test
	public void shouldInvokeNextThreeTwoTimes(){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(8, 8));
		when(generator.getNumberOfUnvisitedCells()).thenReturn(2).thenReturn(2).thenReturn(1).thenReturn(0);
		generator.generateMaze();
		verify(generator,times(3)).nextStep();
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getValidSizesWithValidCoordinates3(){
		return new Object[]{
			new Object[]{8,8,3,3},
			new Object[]{11,13,9,11},
			new Object[]{8,8,0,0},
			new Object[]{8,8,7,7}
		};
	}
	
	@Test
	@Parameters(method = "getValidSizesWithValidCoordinates3")
	public void shouldAskForRandomCellCoordinatesAndPushItToTheStack(int width, int height, int x, int y){
		MazeDFSGenerator generator = spy(new MazeDFSGenerator(width, height));
		Stack<Coordinates2D> stack = spy(new Stack<Coordinates2D>());
		generator.setRouteCoordinatesStack(stack);
		when(generator.getNumberOfUnvisitedCells()).thenReturn(0);
		when(generator.getRandomCellCoordinates()).thenReturn(new Coordinates2D(x,y));
		generator.generateMaze();
		verify(generator).getRandomCellCoordinates();
		verify(stack).push(new Coordinates2D(x,y));
	}
}
