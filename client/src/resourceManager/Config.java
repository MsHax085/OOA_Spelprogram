package src.resourceManager;


/**
 * 
 * @author Oscar Andersson
 *
 */
public class Config{

	public FileHandler fh;
	private String configPath;
	
	public Config(String configPath) {
		this.configPath = configPath;
		fh = FileHandler.getInstance();
	}


	//TODO Config-file based on java.util.prefs
}
