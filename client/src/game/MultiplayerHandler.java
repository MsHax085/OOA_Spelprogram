package src.game;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JPanel;

import src.game.entities.*;

/**
 * 
 * @author ludwigfriborg
 *
 * MultiplayerHandler handles all the external players, by containing a list of their boards (Draw-class).  
 *
 */
public class MultiplayerHandler {
    private TreeMap<Integer, Draw> playerMap;
    private JPanel panel;
    private boolean anyOneHasFinished;
	
    /**
     * requires a panel that the other players displaypanls can be added to.
     * @param panel
     */
    public MultiplayerHandler(JPanel panel){
        playerMap = new TreeMap<>();
        this.panel = panel;
        anyOneHasFinished = false;
    }
    
    public TreeMap<Integer, Draw> getMap(){
        return playerMap;
    }

    /**
     * Adds a player to the multiplayerhandler, if an elemt with the given id allready exists does the method nothing
     * @param id - clients id
     * @param name - clients name
     * @param mapNumber - current map number
     */
    public void addPlayer(int id , String name, int mapNumber){
    	if(playerMap.get(id) == null){
	        ArrayList<Entity> list = new MapHandler().getMap(mapNumber);
	        Player player = new Player(0, 0);
	        for (Entity ent : list) {
	            if (ent.getClass() == Start.class) {
	                player.setX(ent.getX());
	                player.setY(ent.getY());
	            }
	        }
	        list.add(player);
	        playerMap.put(id, new Draw(list, name, 16));
	        panel.add(playerMap.get(id));
	        System.out.println(name + " has been added.");
    	}else{
    		System.out.println("client does allready exist");
    	}
    }

    /**
     * Updates the desired player's playerPosition
     * @param id - clients id
     * @param x - desired x
     * @param y - desired y
     */
    public void updatePlayer(int id, int x, int y){
    	if(playerMap.get(id) == null){
    		System.out.println("client not available");
    	}else{
	        ArrayList<Entity> list = playerMap.get(id).getList();
	        for (Entity ent : list) {
	            if (ent.getClass() == Player.class) {
	                ent.setX(x);
	                ent.setY(y);
	            }
	        }
	        playerMap.get(id).drawList(list);
    	}
    }

    /**
     * Updates the desired player's slabPosition
     * @param id - clients id
     * @param x - desired x
     * @param y - desired y
     */
    public void updateSlab(int id, int x, int y){
    	if(playerMap.get(id) == null){
    		System.out.println("client not available");
    	}else{
	        ArrayList<Entity> list = playerMap.get(id).getList();
	        for (Entity ent : list) {
	            if (ent.getClass() == Slab.class) {
	                ent.setX(x);
	                ent.setY(y);
	            }
	        }
	        playerMap.get(id).drawList(list);
    	}
    }
    
    /**
     * @return true if anyone has finished else false
     */
    public boolean getAnyOneHasFinished(){
        return anyOneHasFinished;
    }

    public void setAnyOneHasFinished(){
        anyOneHasFinished = true;
    }

    /**
     * @return true if everybody in the game has finished else false
     */
    public boolean hasEveryBodyFinished(){
    	boolean temp = true;
    	for(Draw obj : playerMap.values()){
    		temp = obj.getHasFinished();
    	}
    	return temp;
    }
    
    /**
     * Sets a player done, if it has not allready finished. If the player also is the first one it sets as game winner aswell.
     * @param id - clients id
     * @param time - total time
     */
    public void playerHasFinished(int id, int time){
    	if(!playerMap.get(id).getHasFinished() && playerMap.get(id) != null){
	        if(anyOneHasFinished == false){
	            anyOneHasFinished = true;
	            playerMap.get(id).setIsWinner();
	        }
	        System.out.println(playerMap.get(id).getName() + " is done.");
	        playerMap.get(id).setHasFinished();
	        playerMap.get(id).setTime(time);
	        playerMap.get(id).repaint();
    	}
    }

    /**
     * Resets a player 
     * @param id - clients id
     * @param mapNumber - current map number
     */
    public void resetPlayer(int id, int mapNumber){
    	if(playerMap.get(id) == null){
    		System.out.println("client not available");
    	}else{
    		playerMap.get(id).drawList(new MapHandler().getMap(mapNumber));
    	}
    }
}
