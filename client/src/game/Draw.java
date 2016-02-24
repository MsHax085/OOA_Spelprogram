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
	private ArrayList<Entity> list;
	private int blockSize, gameWidth, gameHeight;
	private DataBase db;
	
	/**
	 * @param list
	 * @param blockSize
	 */
	public Draw(ArrayList<Entity> list, int blockSize){
		this.list = list; 
		this.blockSize = blockSize;
		db = DataBase.getInstance();
		
		try{
			this.gameWidth = db.getConfigHandler().getGameWidth();
			this.gameHeight = db.getConfigHandler().getGameHeight();
		}catch(NullPointerException | NumberFormatException e){
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
	
	public ArrayList<Entity> getList(){
		return list;
	}
	
	public void paint(Graphics g){
		g.setColor(new Color(39,161,14));
		g.fillRect(0, 0, gameWidth * blockSize, gameHeight * blockSize);
		
		for(Entity ent : list){
			ent.draw(g, blockSize);
		}
	}
}
