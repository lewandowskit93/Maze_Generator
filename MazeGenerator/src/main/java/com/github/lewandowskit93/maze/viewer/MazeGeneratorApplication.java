package com.github.lewandowskit93.maze.viewer;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

public class MazeGeneratorApplication {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run(){
				MazeGeneratorFrame mgf = new MazeGeneratorFrame("Maze Generator");
				mgf.setSize(new Dimension(800, 600));
				mgf.setDefaultCloseOperation(MazeGeneratorFrame.EXIT_ON_CLOSE);
				mgf.setVisible(true);
			}
		});
	}

}
