package src.resourceManager;

/**
 * 
 * @author Oscar Andersson
 *
 */
public class DataBase {
	
	public FileHandler fileHandlerObj;

	
	/* Constructs a FileHandler Object
	 */
	public DataBase() {
		
		fileHandlerObj = new FileHandler();
	}

	
	/* Returns a string containing the content
	 * of the file specified in the argument.
	 * 
	 */
	public String getFile(String fileName)
	{
		return fileHandlerObj.readFile(fileName);
	}
	
	
	
	/* Returns true of false depending on how
	 * sucessful the writing was. 
	 */
	public Boolean writeFile(String fileName, String writeContent)
	{
		return fileHandlerObj.writeFile(fileName,writeContent);
	}
}
