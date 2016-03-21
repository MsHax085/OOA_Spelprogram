package src.resourceManager;

import src.resourceManager.config.ConfigHandler;
import src.resourceManager.file.FileHandler;


/**
 * This class handles reading of map files.
 * It reads the maps from a file and saves them
 * to local variables, because its desirable to
 * have often used information stored in the memory
 * instead of putting unnecessary load on the harddrive. 
 * 
 * @author Oscar Andersson
 * @version 2016-2-28
 */

public class Map {

    private FileHandler fh;
    private ConfigHandler cO;
    private String mapPath;
    private static Map instance = null;
    private final char[][] map1;
    private final char[][] map2;

    public Map() {
    	mapPath = "map";
        fh = FileHandler.getInstance();
        cO = ConfigHandler.getInstance();
        map1 = readMap(1);
        map2 = readMap(2);
    }
    
    /**
     * Returns the current instance of Map.
     * If there is none, one will be created.
     * 
     * @return The instance of Map.
     */
    public static Map getInstance() {
        if (instance == null) instance = new Map();
        return instance;
    }
    /**
     * This method read the content of a map file and return the map
     * as a char[][].
     * @param mapnumber - the number of the map that is to be read.
     * @return a specified map as a char[][].
     */
    public char[][] readMap(int mapnumber) {
        int width = cO.getGameWidth();
        int height = cO.getGameHeight();

        String tempString = fh.readFile(mapPath + mapnumber + ".txt");
        String[] splitRows = tempString.split("\n");
        String[] splitCol;
        char[][] builder = new char[height][width];

        try {
            for (int i=0; i < splitRows.length; i++) {
                splitCol = splitRows[i].split(",");
                for (int p = 0; p < splitCol.length; p++) {
                    builder[i][p] = splitCol[p].charAt(0);
                }

            }
            return builder;
        } catch (Exception e) {
            System.out.println("Error reading map");
            return null; // Return null if the map "building" wasnt successful. 
        }
    }
    

    /**
     * Returns a specific map.
     * 
     * @param map - number of the map that is to be returned
     * @return - the map
     */
    public char[][] getMap(int map){
    	switch (map) {
        case 1:
            return map1;
        case 2:
        	return map2;	
    }
    	return null; //else returns null
    }
}
	
	
	
	
	
	

