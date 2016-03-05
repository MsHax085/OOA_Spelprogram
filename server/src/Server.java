package src;

/**
 * Contains the main method, that starts the rest of the program.
 * @author Richard
 * @version 2016-02-29
 */
public class Server {

    public static void main(String[] args) {
        System.out.println("Movin' blocks - server started.");
        Core.getInstance();// Start core
    }
}
