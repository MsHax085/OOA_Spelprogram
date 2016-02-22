package src;

import src.network.Network;

public class Core{
	
    private static Core instance = null;
	private Network network;
    
    public Core() {
        System.out.println("Core started.");
        this.network = new Network();
        network.update();
    }
    
    public static Core getInstance() {
        if (instance == null) instance = new Core();
        return instance;
    }
    
    
}
