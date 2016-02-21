package src.game.entities;

import java.awt.Color;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Player extends Entity {

	public Player(int x, int y) {
		super(x,y,false);
		color = Color.blue;
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
}
