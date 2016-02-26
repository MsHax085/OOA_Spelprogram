package src.game.entities;

import src.resourceManager.Material;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Player extends Entity {

    public Player(int x, int y) {
        super(x,y,false);
        material = Material.BOX.getValue();
    }
}
