package src.network;

import java.util.HashMap;
import src.network.packets.HelloHandler;
import src.network.packets.TestHandler;

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
    
    public void loadRecvHandlers() {
        handlers = new HashMap<>();
        handlers.put(RecvPacketOpcodes.PACKET1.getValue(), new HelloHandler());
        handlers.put(RecvPacketOpcodes.PACKET2.getValue(), new TestHandler());
    }
}
