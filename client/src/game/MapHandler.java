package src.game;

import java.util.ArrayList;

import src.game.entities.*;
import src.resourceManager.Map;
import src.resourceManager.config.ConfigHandler;

/**
 * MapHandler retrievs maps from local resourses
 * @author ludwigfriborg
 * @version 2016-02-29
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
    
	/**
	 * Retrives the desired map from the database
	 * @param mapNumber
	 * @return the desired map
	 */
    public ArrayList<Entity> getMap(int mapNumber){
        ArrayList<Entity> list = new ArrayList<>();
        int mapWidth, mapHeight;
        try{
            map = Map.getInstance().getMap(mapNumber);
            mapWidth = ConfigHandler.getInstance().getGameWidth();
            mapHeight = ConfigHandler.getInstance().getGameHeight();
        } catch (Exception e) {
            System.out.println("MapHandler - Couldn't read from config or map file.");
            mapWidth = 16;
            mapHeight = 10;
        }
        for (int x = 0; x < mapWidth; x++) {
            for (int y = 0; y < mapHeight; y++) {
                if(map[y][x]=='b')
                    list.add(new Wall(x, y));
                else if(map[y][x]=='s')
                    list.add(new Start(x, y));
                else if(map[y][x]=='g')
                    list.add(new Goal(x, y));
                else if(map[y][x]=='k')
                    list.add(new Stone(x, y));
                else if(map[y][x]=='l')
                    list.add(new Slab(x, y));
            }
        }
        return list;
    }
}
