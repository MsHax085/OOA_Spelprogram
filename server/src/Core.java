package src;

import src.network.Network;

/**
 * Connects all the parts of the program.
 * 
 * @author Richard
 * @version 2016-02-25
 */

public class Core{
	
    private static Core instance = null;
    private Network network;
    
    public Core() {
        System.out.println("Core started.");
        this.network = new Network();
        network.start();
    }
    
    public static Core getInstance() {
        if (instance == null) instance = new Core();
        return instance;
    }
    
    
}
