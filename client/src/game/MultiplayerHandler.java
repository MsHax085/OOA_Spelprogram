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
    private TreeMap<String, Draw> playerMap;
    private JPanel panel;
    private boolean anyOneHasFinished;
	
    public MultiplayerHandler(JPanel panel){
        playerMap = new TreeMap<>();
        this.panel = panel;
        anyOneHasFinished = false;
    }

    public TreeMap<String, Draw> getMap(){
        return playerMap;
    }

    public void addPlayer(String name, int mapNumber){
    	if(playerMap.get(name) == null){
	        ArrayList<Entity> list = new MapHandler().getMap(mapNumber);
	        Player player = new Player(0, 0);
	        for (Entity ent : list) {
	            if (ent.getClass() == Start.class) {
	                player.setX(ent.getX());
	                player.setY(ent.getY());
	            }
	        }
	        list.add(player);
	        playerMap.put(name, new Draw(list, 16));
	        panel.add(playerMap.get(name));
	        System.out.println(name + " has been added.");
    	}
    }

    public void updatePlayer(String name, int x, int y){
        ArrayList<Entity> list = playerMap.get(name).getList();
        for (Entity ent : list) {
            if (ent.getClass() == Player.class) {
                ent.setX(x);
                ent.setY(y);
            }
        }
        playerMap.get(name).drawList(list);
    }

    public void updateSlab(String name, int x, int y){
        ArrayList<Entity> list = playerMap.get(name).getList();
        for (Entity ent : list) {
            if (ent.getClass() == Slab.class) {
                ent.setX(x);
                ent.setY(y);
            }
        }
        playerMap.get(name).drawList(list);
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
    
    public void playerHasFinished(String name, int time){
    	if(!playerMap.get(name).getHasFinished()){
	        if(anyOneHasFinished == false){
	            anyOneHasFinished = true;
	            playerMap.get(name).setIsWinner();
	        }
	        System.out.println(name + " is done.");
	        playerMap.get(name).setHasFinished();
	        playerMap.get(name).setTime(time);
	        playerMap.get(name).repaint();
    	}
    }

    public void resetPlayer(String name, int mapNumber){
        playerMap.get(name).drawList(new MapHandler().getMap(mapNumber));
    }
}
