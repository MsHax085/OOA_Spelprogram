package src.resourceManager;

/**
 *
 * @author Richard
 */
public enum Material {
    GRASS(0),
    STONE(1),
    SLAB(2),
    TNT(3),
    DIAMOND(4),
    SAND(5),
    BOX(6);
    
    private final int value;
    
    private Material(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
