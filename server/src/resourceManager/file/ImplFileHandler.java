package src.resourceManager.file;

/**
 *
 * @author Richard
 */
public interface ImplFileHandler {
    
    public String readFile(String fileName);
    public Boolean writeFile(String fileName, String writeContent);
}
