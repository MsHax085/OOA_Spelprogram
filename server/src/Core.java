package src;

import src.network.Network;
import src.network.PacketProcessor;

public class Core{
	
    private static Core instance = null;
	private Network network;
    
    public Core() {
        System.out.println("Core started.");
        PacketProcessor.getInstance().loadRecvHandlers();
        this.network = new Network();
        network.update();
    }
    
    public static Core getInstance() {
        if (instance == null) instance = new Core();
        return instance;
    }
}
