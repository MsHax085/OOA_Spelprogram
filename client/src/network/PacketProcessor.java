package src.network;

import java.util.HashMap;

import src.network.packets.Handler00LobbyListResponse;
import src.network.packets.Handler01JoinLobbyResponce;
import src.network.packets.Handler02UpdateClientLobby;
import src.network.packets.Handler03CreateLobbyStatus;
import src.network.packets.Handler04StartGame;
import src.network.packets.Handler05HighscoreResponce;
import src.network.packets.Handler06MoveGameEnteties;
import src.network.packets.Handler07PlayerWon;
import src.network.packets.Handler08ClientMapReset;
import src.network.packets.Handler09ClientLoginResponse;
import src.network.packets.Handler0AClientLogoutResponse;
import src.network.packets.Handler0BLeaveLobbyResponse;
import src.network.packets.TestHandler;

/**
 * 
 *  Links Packet ID with packet handler classes.
 * 
 *  @author Richard, BögErik
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
        handlers.put(RecvPacketOpcodes.TESTPACKET.getValue(), new TestHandler());
        handlers.put(RecvPacketOpcodes.PACKET00.getValue(), new Handler00LobbyListResponse());
        handlers.put(RecvPacketOpcodes.PACKET01.getValue(), new Handler01JoinLobbyResponce());
        handlers.put(RecvPacketOpcodes.PACKET02.getValue(), new Handler02UpdateClientLobby());
        handlers.put(RecvPacketOpcodes.PACKET03.getValue(), new Handler03CreateLobbyStatus());
        handlers.put(RecvPacketOpcodes.PACKET04.getValue(), new Handler04StartGame());
        handlers.put(RecvPacketOpcodes.PACKET05.getValue(), new Handler05HighscoreResponce());
        handlers.put(RecvPacketOpcodes.PACKET06.getValue(), new Handler06MoveGameEnteties());
        handlers.put(RecvPacketOpcodes.PACKET07.getValue(), new Handler07PlayerWon());
        handlers.put(RecvPacketOpcodes.PACKET08.getValue(), new Handler08ClientMapReset());
        handlers.put(RecvPacketOpcodes.PACKET09.getValue(), new Handler09ClientLoginResponse());
        handlers.put(RecvPacketOpcodes.PACKET0A.getValue(), new Handler0AClientLogoutResponse());
        handlers.put(RecvPacketOpcodes.PACKET0B.getValue(), new Handler0BLeaveLobbyResponse());
    }
}
