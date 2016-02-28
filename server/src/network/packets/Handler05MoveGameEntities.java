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
 * NOT COMPLETED
 * Sends a packet to all clients but the sender.
 * TODO: Figure out how the relevant game entitie's position should be sent in a packet.
 * @author BögErik
 */
public class Handler05MoveGameEntities implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
            Iterator clientsInLobby = lobby.getClientsInLobby();
            while (clientsInLobby.hasNext()) {
                byte[] moveGameEntetiesPacket = PacketBuilder.getInstance().create06MoveGameEntetiesPacket();
                ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                if(!cli.equals(senderClient)) {
                    Connection.getInstance().sendPacket(moveGameEntetiesPacket, cli);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler05MoveGameEntities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}