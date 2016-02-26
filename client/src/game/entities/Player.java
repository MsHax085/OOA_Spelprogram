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
        color = new Color(151,195,209);
    }
}
