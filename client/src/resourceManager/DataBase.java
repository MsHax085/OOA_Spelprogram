package src.resourceManager;


/**
 * 
 * @author Oscar Andersson
 *
 * TODO Singleton designPattern??
 */
public class DataBase{
	
	private FileHandler fileObj;
	
	/* Constructs a Score Object assigning its filepath. 
	 */
	public DataBase() {
		fileObj = FileHandler.getInstance();
	}

	


	/* Returns the highscore-list in the form of a treemap
	 * sorting the fastest times first
	 */

	
	public String readFile(String fileN)
	{
		return fileObj.readF(fileN);
	}
	public Boolean writeFile(String fileN,String writeC)
	{
		return fileObj.writeF(fileN,writeC);
	}
	
	
}
