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
	
	public Entity(int x, int y){
		color = Color.pink;
		this.x = x;
		this.y = y;
	}
	
	public void draw(Graphics g, int blockSize){
		g.setColor(color);
		
		//ritar dem på rätt ruta
		g.fillRect(x*blockSize,y*blockSize, blockSize, blockSize);
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
}
