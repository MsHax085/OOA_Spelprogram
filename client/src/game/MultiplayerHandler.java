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
	private boolean anyOneDone;
	
	public MultiplayerHandler(JPanel panel){
		playerMap = new TreeMap<String, Draw>();
		this.panel = panel;
		anyOneDone = false;
	}
	
	public TreeMap<String, Draw> getMap(){
		return playerMap;
	}
	
	public void addPlayer(String name, int mapNumber){
		ArrayList<Entity> list = new MapHandler().getMap(mapNumber);
		Player player = new Player(0, 0);
		for(Entity ent : list){
			if(ent.getClass() == Start.class){
				player.setX(ent.getX());
				player.setY(ent.getY());
			}
		}
		list.add(player);
		playerMap.put(name, new Draw(list, 16));
		panel.add(playerMap.get(name));
	}
	
	public void updatePlayer(String name, int x, int y){
		ArrayList<Entity> list = playerMap.get(name).getList();
		for(Entity ent : list){
			if(ent.getClass() == Player.class){
				ent.setX(x);
				ent.setY(y);
			}
		}
		playerMap.get(name).drawList(list);
	}

	public void updateSlab(String name, int x, int y){
		ArrayList<Entity> list = playerMap.get(name).getList();
		for(Entity ent : list){
			if(ent.getClass() == Slab.class){
				ent.setX(x);
				ent.setY(y);
			}
		}
		playerMap.get(name).drawList(list);
	}
	
	
	
	public void playerIsDone(String name, int time){
		boolean isWinner = true;
		for(Draw ent : playerMap.values()){
			if(ent.getIsDone()){
				isWinner = false;
			}
		}
		if(isWinner)
			playerMap.get(name).setIsWinner();
		playerMap.get(name).setIsDone();
		playerMap.get(name).setTime(time);
		playerMap.get(name).repaint();
		
	}
	
	public void resetPlayer(String name, int mapNumber){
		playerMap.get(name).drawList(new MapHandler().getMap(mapNumber));
	}
}
