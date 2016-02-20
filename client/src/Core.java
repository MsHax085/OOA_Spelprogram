package src;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Core {
	
    private static Core instance = null;
    
    public Core() {
        System.out.println("Core started.");
    }
    
    public static Core getInstance() {
        if (instance == null) instance = new Core();
        return instance;
    }
}
