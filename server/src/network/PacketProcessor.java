package src.network;

import java.util.HashMap;
import src.network.packets.Handler00LobbyListRequest;
import src.network.packets.Handler01JoinLobby;
import src.network.packets.Handler03CreateLobby;
import src.network.packets.Handler08ClientLogin;
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
        handlers.put(RecvPacketOpcodes.PACKET01.getValue(), new Handler01JoinLobby());
        handlers.put(RecvPacketOpcodes.PACKET03.getValue(), new Handler03CreateLobby());
        handlers.put(RecvPacketOpcodes.TESTPACKET.getValue(), new TestHandler());
        handlers.put(RecvPacketOpcodes.PACKET08.getValue(), new Handler08ClientLogin());
    }
}
