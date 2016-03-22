package src;

/**
 * Contains the main method, that creates an instance of Core.
 * @author Richard
 * @version 2016-02-29
 */
public class Server {

    public static void main(String[] args) {
        System.out.println("Movin' blocks - server started.");
        Core.getInstance();// Start core
    }
}
