package com.github.lewandowskit93.maze.viewer;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.github.lewandowskit93.maze.core.Direction;
import com.github.lewandowskit93.maze.core.Maze;
import com.github.lewandowskit93.maze.generators.MazeDFSGenerator;

public class MazeGeneratorPanel extends JPanel implements MazeTilesLoader, ComponentListener, ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MazeViewerPanel mazeViewerPanel;
	private MenuPanel menuPanel;
	private JPanel panel;
	private boolean generatingMaze;
	
	
	
	public MazeGeneratorPanel() {
		super();
		initGUI();
	}

	public MazeGeneratorPanel(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		initGUI();
	}

	private void initGUI(){
		addComponentListener(this);
		setLayout(null);
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.LINE_AXIS));
		add(panel);
		
		mazeViewerPanel = new MazeViewerPanel((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight()));
		menuPanel = new MenuPanel((int)Math.floor(getWidth()*0.2),(int)Math.floor(getHeight()));	
		panel.add(mazeViewerPanel);
		panel.add(menuPanel);
		
		mazeViewerPanel.getMazePanel().setMazeTiles(loadTiles());
		mazeViewerPanel.setMinimumSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
		mazeViewerPanel.setPreferredSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
		mazeViewerPanel.setMaximumSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
		mazeViewerPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		
		menuPanel.setMinimumSize(new Dimension(10,getHeight()));
		menuPanel.setPreferredSize(new Dimension((int)Math.min(Math.floor(getWidth()*0.2),200),(int)Math.floor(getHeight())));
		menuPanel.setMaximumSize(new Dimension(200,getHeight()));
		menuPanel.setBorder(BorderFactory.createRaisedBevelBorder());
		menuPanel.getGenerateMazeButton().addActionListener(this);
	}
	
	public synchronized boolean isGeneratingMaze(){
		return generatingMaze;
	}
	
	public synchronized void setGeneratingMaze(boolean generatingMaze){
		this.generatingMaze=generatingMaze;
	}
	
	public synchronized boolean tryStartGeneratingMaze(){
		if(!generatingMaze){
			generatingMaze=true;
			return true;
		}
		else return false;
	}

	@Override
	public HashMap<Integer, BufferedImage> loadTiles() {
		
		
		HashMap<Integer, BufferedImage> tiles = new HashMap<Integer, BufferedImage>();
		for(int i=0;i<16;++i){
			BufferedImage tile = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2d = (Graphics2D)tile.getGraphics();
			g2d.setBackground(Color.BLACK);
			g2d.clearRect(0,0,32,32);
			g2d.setColor(Color.WHITE);
			BasicStroke stroke = new BasicStroke(3);
			g2d.setStroke(stroke);
			if(Direction.getFromIntValue(i).contains(Direction.NORTH))g2d.drawRect(0, 1, 32, 0);
			if(Direction.getFromIntValue(i).contains(Direction.SOUTH))g2d.drawRect(0, 30, 32, 0);
			if(Direction.getFromIntValue(i).contains(Direction.WEST))g2d.drawRect(1, 0, 0, 32);
			if(Direction.getFromIntValue(i).contains(Direction.EAST))g2d.drawRect(30, 0, 0, 32);
			tiles.put(i, tile);
		}
		return tiles;
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		if(arg0.getComponent()==this){
			if(panel!=null){
				panel.setLocation(0, 0);
				panel.setSize(getWidth(),getHeight());
			}
			if(mazeViewerPanel!=null){
				mazeViewerPanel.setMinimumSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
				mazeViewerPanel.setPreferredSize(new Dimension((int)Math.floor(getWidth()*0.8),(int)Math.floor(getHeight())));
				mazeViewerPanel.setMaximumSize(new Dimension((int)Math.floor(getWidth()*0.9),(int)Math.floor(getHeight())));
				//mazeViewerPanel.setLocation(0, 0);
			}
			if(menuPanel!=null){
				menuPanel.setMinimumSize(new Dimension(10,getHeight()));
				menuPanel.setPreferredSize(new Dimension((int)Math.min(Math.floor(getWidth()*0.2),200),getHeight()));
				menuPanel.setMaximumSize(new Dimension(200,getHeight()));
				//menuPanel.setLocation(menuPanel.getLocation().x,menuPanel.getLocation().y);
			}
			//validate();
			//repaint();
		}
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(menuPanel!=null){
			if(e.getSource()==menuPanel.getGenerateMazeButton()){
				if(tryStartGeneratingMaze()){
					Thread generatorThread = new Thread(new Runnable(){
						
						private int width,height;
						private MazePanel panel;
						private MazeGeneratorPanel generatorPanel;
						
						@Override
						public void run() {
							MazeDFSGenerator generator = new MazeDFSGenerator(width, height);
							Maze maze = generator.generateMaze();
							panel.setMaze(maze);
							panel.repaint();
							generatorPanel.setGeneratingMaze(false);
						}
						
						public Runnable init(int width, int height, MazePanel panel, MazeGeneratorPanel generatorPanel){
							this.width=width;
							this.height=height;
							this.panel=panel;
							this.generatorPanel=generatorPanel;
							return this;
						}
						
						
					}.init(menuPanel.getMazeWidth(),menuPanel.getMazeHeight(),mazeViewerPanel.getMazePanel(),this));
					
					generatorThread.start();
					
				}
			}
				
		}
	}

}
