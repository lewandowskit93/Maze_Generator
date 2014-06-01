package com.github.lewandowskit93.maze.viewer;

import java.awt.image.BufferedImage;
import java.util.HashMap;


public interface MazeTilesLoader {
	HashMap<Integer, BufferedImage> loadTiles();
}
