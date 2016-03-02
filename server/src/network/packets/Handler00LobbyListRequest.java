package src.network.packets;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.LobbyManager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Sends a create00LobbyListResponcePacket to the client who requested all the lobbies.
 * 
 * @author BögErik
 */
public class Handler00LobbyListRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create00LobbyListResponsePacket(LobbyManager.getInstance().getNumberOfLobbies(), LobbyManager.getInstance().getLobbies()), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler00LobbyListRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}