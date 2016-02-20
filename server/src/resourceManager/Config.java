package src.resourceManager;


/**
 * 
 * @author Oscar Andersson
 *
 */
public class Config implements FileHandler{

	private String configPath;
	
	public Config(String configPath) {
		this.configPath = configPath;
	}

	
	/* This method has an inparameter that marks which setting
	 * parameter the method is going to return.
	 * The method splits up the rows and columns.
	 * And then search through the setting columns and returns
	 * the value columns.
	 */
	
	public String readConf(String setting){
		
		String tempString = readF(configPath);
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		
		for(int i=0; i < splitRows.length; i++)
		{
			splitCol = splitRows[i].split(",");
			if(splitCol[0].equals(setting)){
				return splitCol[1];
			}
		}
		
		return null; //Marks that readConfig could not find the setting.
	}
}
