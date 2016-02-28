package src.network.packets;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientLoggedIn;
import src.client.ClientManager;
import src.client.Lobby;
import src.client.LobbyManager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Sends a packet to all clients but the sender. The packet contains the ID of the client who reseted.
 * 
 * @author BögErik
 */
public class Handler07PlayerReset implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
            Iterator clientsInLobby = lobby.getClientsInLobby();
            while (clientsInLobby.hasNext()) {
                byte[] playerResetPacket = PacketBuilder.getInstance().create08ClientMapResetPacket(senderClient.getId());
                ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                if(!cli.equals(senderClient)) {
                    Connection.getInstance().sendPacket(playerResetPacket, cli);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler07PlayerReset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}