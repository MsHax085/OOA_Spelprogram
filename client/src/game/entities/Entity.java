package src.game.entities;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Entity implements ImplEntity {
    protected int x, y;
    protected Color color;
    protected boolean solid;

    public Entity(int x, int y, boolean solid){
        color = Color.pink;
        this.x = x;
        this.y = y;
        this.solid = solid;
    }

    @Override
    public void draw(Graphics g, int blockSize){
        g.setColor(color);
        g.fillRect(x*blockSize,y*blockSize, blockSize, blockSize);
    }

    @Override
    public int getX(){
        return x;
    }
    
    @Override
    public int getY(){
        return y;
    }

    @Override
    public void setX(int x){
        this.x = x;
    }
    @Override
    public void setY(int y){
        this.y = y;
    }

    @Override
    public void move(int x, int y){
        this.x += x;
        this.y += y;
    }

    @Override
    public boolean isSolid(){
        return solid;
    }
}
