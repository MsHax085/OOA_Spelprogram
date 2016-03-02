package src.network.packets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientLoggedIn;
import src.client.LobbyManager;
import src.lobbyManager.Manager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;
import src.resourceManager.Score;

/**
 * Sends a create00LobbyListResponcePacket to the client who requested all the lobbies.
 * 
 * @author Erik Thorsson Högfeldt
 */
public class Handler04HighscoreRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create05HighscoreResponcePacket(packet.getPacket().readInt()), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler04HighscoreRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}