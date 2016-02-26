package src.game.entities;

import src.resourceManager.Material;

public class Stone extends Entity {

    public Stone(int x, int y) {
        super(x, y, false);
        material = Material.STONE.getValue();
    }
}
