package src.resourceManager;


import src.resourceManager.config.ConfigHandler;
import src.resourceManager.file.FileHandler;
import java.util.*;


/**
 * This class manages the storing of highscore information.
 * It can load and save highscore information to highscore files.
 * 
 * @author Oscar Andersson
 * @version 2016-03-03
 */
public class Score {

	private final String highscorePath;
	private final FileHandler fh;
    private static Score instance = null;
	
	private Score() {
		highscorePath = "highscore";
		fh = FileHandler.getInstance();
		for (int i = 1; i <= ConfigHandler.getInstance().getNumberOfMaps(); i++) {
		    if(fh.readFile(highscorePath + i + ".txt")==null){fh.writeFile(highscorePath + i + ".txt","");}
		}
	}
    public static Score getInstance() {
        if (instance == null) instance = new Score();
        return instance;
    }
	
	/**
	 * Returns a treemap of the highscore list for a specific map.
	 * It has the time in the first value, and
	 * the username in the other.
	 * @param mapNumber - specifies which highscore the method is going to get.
	 * @return treemap of the highscore list for a specific map.
	 */
	public TreeMap<Integer, String> getScore(int mapNumber)
	{
		TreeMap<Integer, String> scoreMap = 
	             new TreeMap<Integer, String>();
		
		String tempString = fh.readFile(highscorePath+mapNumber+".txt");
		String[] splitRows = tempString.split("\n");
		String[] splitCol;
		
		try{
		for(int i=0; i < splitRows.length; i++){
			splitCol = splitRows[i].split(",");
			scoreMap.put(Integer.parseInt(splitCol[1]),splitCol[0]);
		}
		} catch(Exception e){
			System.out.println("TreeMap read error");
		}
		return scoreMap;
	}
	
	
	/**
	 * Saves a treemap of the highscore list of a specific map.
	 * The treemap has the same "appearance" as in the getScore method.
	 * @param scoresave - A treemap of the new highscore.
	 * @param mapnumber - specifies which highscore that is going to be set.
	 * @return false if the writeFile method wasn't successful, and true if it was.
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
