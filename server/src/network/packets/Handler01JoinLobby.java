package src.network.packets;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
import src.resourceManager.config.ConfigHandler;

/**
 * Handles a join lobby packet, adds the logged in client to the lobby and responds with a join lobby status. 
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-03
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
                int joinLobbyStatus = 5;
                if (lobby == null) {
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " tried to join nonexisting lobby");
                } else if (LobbyManager.getInstance().getLobbyByClient(senderClient) != null) {
                    joinLobbyStatus = 4;
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " tried to join lobby while in an lobby");
                } else if (lobby.getLobbyCurrentMap() != 0){
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " tried to join a lobby: " + lobbyName + " but the lobby is in a game");
                    joinLobbyStatus = 3;
                } else if (lobby.getNumberOfClients() >= ConfigHandler.getInstance().getMaxClientsLobby()) {
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " tried to enter lobby: " + lobbyName + " but it was full");
                    joinLobbyStatus = 2;
                } else if (!password.equals(lobby.getLobbyPassword())){
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " tried to enter lobby: " + lobbyName + " with invalid password");
                    joinLobbyStatus = 1;
                } else {
                    lobby.addClientToLobby((ClientLoggedIn)ClientManager.getInstance().getClientById(cs.getId()));
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " has joined lobby: " + lobbyName);
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
