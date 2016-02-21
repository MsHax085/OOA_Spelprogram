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
	private Static DataBase instance = null;
	
	/* Constructs a Score Object assigning its filepath. 
	 */
	private DataBase() {
		fileObj = FileHandler.getInstance();
	}
	
	public static DataBase getInstance(){
		if(instance == null)
		{
			instance = new FileHandler();
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
}
