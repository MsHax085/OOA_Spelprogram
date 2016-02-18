package src.resourceManager;

import java.util.TreeMap;

/**
 * 
 * @author Oscar Andersson
 *
 * TODO Singleton designPattern
 */
public class DataBase implements FileHandler{
	
	private Score scoreObj;

	
	/* Constructs a Score Object assigning its filepath. 
	 */
	public DataBase() {
		scoreObj = new Score("highscore.txt");
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
}
