package src.network.packets;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientLoggedIn;
import src.client.ClientManager;
import src.client.ClientSession;
import src.client.LobbyManager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Checks if the client can enter a lobby. if it can the client is added, all clients in the lobby is notified
 * and the client who joined receives a status packet. If it fails the client is told why with a status packet.
 * @author Erik Thorsson Högfeldt
 */
public class Handler03CreateLobby implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            if (senderClient != null) { // if the client is logged in
                // Opcode (first short) already read
                String lobbyName = packet.getPacket().readUTF();
                String password = packet.getPacket().readUTF();
                ClientSession cs = packet.getSession();
                int createLobbyStatus = 1;
            
                if (LobbyManager.getInstance().getLobby(lobbyName) != null) {
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " tried to create a lobby with another lobby's name");
                } else {
                    LobbyManager.getInstance().addLobby(lobbyName, password);
                    LobbyManager.getInstance().getLobby(lobbyName).addClientToLobby((ClientLoggedIn)ClientManager.getInstance().getClientById(cs.getId()));
                    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >ClientID : " + cs.getId() + " has joined lobby: " + lobbyName);
                    createLobbyStatus = 0;
                }
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create03CreateLobbyStatusPacket(createLobbyStatus), cs);
                if (createLobbyStatus == 0) {
                    Connection.getInstance().sendPacket(PacketBuilder.getInstance().create02UpdateClientLobbyPacket(1, LobbyManager.getInstance().getLobby(lobbyName).getClientsInLobby()), cs);
                }
            } else {
                // if the client isn't logged in: Send a failed to login packet.
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(-1), packet.getSession());
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler03CreateLobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
