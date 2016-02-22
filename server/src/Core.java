package src;

import src.network.Network;

/**
 * 
 * @author 
 * 
 * TODO: Load settings and shit from DataBase, Add a class to read commands from a CLI.
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
