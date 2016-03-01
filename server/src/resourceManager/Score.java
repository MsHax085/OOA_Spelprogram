package src.resourceManager;


import src.resourceManager.file.FileHandler;
import java.util.*;


/**
 * 
 * @author Oscar Andersson
 */
public class Score {

	private final String highscorePath;
	private final FileHandler fh;
    private static Score instance = null;
	
	public Score() {
		highscorePath = "highscore";
		fh = FileHandler.getInstance();
	}
    public static Score getInstance() {
        if (instance == null) instance = new Score();
        return instance;
    }
	
	/* Returns a treemap of the highscore list.
	 * It has the time in the first value, and
	 * the username in the other.
	 */
	public TreeMap<Integer, String> getScore(int mapNumber)
	{
		TreeMap<Integer, String> scoreMap = 
	             new TreeMap<Integer, String>();
		
		String tempString = fh.readFile(highscorePath+mapNumber+".txt");
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		
		for(int i=0; i < splitRows.length; i++)
		{
			splitCol = splitRows[i].split(",");
			scoreMap.put(Integer.parseInt(splitCol[1]),splitCol[0]);
		}
		return scoreMap;
	}
	
	
	/* Saves a treemap of the highscore list of a specific map.
	 * The treemap has the same "appearance" as in the getScore method.
	 * Returns false if the writeFile method wasn't successful, and true if it was.
	 */

	public Boolean setScore(TreeMap<Integer,String> scoreSave, int mapNumber)
	{	
		StringBuilder tempArr = new StringBuilder(); 
		
	    Set<?> s = scoreSave.entrySet();
	    Iterator<?> it = s.iterator();
	    while(it.hasNext()) {
		       @SuppressWarnings("rawtypes")
			Map.Entry mentry = (Map.Entry)it.next();
		       tempArr.append( mentry.getValue()+ ","+ mentry.getKey()+"\n");
		      }
		return fh.writeFile((highscorePath+mapNumber+".txt"),tempArr.toString());
	    
		
	}
}
