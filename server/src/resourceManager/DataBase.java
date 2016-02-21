package src.resourceManager;

import java.util.TreeMap;

/**
 * 
 * @author Oscar Andersson
 *
 */
public class DataBase{
	
	private Score scoreObj;
	private Config confObj;
	private FileHandler fileObj;
	private static DataBase instance = null;
	
	/* Constructs a Score Object assigning its filepath. 
	 */
	public DataBase() {
		scoreObj = new Score("highscore.txt");
		confObj = new Config("config.txt");
		fileObj = FileHandler.getInstance();
	}

	public static DataBase getInstance(){
		if(instance == null)
		{
			instance = new DataBase();
		}
		return instance;
	}
	


	/* Returns the highscore-list in the form of a treemap
	 * sorting the fastest times first
	 */
	public TreeMap<Integer, String> getHighscore()
	{
		return scoreObj.getScore();
	}

	public Boolean setHighscore(TreeMap<Integer, String> saveScore)
	{
		return scoreObj.setScore(saveScore);
	}
	public String readFile(String fileN)
	{
		return fileObj.readF(fileN);
	}
	public Boolean writeFile(String fileN,String writeC)
	{
		return fileObj.writeF(fileN,writeC);
	}
	public String readConfig(String setting)
	{
		return confObj.readConf(setting);
	}
	
}
