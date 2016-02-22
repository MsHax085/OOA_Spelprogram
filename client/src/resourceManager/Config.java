package src.resourceManager;

/**
 * 
 * @author Oscar Andersson
 * TODO Config based on java.util.prefs
 */


public class Config{

	private FileHandler fh;
	private String configPath;
	
	public Config(String configPath) {
		this.configPath = configPath;
		fh = FileHandler.getInstance();
	}

	/* Reads a value related to a specific setting.
	 *
	*/
	public String readConf(String setting){
		String tempString = fh.readF(configPath);
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
	
	/* Overwrites a value related to a specific setting. 
	 * 
	*/
	public Boolean writeConf(String setting, String value)
	{
		StringBuilder tempArr = new StringBuilder(); 
		String tempString = fh.readF(configPath);
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		for(int i=0; i < splitRows.length; i++)
		{
			splitCol = splitRows[i].split(",");
			
			if(splitCol[0].equals(setting)){
				splitCol[1] = value;
				int p;
				for(p=0; p<i ; p++){
					tempArr.append(splitRows[p]);
					tempArr.append("\n");
				}
				tempArr.append(splitCol[0]+","+value);
				tempArr.append("\n");	
				p++; 
				while(p<splitRows.length)
				{
					tempArr.append(splitRows[p]);
					tempArr.append("\n");
					p++;
				}
				fh.writeF("config.txt", tempArr.toString());
				return true;
			}
			
		}
		return false;
	}
	
}
