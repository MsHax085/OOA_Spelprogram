package src.game.entities;

import java.awt.Color;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Block extends Entity {
	public Block(int x, int y) {
		super(x, y, true);
		color = Color.green;
	}
}
