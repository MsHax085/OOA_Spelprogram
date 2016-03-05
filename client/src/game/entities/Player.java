package src.game.entities;

import src.resourceManager.Material;

/**
 * Subclass to entity
 * @author ludwigfriborg
 * @version 2016-02-26
 */
public class Player extends Entity {

    public Player(int x, int y) {
        super(x,y,false);
        material = Material.BOX.getValue();
    }
}
