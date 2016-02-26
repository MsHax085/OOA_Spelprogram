package src.game.entities;

import src.resourceManager.Material;

public class Slab extends Entity {

    public Slab(int x, int y) {
        super(x, y, false);
        material = Material.TNT.getValue();
    }
}
