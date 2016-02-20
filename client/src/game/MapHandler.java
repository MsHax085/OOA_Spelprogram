package src.game;

import java.util.ArrayList;

import src.game.entities.*;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class MapHandler {
	private char[][] map = {{'3', '3', '0', '0'},
							{'3', '0', '0', '0'},
							{'3', '0', '3', '3'},
							{'3', '0', '3', '3'},
							{'3', '0', '0', '0'},
							{'3', '3', '0', 's'}}; //lite f�r att testa
	private int blockSize;
	public MapHandler(int blockSize){
		this.blockSize = blockSize;
	}
	
	public ArrayList<Entity> getMap(int mapNumber){
		ArrayList<Entity> list = new ArrayList<Entity>();
		//map = DataBase.getMap(mapNumber); -ska h�mta mappen fr�n lokalt doc, dummykod f�r�vrigt
		
		for(int x = 0; x < 4; x++){
			for(int y = 0; y < 6; y++){
				if(map[y][x]=='3')
					list.add(new Block(x, y));
				else if(map[y][x]=='s')
					list.add(new Start(x, y));
			}
		}
		return list;
	}
}
