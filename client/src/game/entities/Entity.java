package src.game.entities;

import java.awt.Graphics;
import src.resourceManager.Resources;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Entity implements ImplEntity {
    
    protected int x, y;
    protected int material;
    protected boolean solid;

    public Entity(int x, int y, boolean solid){
        this.x = x;
        this.y = y;
        this.solid = solid;
    }

    @Override
    public void draw(Graphics g, int blockSize){
        g.drawImage(Resources.getInstance().getImage(material), x * blockSize, y * blockSize, blockSize, blockSize, null);
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
