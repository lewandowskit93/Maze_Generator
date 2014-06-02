package com.github.lewandowskit93.maze.generators;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import com.github.lewandowskit93.maze.core.Cell;
import com.github.lewandowskit93.maze.core.Coordinates2D;
import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.core.Hole;
import com.github.lewandowskit93.maze.core.InvalidMazeHeightException;
import com.github.lewandowskit93.maze.core.InvalidMazeWidthException;
import com.github.lewandowskit93.maze.core.Maze;
import com.github.lewandowskit93.maze.core.MazeGenerator;

public class MazeDFSGenerator implements MazeGenerator {
	
	private int width,height;
	private Maze maze;
	private HashSet<Cell> unvisitedCells;
	private Random numberGenerator;
	private Stack<Coordinates2D> routeCoordinatesStack;

	public MazeDFSGenerator(int width, int height) {
		if(width<=0)throw new InvalidMazeWidthException(width);
		if(height<=0)throw new InvalidMazeHeightException(height);
		this.width=width;
		this.height=height;
		this.numberGenerator=new Random();
		this.routeCoordinatesStack=new Stack<Coordinates2D>();
		reset();
	}

	@Override
	public Maze generateMaze() {
		routeCoordinatesStack.push(getRandomCellCoordinates());
		while(getNumberOfUnvisitedCells()>0){
			nextStep();
		}
		Hole startHole = makeRandomHoleInBounds();
		Coordinates2D startCoords = startHole.getCoordinates();
		maze.setStartCoordinates(startCoords.getX(), startCoords.getY());
		Hole finishHole = null;
		do{
			finishHole = makeRandomHoleInBounds();
		}while(startHole.equals(finishHole));
		Coordinates2D finishCoords=finishHole.getCoordinates();
		maze.setFinishCoordinates(finishCoords.getX(), finishCoords.getY());
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
		routeCoordinatesStack.clear();
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

	public Stack<Coordinates2D> getRouteCoordinatesStack() {
		return routeCoordinatesStack;
	}

	public void setRouteCoordinatesStack(Stack<Coordinates2D> stack) {
		if(stack==null)throw new NullPointerException("Route coordinates stack cannot be set to null.");
		this.routeCoordinatesStack=stack;	
	}

	public void nextStep() {
		if(!routeCoordinatesStack.empty()){
			Coordinates2D coordinates = routeCoordinatesStack.pop();
			visitCell(coordinates.getX(), coordinates.getY());
			Direction direction = getRandomUnvisitedNeighbours(coordinates.getX(), coordinates.getY());
			if(direction!=null){
				routeCoordinatesStack.push(coordinates);
				Coordinates2D neighbourCoordinates = maze.getNeighbourCoordinates(coordinates.getX(), coordinates.getY(), direction);
				routeCoordinatesStack.push(neighbourCoordinates);
				maze.removeWall(coordinates.getX(), coordinates.getY(), direction);
			}
			else{
				if(!routeCoordinatesStack.empty()){
					Coordinates2D previousCoordinates = routeCoordinatesStack.pop();
					routeCoordinatesStack.push(previousCoordinates);
				}
			}
		}
	}

	public Hole makeRandomHoleInBounds() {
		int ord = numberGenerator.nextInt(Direction.values().length);
		Direction toRemove = Direction.values()[ord];
		Coordinates2D coordinates = null;
		switch(toRemove){
			case NORTH:{
				coordinates = new Coordinates2D(numberGenerator.nextInt(width), 0);
				
				break;
			}
			case EAST:{
				coordinates = new Coordinates2D(width-1,numberGenerator.nextInt(height));
				break;
			}
			case SOUTH:{
				coordinates = new Coordinates2D(numberGenerator.nextInt(width), height-1);
				break;
			}
			case WEST:{
				coordinates = new Coordinates2D(0,numberGenerator.nextInt(height));
				break;
			}
			default:{
				
			}
		}
		if(coordinates!=null){
			maze.removeWall(coordinates.getX(), coordinates.getY(), toRemove);
		}
		return new Hole(coordinates,toRemove);
	}

}
