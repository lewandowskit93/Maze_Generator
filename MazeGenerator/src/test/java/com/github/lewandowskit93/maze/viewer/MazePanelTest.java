package com.github.lewandowskit93.maze.viewer;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.lewandowskit93.maze.core.Cell;
import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.core.Maze;

import static org.mockito.Mockito.*;

@RunWith(JUnitParamsRunner.class)
public class MazePanelTest {

	@Test
	public void shouldBeAbleToCreateMazePanel(){
		MazePanel panel = new MazePanel();
		assertTrue(panel.isDoubleBuffered());
		assertNull(panel.getLayout());
		assertEquals(800,panel.getWidth());
		assertEquals(600,panel.getHeight());
		assertTrue(panel.isVisible());
	}
	
	@Test
	public void shouldBeAbleToCreateBufferedMazePanel(){
		MazePanel panel = new MazePanel(true);
		assertTrue(panel.isDoubleBuffered());
		assertNull(panel.getLayout());
		assertEquals(800,panel.getWidth());
		assertEquals(600,panel.getHeight());
		assertTrue(panel.isVisible());
	}

	@Test
	public void shouldBeAbleToCreateNotBufferedMazePanel(){
		MazePanel panel = new MazePanel(false);
		assertFalse(panel.isDoubleBuffered());
		assertNull(panel.getLayout());
		assertEquals(800,panel.getWidth());
		assertEquals(600,panel.getHeight());
		assertTrue(panel.isVisible());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getSizes(){
		return new Object[]{
				new Dimension(100,120),
				new Dimension(200,300),
				new Dimension(400,200),
				new Dimension(320,240),
				new Dimension(1024,800)
		};
	}
	
	@Test
	@Parameters(method = "getSizes")
	public void shouldBeAbleToCreateSizedMazePanel(Dimension dimension){
		MazePanel panel = new MazePanel(dimension.width,dimension.height);
		assertTrue(panel.isDoubleBuffered());
		assertNull(panel.getLayout());
		assertEquals(dimension.width,panel.getWidth());
		assertEquals(dimension.height,panel.getHeight());
		assertTrue(panel.isVisible());
	}
	
	@Test
	@Parameters(method = "getSizes")
	public void shouldBeAbleToCreateBufferedSizedMazePanel(Dimension dimension){
		MazePanel panel = new MazePanel(dimension.width,dimension.height,true);
		assertTrue(panel.isDoubleBuffered());
		assertNull(panel.getLayout());
		assertEquals(dimension.width,panel.getWidth());
		assertEquals(dimension.height,panel.getHeight());
		assertTrue(panel.isVisible());
	}
	
	@Test
	@Parameters(method = "getSizes")
	public void shouldBeAbleToCreateNotBufferedSizedMazePanel(Dimension dimension){
		MazePanel panel = new MazePanel(dimension.width,dimension.height,false);
		assertFalse(panel.isDoubleBuffered());
		assertNull(panel.getLayout());
		assertEquals(dimension.width,panel.getWidth());
		assertEquals(dimension.height,panel.getHeight());
		assertTrue(panel.isVisible());
	}
	
	@SuppressWarnings("unused")
	private static final Object[] getMazes(){
		return new Object[]{
				new Maze(3,3),
				new Maze(1,3),
				new Maze(3,1),
				new Maze(12,13)
		};
	}
	
	@Test
	@Parameters(method = "getMazes")
	public void shouldBeAbleToSetMazes(Maze maze){
		MazePanel panel = new MazePanel();
		panel.setMaze(maze);
		assertSame(maze,panel.getMaze());
	}
	
	@Test
	public void shouldBeAbleToSetMazeTiles(){
		MazePanel panel = new MazePanel();
		HashMap<Integer,BufferedImage> tiles = new HashMap<Integer,BufferedImage>();
		panel.setMazeTiles(tiles);
		assertSame(tiles,panel.getMazeTiles());
	}
	
	@Test
	public void shouldPaintAllTilesCellsWallsShouldBeChecked(){
		Graphics2D g = mock(Graphics2D.class);
		MazePanel panel = new MazePanel(48,96);
		HashMap<Integer,BufferedImage> tiles = new HashMap<Integer,BufferedImage>();
		
		for(int i=0;i<16;++i){
			BufferedImage tile = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
			tiles.put(i, tile);
		}
		panel.setMazeTiles(tiles);
		Cell[][] cells = new Cell[][]{
				new Cell[]{mock(Cell.class),mock(Cell.class),mock(Cell.class),mock(Cell.class)},
				new Cell[]{mock(Cell.class),mock(Cell.class),mock(Cell.class),mock(Cell.class)},
				new Cell[]{mock(Cell.class),mock(Cell.class),mock(Cell.class),mock(Cell.class)},
				new Cell[]{mock(Cell.class),mock(Cell.class),mock(Cell.class),mock(Cell.class)}
		};
		Maze maze = new Maze(4,4,cells);
		for(int y=0;y<4;++y){
			for(int x=0;x<4;++x){
				when(cells[y][x].getWalls()).thenReturn(Direction.getFromIntValue(y*4+x));
				//when(maze.getCell(x,y)).thenReturn(cells[y][x]);
			}
		}
		panel.setMaze(maze);
		panel.paintMaze(g);
		for(int y=0;y<4;++y){
			for(int x=0;x<4;++x){
				BufferedImage tile = tiles.get(y*4+x);
				verify(g).drawImage(tile,x*12,y*24,12,24,null);
				verify(cells[y][x]).getWalls();
			}
		}
		
	}
}
