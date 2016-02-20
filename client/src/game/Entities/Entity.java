package src.game.entities;
/**
 * 
 * @author ludwigfriborg
 *
 */
public class Entity {

    private int x = -1;
    private int y = -1;
    
    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Entity() {
        this(-1, -1);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
