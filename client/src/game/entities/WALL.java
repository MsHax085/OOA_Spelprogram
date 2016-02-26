package src.game.entities;

import src.resourceManager.Material;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Wall extends Entity {
    
    public Wall(int x, int y) {
        super(x, y, true);
        material = Material.SLAB.getValue();
    }
}
