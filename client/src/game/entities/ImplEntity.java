package src.game.entities;

import java.awt.Graphics;

/**
 * interface for Game entity
 * @author Richard
 * @version 2016-02-24
 */
public interface ImplEntity {
    
    public void draw(Graphics g, int blockSize);
    public int getX();
    public int getY();
    public void setX(int x);
    public void setY(int y);
    public void move(int x, int y);
    public boolean isSolid();
}
