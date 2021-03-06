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
 * Handles a move game entities packet, sends the received information to all clients in the lobby except for the sender.
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-02
 */
public class Handler05MoveGameEntities implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            if (senderClient != null) { // if the client is logged in
        	int playerPositionX = packet.getPacket().readByte();
        	int playerPositionY = packet.getPacket().readByte();
        	int boxPositionX = packet.getPacket().readByte();
        	int boxPositionY = packet.getPacket().readByte();
                Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
                if (lobby == null) return;
                Iterator clientsInLobby = lobby.getClientsInLobby();
                byte[] moveGameEntetiesPacket = PacketBuilder.getInstance().create06MoveGameEntetiesPacket(senderClient.getId(), playerPositionX, playerPositionY, boxPositionX, boxPositionY);
                while (clientsInLobby.hasNext()) {
                    ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                    if(!cli.equals(senderClient)) {
                        Connection.getInstance().sendPacket(moveGameEntetiesPacket, cli);
                    }
                }
            } else {
                // if the client isn't logged in: Send a failed to login packet.
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(-1), packet.getSession());
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler05MoveGameEntities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}