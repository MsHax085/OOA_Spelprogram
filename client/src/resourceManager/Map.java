package src.resourceManager;

import src.resourceManager.config.ConfigHandler;
import src.resourceManager.file.FileHandler;


/**
 * 
 * @author Oscar Andersson
 *
 */

public class Map {

	
	private FileHandler fh;
	private ConfigHandler cO;
	private String mapPath;
	
	public Map(String mapPath) {
		this.mapPath = mapPath;
		fh = FileHandler.getInstance();
		cO = new ConfigHandler("config.txt");
	}
	
	
	/* This method read the content of a map file and return the map
	 * as a char[][].
	 *
	 */
	public char[][] readMap(int mapnumber)
	{
		int width = Integer.parseInt(cO.readConfig("gameWidth"));
		int height = Integer.parseInt(cO.readConfig("gameHeight"));

		
		String tempString = fh.readFile(mapPath+mapnumber+".txt");
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		char[][] builder = new char[height][width];
		
		try{
		for(int i=0; i < splitRows.length; i++)
		{
			splitCol = splitRows[i].split(",");
			for(int p=0; p < splitCol.length;p++)
			{
				builder[i][p] = splitCol[p].charAt(0);
			}
			
		}
		return builder;
		}
		catch(Exception e){
			System.out.println("Error reading map");
			return null; // Return null if the map "building" wasnt successful. 
		}
	}
}
	
	
	
	
	
	

