package src.game.entities;

import src.resourceManager.Material;

/**
 * Class that holds data around specific entity objects, for example the type of material or texture. 
 * @author ludwigfriborg
 * @version 2016-02-26
 */
public class Goal extends Entity {	
    
    public Goal(int x, int y) {
        super(x, y, false);
        material = Material.DIAMOND.getValue();
    }
}