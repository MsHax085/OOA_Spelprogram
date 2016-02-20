package src.resourceManager;

import java.util.*;


/**
 * 
 * @author Oscar Andersson
 */
public class Score implements FileHandler {

	private String highscorePath;
	
	public Score(String highscorePath) {
		this.highscorePath = highscorePath;
	}

	
	
	/* Returns a treemap of the highscore list.
	 * It has the time in the first value, and
	 * the username in the other.
	 */
	public TreeMap<Integer, String> getScore()
	{
		TreeMap<Integer, String> scoreMap = 
	             new TreeMap<Integer, String>();
		
		String tempString = readF(highscorePath);
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		
		for(int i=0; i < splitRows.length; i++)
		{
			splitCol = splitRows[i].split(",");
			scoreMap.put(Integer.parseInt(splitCol[1]),splitCol[0]);
		}
		return scoreMap;
	}
	
	
	/* Saves a treemap of the highscore list.
	 * The treemap has the same "appearance" as in the getScore method.
	 * Returns false if the writeFile method wasn't successful, and true if it was.
	 */
	public Boolean setScore(TreeMap<Integer,String> scoreSave)
	{	
		StringBuilder tempArr = new StringBuilder(); 
		
	    Set s = scoreSave.entrySet();
	    Iterator it = s.iterator();
	    while(it.hasNext()) {
		       Map.Entry mentry = (Map.Entry)it.next();
		       tempArr.append( mentry.getValue()+ ","+ mentry.getKey()+"\n");
		      }
		return writeF(highscorePath,tempArr.toString());
	    
		
	}
}
