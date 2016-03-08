package src;

/**
 * Main class, builds a new instance of core.
 * @author Richard
 * @version 2016-02-29
 */
public class Client {
	
    public static void main(String[] args) {
        System.out.println("Movin' blocks - client started.");
        Core.getInstance();// Start core
    }
}
