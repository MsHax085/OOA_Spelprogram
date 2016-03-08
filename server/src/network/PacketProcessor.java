package src.network;

import java.util.HashMap;
import src.network.packets.Handler00LobbyListRequest;
import src.network.packets.Handler01JoinLobby;
import src.network.packets.Handler02ReadyRequest;
import src.network.packets.Handler03CreateLobby;
import src.network.packets.Handler04HighscoreRequest;
import src.network.packets.Handler05MoveGameEntities;
import src.network.packets.Handler06PlayerWon;
import src.network.packets.Handler07PlayerReset;
import src.network.packets.Handler08ClientLogin;
import src.network.packets.Handler09ClientLogout;
import src.network.packets.Handler0ALeaveLobby;
import src.network.packets.TestHandler;

/**
 * 
 * Gathers the packet handlers so they can easily be created.
 * 
 * @author Richard
 * @version 2016-03-02
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
        handlers.put(RecvPacketOpcodes.TESTPACKET.getValue(), new TestHandler());
        handlers.put(RecvPacketOpcodes.PACKET00.getValue(), new Handler00LobbyListRequest());
        handlers.put(RecvPacketOpcodes.PACKET01.getValue(), new Handler01JoinLobby());
        handlers.put(RecvPacketOpcodes.PACKET02.getValue(), new Handler02ReadyRequest());
        handlers.put(RecvPacketOpcodes.PACKET03.getValue(), new Handler03CreateLobby());
        handlers.put(RecvPacketOpcodes.PACKET04.getValue(), new Handler04HighscoreRequest());
        handlers.put(RecvPacketOpcodes.PACKET05.getValue(), new Handler05MoveGameEntities());
        handlers.put(RecvPacketOpcodes.PACKET06.getValue(), new Handler06PlayerWon());
        handlers.put(RecvPacketOpcodes.PACKET07.getValue(), new Handler07PlayerReset());
        handlers.put(RecvPacketOpcodes.PACKET08.getValue(), new Handler08ClientLogin());
        handlers.put(RecvPacketOpcodes.PACKET09.getValue(), new Handler09ClientLogout());
        handlers.put(RecvPacketOpcodes.PACKET0A.getValue(), new Handler0ALeaveLobby());
    }
}
