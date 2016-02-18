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
	 * It has the username in the first value, and
	 * the time in the other.
	 */
	public TreeMap<Integer, String> getScore()
	{
		TreeMap<Integer, String> scoreMap = 
	             new TreeMap<Integer, String>();
		
		String tempString = readFile(highscorePath);
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		
		for(int i=0; i < splitRows.length; i++)
		{
			splitCol = splitRows[i].split(",");
			scoreMap.put(Integer.parseInt(splitCol[1]),splitCol[0]);
		}
		return scoreMap;
	}
	
	
	public Boolean setScore(TreeMap<Integer,String> scoreSave)
	{	
		StringBuilder tempArr = new StringBuilder(); 
		
	    Set set = scoreSave.entrySet();
	    Iterator iterator = set.iterator();
	    while(iterator.hasNext()) {
		       Map.Entry mentry = (Map.Entry)iterator.next();
		       tempArr.append( mentry.getValue()+ ","+ mentry.getKey()+"\n");
		      }
		return writeFile(highscorePath,tempArr.toString());
	    
		
	}
}
