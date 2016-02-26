package src.game.entities;

import src.resourceManager.Material;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class WALL extends Entity {
    
    public WALL(int x, int y) {
        super(x, y, true);
        material = Material.SLAB.getValue();
    }
}
