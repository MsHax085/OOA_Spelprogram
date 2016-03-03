package src.network.packets;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
 * Logs this client out of the server. If this client is in a lobby he is 
 * automatically removed from it.
 * @author Erik Thorsson Högfeldt
 */
public class Handler09ClientLogout implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            // Opcode (first short) already read
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            int clientLogoutStatus = 2;
            if (senderClient == null) {
                clientLogoutStatus = 1;
            } else {
                Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
                if (lobby != null) {
                    lobby.removeClientFromLobby(senderClient);
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
                }
                ClientManager.getInstance().deregisterClientAsLoggedIn(senderClient);;
                clientLogoutStatus = 0;
                System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >Client   : " + senderClient.getUsername() + " logged out");
            }
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create0AClientLogoutResponsePacket(clientLogoutStatus), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler09ClientLogout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
