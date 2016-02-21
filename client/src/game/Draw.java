package src.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import src.game.entities.Entity;
import src.resourceManager.DataBase;

/**
 * 
 * @author ludwigfriborg
 *
 *	Draw
 *	Draws is a JPanel that displays a game playing field
 *	the field could belong to anyone, the only thing draw 
 *	does is to draw the passed list of entities
 *
 */
public class Draw extends JPanel{
	private static final long serialVersionUID = 1L;
	ArrayList<Entity> list;
	int blockSize, gameWidth, gameHeight;
	DataBase db;
	
	/**
	 * @param list
	 * @param blockSize
	 */
	public Draw(ArrayList<Entity> list, int blockSize){
		this.list = list; 
		this.blockSize = blockSize;
		db = DataBase.getInstance();
		
		try{
			this.gameWidth = Integer.parseInt(db.readConfig("gameWidth"));
			this.gameHeight = Integer.parseInt(db.readConfig("gameHeight"));
		}catch(NullPointerException e){
			System.out.println("Draw - Couldn't read from config.");
			
			this.gameWidth = 16;
			this.gameHeight = 10;
		}
		
		this.setPreferredSize(new Dimension(gameWidth*this.blockSize, gameHeight*this.blockSize));
	}
	
	public void drawList(ArrayList<Entity> list){
		this.list = list;
		repaint();
	}
	
	public void paint(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, gameWidth * blockSize, gameHeight * blockSize);
		
		for(Entity ent : list){
			ent.draw(g, blockSize);
		}
	}
}
