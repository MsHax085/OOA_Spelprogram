package src.game.entities;

import java.awt.Color;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Goal extends Entity {	
    
    public Goal(int x, int y) {
        super(x, y, false);
        color = new Color(214,227,138);
    }
}