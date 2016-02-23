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
	
	public void move(int x, int y){
		this.x += x;
		this.y += y;
	}
}
