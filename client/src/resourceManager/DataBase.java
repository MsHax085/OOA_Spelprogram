package src.resourceManager;

import src.resourceManager.config.ConfigHandler;


/**
 * 
 * @author Oscar Andersson
 *
 * 
 */
public class DataBase{
	
    private final Map mapObj;
    private final ConfigHandler confObj;
    private static DataBase instance = null;

    /* Constructs a Score Object assigning its filepath. 
     */
    private DataBase() {
        mapObj = new Map("map");
        confObj = new ConfigHandler();
    }

    public static DataBase getInstance(){
        if (instance == null) instance = new DataBase();
        return instance;
    }
    public ConfigHandler getConfigHandler() {
        return confObj;
    }

    public Map getMap() {
        return mapObj;
    }
}
