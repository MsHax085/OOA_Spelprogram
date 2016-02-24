package src.resourceManager;

import src.resourceManager.config.ConfigHandler;
import src.resourceManager.file.FileHandler;


/**
 * 
 * @author Oscar Andersson
 *
 * 
 */
public class DataBase{
	
	private FileHandler fileObj;
	private ConfigHandler configObj;
	private Map mapObj;
	private static DataBase instance = null;
	
	/* Constructs a Score Object assigning its filepath. 
	 */
	private DataBase() {
		fileObj = FileHandler.getInstance();
		configObj = new ConfigHandler("config.txt");
		mapObj = new Map("map");
	}
	
	public static DataBase getInstance(){
		if(instance == null)
		{
			instance = new DataBase();
		}
		return instance;
	}
	

	
	public String readFile(String fileN)
	{
		return fileObj.readFile(fileN);
	}
	public Boolean writeFile(String fileN,String writeC)
	{
		return fileObj.writeFile(fileN,writeC);
	}
	
	public String readConfig(String setting){
		return configObj.readConfig(setting);
	}

	
	public Boolean writeConfig(String setting, String value){
		return configObj.writeConfig(setting,value);
		
	}
	public char[][] readMap(int mapnmb)
	{
		return mapObj.readMap(mapnmb);
	}
}
