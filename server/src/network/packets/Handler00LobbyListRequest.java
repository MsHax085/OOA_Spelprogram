package src.network.packets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.LobbyManager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Handles a lobby list request, sends back a lobby list response packet. 
 * 
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-02
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