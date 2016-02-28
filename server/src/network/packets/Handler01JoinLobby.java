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
 * Checks if the client can enter a lobby. if it can the client is added, all clients in the lobby is notified
 * and the client who joined receives a status packet. If it fails the client is told why with a status packet.
 * @author BögErik
 */
public class Handler01JoinLobby implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientSession cs = packet.getSession();
            ClientLoggedIn senderClient = (ClientLoggedIn)ClientManager.getInstance().getClientById(cs.getId());
            if (senderClient != null) { //checks if the client is logged in
                // Opcode (first short) already read
                String password = packet.getPacket().readUTF();
                String lobbyName = packet.getPacket().readUTF();
                Lobby lobby = LobbyManager.getInstance().getLobby(lobbyName);
                int joinLobbyStatus = 3;
                if (lobby == null) {
                    System.out.println(">ClientID: " + cs.getId() + " tried to join nonexisting lobby");
                } else if (lobby.getNumberOfClients() >= 5) { // 5 is the maxNumberOFClients.
                    System.out.println(">ClientID: " + cs.getId() + " tried to enter lobby: " + lobbyName + " but it was full");
                    joinLobbyStatus = 2;
                } else if (!password.equals(lobby.getLobbyPassword())){
                    System.out.println(">ClientID: " + cs.getId() + " tried to enter lobby: " + lobbyName + " with invalid password");
                    joinLobbyStatus = 1;
                } else {
                    lobby.addClientToLobby((ClientLoggedIn)ClientManager.getInstance().getClientById(cs.getId()));
                    System.out.println(">ClintID: " + cs.getId() + " has joined lobby: " + lobbyName);
                    joinLobbyStatus = 0;
                }
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create01JoinLobbyResponcePacket(joinLobbyStatus), cs);
                if (joinLobbyStatus == 0) {
                    byte[] updateClientLobbyPacket = PacketBuilder.getInstance().create02UpdateClientLobbyPacket(lobby.getNumberOfClients(), lobby.getClientsInLobby());
                    Iterator clientsInLobby = lobby.getClientsInLobby();
                    while (clientsInLobby.hasNext()) {
                        ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                        Connection.getInstance().sendPacket(updateClientLobbyPacket, cli);
                    }
                }
            } else {
                //If the client isn't logged in: send a login failed packet instead.
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(-1), cs);
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler01JoinLobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
