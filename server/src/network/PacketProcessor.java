package src.network;

import java.util.HashMap;
import src.network.packets.HelloHandler;
import src.network.packets.TestHandler;

/*
 * 
 *  @author Richard, B�gErik
 *  
 *  Links Packet ID with packet classes
 *  TODO: Maybe start a PacketBuilder here so all packets could use it if needed.
 */

public class PacketProcessor {

    private static PacketProcessor packetProcessor;
    private HashMap<Integer, ImplPacketHandler> handlers;
    
    public static PacketProcessor getInstance() {
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
        handlers.put(RecvPacketOpcodes.PACKET1.getValue(), new HelloHandler());
        handlers.put(RecvPacketOpcodes.PACKET2.getValue(), new TestHandler());
    }
}
