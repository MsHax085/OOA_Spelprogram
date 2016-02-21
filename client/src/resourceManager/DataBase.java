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
	
	/* Constructs a Score Object assigning its filepath. 
	 */
	public DataBase() {
		fileObj = FileHandler.getInstance();
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
