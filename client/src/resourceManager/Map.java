package src.resourceManager;


/**
 * 
 * @author Oscar Andersson
 *
 */

public class Map {

	
	private FileHandler fh;
	private Config cO;
	private String mapPath;
	
	public Map(String mapPath) {
		this.mapPath = mapPath;
		fh = FileHandler.getInstance();
		cO = new Config("config.txt");
	}
	
	public char[][] readMap(int mapnumber)
	{
		int width = Integer.parseInt(cO.readConf("gameWidth"));
		int height = Integer.parseInt(cO.readConf("gameHeight"));

		
		String tempString = fh.readF(mapPath+mapnumber+".txt");
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
			return null;
		}
	}
}
	
	
	
	
	
	

