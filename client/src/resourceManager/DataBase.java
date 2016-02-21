package src.resourceManager;


/**
 * 
 * @author Oscar Andersson
 *
 * TODO Singleton designPattern??
 */
public class DataBase{
	
	private FileHandler fileObj;
	private Config configObj;
	private Map mapObj;
	private static DataBase instance = null;
	
	/* Constructs a Score Object assigning its filepath. 
	 */
	private DataBase() {
		fileObj = FileHandler.getInstance();
		configObj = new Config("config.txt");
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
		return fileObj.readF(fileN);
	}
	public Boolean writeFile(String fileN,String writeC)
	{
		return fileObj.writeF(fileN,writeC);
	}
	
	public String readConfig(String setting){
		return configObj.readConf(setting);
	}

	
	public Boolean writeConfig(String setting, String value){
		return configObj.writeConf(setting,value);
		
	}
	public char[][] readMap(int mapnmb)
	{
		return mapObj.readMap(mapnmb);
	}
}
