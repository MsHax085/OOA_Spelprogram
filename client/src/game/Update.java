package src.game;

import java.io.IOException;
import java.util.ArrayList;

import src.game.entities.*;
import src.network.Connection;
import src.network.PacketBuilder;

/**
 * 
 * @author ludwigfriborg
 * 
 * Update is the model in the game packet and the class job is to keep track of the local game.
 * It is here normal game logic is acounted for. 
 *
 */

public class Update{
    
    private ArrayList<Entity> entities;
    private int mapNumber;
    private boolean hasFinished;
    private Player player;

    public Update(int mapNumber){
        this.mapNumber = mapNumber;
        hasFinished = false;

        init();
    }

    /**
     * initiates the local player
     */
    public void init(){
        entities = new MapHandler().getMap(mapNumber);
        player = new Player(0,0);

        for (Entity ent : entities) {
            if (ent.getClass() == Start.class) {
                player.setX(ent.getX());
                player.setY(ent.getY());
            }
        }

        entities.add(player);
    }
    
    /**
     * updates the local player based on key input
     * @param gl - a GameKeyListener
     */
    public void updateMovement(GameKeyListener gl){
        if (gl.getKeyLeft()) {
            move(-1, 0);
        } else if(gl.getKeyRight()) {
            move(1, 0);
        } else if(gl.getKeyUp()) {
            move(0, -1);
        } else if(gl.getKeyDown()) {
            move(0, 1);
        } else if(gl.getKeySpace()) {
            init();
            UpdateResetMultiplayer();
        }
    }

    /**
     * moves the player aswell as other game object
     * @param x - the relative x-position (delta x)
     * @param y - the relative y-position (delta y)
     */
    private void move(int x, int y){
        boolean moveAllowed = true;
        Slab slab = null;
        for (Entity ent : entities) {
            if (intersect(player.getX() + x, ent.getX(), player.getY() + y, ent.getY()) && ent.isSolid()) {
                moveAllowed = false;
            } else if(intersect(player.getX() + x, ent.getX(), player.getY() + y, ent.getY()) && ent.getClass() == Slab.class) {
                moveAllowed = isSlabMoveable((Slab)ent, x, y);
                slab = (Slab)ent;
            }
        }

        
        if (moveAllowed) {
            player.move(x, y);
            if (slab != null) {
            	slab.move(x, y);
            	UpdateMoveMultiplayer(player.getX(), player.getY(), slab.getX(), slab.getY());
            }
            else {
            	//possitionen 255 betyder att det var ovörändrat
            	UpdateMoveMultiplayer(player.getX(), player.getY(), 255, 255);
            }
        }
    }

    /**
     * checks if the slab is moveable and if it has reached the final destination if so, sets hasFinished true.
     * @param slab - the desired slab object
     * @param x - the relative x-position (delta x)
     * @param y - the relative y-position (delta y)
     * @return true if the slab is moveable else false
     */
    private boolean isSlabMoveable(Slab slab, int x, int y){
        for(Entity ent : entities){
            if (intersect(slab.getX() + x, ent.getX(), slab.getY() + y, ent.getY()) && ent.getClass() != Goal.class) {
                return false;
            } else if (intersect(slab.getX() + x, ent.getX(), slab.getY() + y, ent.getY()) && ent.getClass() == Goal.class) {
                hasFinished = true;
            }
        }
        return true;
    }

    private boolean intersect(int x1, int x2, int y1, int y2){
        return x1 == x2 && y1 == y2;
    }

    /**
     * Updates the server if a move was made
     * @param playerX
     * @param playerY
     * @param slabX
     * @param slabY
     */ 
    private void UpdateMoveMultiplayer(int playerX, int playerY, int slabX, int slabY){
    	try {
			Connection.getInstance().sendPacket(
					PacketBuilder.getInstance().create05MoveGameEntetiesPacket(playerX, playerY, slabX, slabY));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Updates the server if a reset was made
     */
    private void UpdateResetMultiplayer(){
    	try {
			Connection.getInstance().sendPacket(
					PacketBuilder.getInstance().create07PlayerResetPacket());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public boolean hasFinished(){
        return hasFinished;
    }

    public ArrayList<Entity> getList(){
        return entities;
    }
}