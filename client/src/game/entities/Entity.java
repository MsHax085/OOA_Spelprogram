package src.game.entities;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author ludwigfriborg
 *
 */
public abstract class Entity {
	protected int x, y;
	protected Color color;
	protected boolean solid;
	
	public Entity(int x, int y, boolean solid){
		color = Color.pink;
		this.x = x;
		this.y = y;
		this.solid = solid;
	}
	
	public void draw(Graphics g, int blockSize){
		g.setColor(color);
		g.fillRect(x*blockSize,y*blockSize, blockSize, blockSize);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}

	public void setX(int x){
		this.x = x;
	}
	public void setY(int y){
		this.y = y;
	}

	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}
	
	public boolean isSolid(){
		return solid;
	}
}
