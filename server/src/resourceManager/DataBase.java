package src.resourceManager;

import src.resourceManager.config.ConfigHandler;
/**
 * 
 * @author Oscar Andersson
 *
 */
public class DataBase{
	
	private Score scoreObj;
	private ConfigHandler confObj;
	private static DataBase instance = null;
	
	public DataBase() {
		scoreObj = new Score();
		confObj = new ConfigHandler();
	}

	public static DataBase getInstance(){
		if(instance == null){
			instance = new DataBase();
		}
		return instance;
	}


	public Score getScore(){
		return scoreObj;
	}
	public ConfigHandler getConfigHandler() {
        return confObj;
    }
	
}
