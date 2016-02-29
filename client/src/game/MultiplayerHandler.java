package src.game;

import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JPanel;

import src.game.entities.*;

/**
 * 
 * @author ludwigfriborg
 *
 */

public class MultiplayerHandler {
    private TreeMap<Integer, Draw> playerMap;
    private JPanel panel;
    private boolean anyOneHasFinished;
	
    public MultiplayerHandler(JPanel panel){
        playerMap = new TreeMap<>();
        this.panel = panel;
        anyOneHasFinished = false;
    }

    public TreeMap<Integer, Draw> getMap(){
        return playerMap;
    }

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
    	}
    }

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

    public boolean getAnyOneHasFinished(){
        return anyOneHasFinished;
    }

    public void setAnyOneHasFinished(){
        anyOneHasFinished = true;
    }

    public boolean hasEveryBodyFinished(){
    	boolean temp = true;
    	for(Draw obj : playerMap.values()){
    		temp = obj.getHasFinished();
    	}
    	return temp;
    }
    
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

    public void resetPlayer(int id, int mapNumber){
    	if(playerMap.get(id) == null){
    		System.out.println("client not available");
    	}else{
    		playerMap.get(id).drawList(new MapHandler().getMap(mapNumber));
    	}
    }
}
