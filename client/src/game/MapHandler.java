package src.game;

import java.util.ArrayList;

import src.game.entities.*;
import src.resourceManager.DataBase;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class MapHandler {
	private char[][] map = {{'s', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '3', '0', '0', '0', '3', '0', '3', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '3', '0', '0', '0', '3', '0', '3', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '3', '0', '3', '0', '3', '0', '3', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '3', '3', '0', '3', '3', '0', '3', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0'},
							{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'g'}};
	DataBase db;
	
	public MapHandler(){
		db = DataBase.getInstance();
	}
	
	public ArrayList<Entity> getMap(int mapNumber){
		ArrayList<Entity> list = new ArrayList<Entity>();
		int mapWidth, mapHeight;
		try{
			map = db.readMap(mapNumber);
			mapWidth = Integer.parseInt(db.readConfig("gameWidth"));
			mapHeight = Integer.parseInt(db.readConfig("gameHeight"));
		}catch(Exception e){
			System.out.println("MapHandler - Couldn't read from config or map file.");
			mapWidth = 16;
			mapHeight = 10;
		}
		for(int x = 0; x < mapWidth; x++){
			for(int y = 0; y < mapHeight; y++){
				if(map[y][x]=='b')
					list.add(new Block(x, y));
				else if(map[y][x]=='s')
					list.add(new Start(x, y));
				else if(map[y][x]=='g')
					list.add(new Goal(x, y));
				else if(map[y][x]=='k')
					list.add(new KullerSten(x, y));
				else if(map[y][x]=='l')
					list.add(new Slab(x, y));
			}
		}
		return list;
	}
}
