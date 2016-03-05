package src.game.entities;

import src.resourceManager.Material;

/**
 * Subclass to entity
 * @author ludwigfriborg
 * @version 2016-02-26
 */
public class Slab extends Entity {

    public Slab(int x, int y) {
        super(x, y, false);
        material = Material.TNT.getValue();
    }
}
