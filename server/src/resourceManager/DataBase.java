package src.resourceManager;

/**
 * 
 * @author Oscar Andersson
 *
 */
public class DataBase {

	public DataBase() {
		// TODO Auto-generated constructor stub
	}

	
	/* Returns a string containing the content
	 * of the file specified in the argument.
	 * 
	 */
	public String getFile(String fileName)
	{
		return FileHandler.readFile(fileName);
	}
	
	
	
	/* Returns true of false depending on how
	 * sucessful the writing was. 
	 */
	public Boolean writeFile(String fileName, String writeContent)
	{
		return FileHandler.writeFile(fileName,writeContent);
	}
}
