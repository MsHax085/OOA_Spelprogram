package src.game.entities;

import src.resourceManager.Material;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Goal extends Entity {	
    
    public Goal(int x, int y) {
        super(x, y, false);
        material = Material.DIAMOND.getValue();
    }
}