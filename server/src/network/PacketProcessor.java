package src.network;

import java.util.HashMap;
import src.network.packets.Handler00LobbyListRequest;
import src.network.packets.HelloHandler;
import src.network.packets.TestHandler;

/**
 * 
 * Links Packet ID with packet classes
 * 
 * @author Richard, BögErik
 *  
 */

public class PacketProcessor {

    private static PacketProcessor packetProcessor;
    private HashMap<Integer, ImplPacketHandler> handlers;
    
    public static synchronized PacketProcessor getInstance() {
        if (packetProcessor == null) packetProcessor = new PacketProcessor();
        return packetProcessor;
    }
    
    public ImplPacketHandler getHandler(int opCode) {
        return handlers.get(opCode);
    }
    
    /*
     * Add new packets to the HashMap here..
     */
    public void loadRecvHandlers() {
        handlers = new HashMap<>();
        handlers.put(RecvPacketOpcodes.PACKET00.getValue(), new Handler00LobbyListRequest());
        handlers.put(RecvPacketOpcodes.PACKET1.getValue(), new HelloHandler());
        handlers.put(RecvPacketOpcodes.PACKET2.getValue(), new TestHandler());
    }
}
