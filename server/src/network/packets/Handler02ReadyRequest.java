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
 * Changes the client who sent the packet, readyToStart to true. Sends out the 
 * current client status of all clients to all clients.
 * @author BögErik
 */
public class Handler02ReadyRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            senderClient.setReadyToStart(true);
            Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
            byte[] updateClientLobbyPacket = PacketBuilder.getInstance().create02UpdateClientLobbyPacket(lobby.getNumberOfClients(), lobby.getClientsInLobby());
            Iterator clientsInLobby = lobby.getClientsInLobby();
            while (clientsInLobby.hasNext()) {
                ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                Connection.getInstance().sendPacket(updateClientLobbyPacket, cli);
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler02ReadyRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}