package src.resourceManager;

import java.util.TreeMap;

/**
 * 
 * @author Oscar Andersson
 *
 */
public class DataBase implements FileHandler{
	
	public Score scoreObj;

	
	/* Constructs a Score Object
	 */
	public DataBase() {
		scoreObj = new Score();
	}

	


	/* Returns the highscore-list in the form of a treemap
	 * sorting the fastest times first
	 */
	public TreeMap<Integer, String> getHighscore()
	{
		return scoreObj.getScore();
	}

}
