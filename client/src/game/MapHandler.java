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
							{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', 'g'},}; //lite för att testa
	private int blockSize;
	DataBase db;
	
	public MapHandler(int blockSize){
		this.blockSize = blockSize;
		db = DataBase.getInstance();
	}
	
	public ArrayList<Entity> getMap(int mapNumber){
		ArrayList<Entity> list = new ArrayList<Entity>();
		//map = db.getMap(mapNumber);
		int mapWidth, mapHeight;
		try{
			mapWidth = Integer.parseInt(db.readConfig("gameWidth"));
			mapHeight = Integer.parseInt(db.readConfig("gameHeight"));
		}catch(Exception e){
			System.out.println("MapHandler - Couldn't read from config.");
			mapWidth = 16;
			mapHeight = 10;
		}
		for(int x = 0; x < mapWidth; x++){
			for(int y = 0; y < mapHeight; y++){
				if(map[y][x]=='3')
					list.add(new Block(x, y));
				else if(map[y][x]=='s')
					list.add(new Start(x, y));
				else if(map[y][x]=='g')
					list.add(new Goal(x, y));
			}
		}
		return list;
	}
}
