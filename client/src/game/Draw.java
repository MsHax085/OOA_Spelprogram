package src.game;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.game.entities.Entity;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Draw extends JPanel{
	private static final long serialVersionUID = 1L;
	ArrayList<Entity> list;
	int blockSize, gameWidth, gameHeight;
	
	/**
	 * @param list
	 * @param blockSize
	 * @param gameWidth in number of blocks
	 * @param gameHeight in number of blocks
	 */
	public Draw(ArrayList<Entity> list, int blockSize, int gameWidth, int gameHeight){
		this.list = list; 
		this.blockSize = blockSize;
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
	}
	
	public void drawList(ArrayList<Entity> list){
		this.list = list;
		repaint();
	}
	
	public void paint(Graphics g){
		//background is important so no gosting happens
		g.setColor(Color.black);
		g.fillRect(0, 0, gameWidth * blockSize, gameHeight * blockSize);
		
		for(Entity ent : list){
			ent.draw(g, blockSize);
		}
	}
}
