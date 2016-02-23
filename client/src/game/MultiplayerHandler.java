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
	TreeMap<String, Draw> playerMap;
	JPanel panel;
	
	public MultiplayerHandler(JPanel panel){
		playerMap = new TreeMap<String, Draw>();
		this.panel = panel;
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
		
	}
}
