package src.network.packets;

import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientLoggedIn;
import src.client.ClientManager;
import src.client.ClientSession;
import src.client.Lobby;
import src.client.LobbyManager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Checks if the client can leave a lobby. if it can the client is removed, all clients in the lobby is notified
 * and the client who left receives a status packet. If it fails the client is told why with the status packet.
 * @author BögErik
 */
public class Handler0ALeaveLobby implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            // Opcode (first short) already read
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            int clientLeaveLobbyStatus = 2;
            if (senderClient != null) {
                Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
                if (lobby != null) {
                    lobby.removeClientFromLobby(senderClient);
                    System.out.println(">Client:" + senderClient.getUsername() + " left lobby:" + lobby.getLobbyName());
                    if (lobby.getNumberOfClients() > 0){
                        byte[] updateClientLobbyPacket = PacketBuilder.getInstance().create02UpdateClientLobbyPacket(lobby.getNumberOfClients(), lobby.getClientsInLobby());
                        Iterator clientsInLobby = lobby.getClientsInLobby();
                        while (clientsInLobby.hasNext()) {
                            ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                            if (!cli.equals(senderClient)) {
                                Connection.getInstance().sendPacket(updateClientLobbyPacket, cli);
                            }
                        }
                    } else {
                        LobbyManager.getInstance().removeLobby(lobby);
                    }
                    clientLeaveLobbyStatus = 0;
                } else {
                    clientLeaveLobbyStatus = 1;
                }
            }
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create0BLeaveLobbyResponsePacket(clientLeaveLobbyStatus), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler0ALeaveLobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
