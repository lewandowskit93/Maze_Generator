package com.github.lewandowskit93.maze.generators;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.github.lewandowskit93.maze.core.Cell;
import com.github.lewandowskit93.maze.core.Coordinates2D;
import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.core.InvalidMazeHeightException;
import com.github.lewandowskit93.maze.core.InvalidMazeWidthException;
import com.github.lewandowskit93.maze.core.Maze;
import com.github.lewandowskit93.maze.core.MazeGenerator;

public class MazeDFSGenerator implements MazeGenerator {
	
	private int width,height;
	private Maze maze;
	private HashSet<Cell> unvisitedCells;
	private Random numberGenerator;

	public MazeDFSGenerator(int width, int height) {
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		this.width=width;
		this.height=height;
		this.numberGenerator=new Random();
		reset();
	}

	@Override
	public Maze generateMaze() {
		return maze;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Maze getMaze() {
		return maze;
	}

	public boolean wasCellVisited(int x, int y) {
		return !unvisitedCells.contains(maze.getCell(x,y));
	}

	public void visitCell(int x, int y) {
		if(!wasCellVisited(x,y))unvisitedCells.remove(maze.getCell(x,y));
	}

	public void reset() {
		setMaze(new Maze(width,height));
	}

	public void setMaze(Maze maze) {
		if(maze.getWidth()!=width)throw new InvalidMazeWidthException(maze.getWidth());
		if(maze.getHeight()!=height)throw new InvalidMazeWidthException(maze.getHeight());
		this.maze=maze;
		unvisitedCells = new HashSet<Cell>();
		for(int x=0;x<maze.getWidth();++x){
			for(int y=0;y<maze.getHeight();++y){
				unvisitedCells.add(maze.getCell(x,y));
			}
		}
	}

	public int getNumberOfUnvisitedCells() {
		return unvisitedCells.size();
	}

	public Set<Cell> getUnvisitedCells() {
		return unvisitedCells;
	}

	public Random getRandomNumberGenerator() {
		return numberGenerator;
	}

	public void setRandomNumberGenerator(Random numberGenerator) throws NullPointerException{
		if(numberGenerator==null)throw new NullPointerException("Random number generator cant be set to null.");
		this.numberGenerator=numberGenerator;
		
	}

	public Coordinates2D getRandomCellCoordinates() {
		return new Coordinates2D(numberGenerator.nextInt(width),numberGenerator.nextInt(height));
	}

	public EnumSet<Direction> getUnvisitedNeighbours(int x, int y) {
		EnumSet<Direction> neighbours = maze.getNeighbours(x, y);
		EnumSet<Direction> unvisitedNeighbours = EnumSet.noneOf(Direction.class);
		for(Direction neighbour : neighbours){
			Coordinates2D neighbourCoords = maze.getNeighbourCoordinates(x, y, neighbour);
			if(!wasCellVisited(neighbourCoords.getX(),neighbourCoords.getY())){
				unvisitedNeighbours.add(neighbour);
			}
		}
		return unvisitedNeighbours;
	}

	public Direction getRandomUnvisitedNeighbours(int x, int y) {
		EnumSet<Direction> unvisitedNeighbours = getUnvisitedNeighbours(x, y);
		if(unvisitedNeighbours.size()==0)return null;
		else return (Direction) unvisitedNeighbours.toArray()[numberGenerator.nextInt(unvisitedNeighbours.size())];
	}

}
