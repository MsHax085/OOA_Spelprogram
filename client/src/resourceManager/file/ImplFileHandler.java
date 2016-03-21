package src.resourceManager.file;

/**
 * An interface that declares the structure for FileHandler class.
 * @author Richard
 */
public interface ImplFileHandler {
    
    public String readFile(String fileName);
    public Boolean writeFile(String fileName, String writeContent);
}
