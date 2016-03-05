package src.game.entities;

import src.resourceManager.Material;

/**
 * Subclass to entity
 * @author ludwigfriborg
 * @version 2016-02-26
 */
public class Wall extends Entity {
    
    public Wall(int x, int y) {
        super(x, y, true);
        material = Material.SLAB.getValue();
    }
}
