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
 * Sends a packet to all clients but the sender. Containing the senders ID and time of completion.
 * The time is saved on the server for archiving.
 * TODO: Save the times to the high score list.
 * 
 * @author BögErik
 */
public class Handler06PlayerWon implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            if (senderClient != null) {
                int time = packet.getPacket().readInt();
                senderClient.setTimeOfCompletion(time);
                boolean hasFinished = true;
                Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
                Iterator clientsInLobby = lobby.getClientsInLobby();
                while (clientsInLobby.hasNext()) {
                    byte[] playerWonPacket = PacketBuilder.getInstance().create07PlayerWonPacket(senderClient.getId(), time);
                    ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                    if (!cli.equals(senderClient)) {
                        Connection.getInstance().sendPacket(playerWonPacket, cli);
                    }
                    if (cli.getTimeOfCompletion() <= 0) {
                        hasFinished = false;
                    }
                }
                if (hasFinished) {
                    //TODO: Save all the times to the high score file.
                    
                    
                    lobby.setLobbyCurrentMap(0);
                    byte[] updateClientLobbyPacket = PacketBuilder.getInstance().create02UpdateClientLobbyPacket(lobby.getNumberOfClients(), lobby.getClientsInLobby());
                    clientsInLobby = lobby.getClientsInLobby();
                    while (clientsInLobby.hasNext()) {
                        ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                        cli.setTimeOfCompletion(0);
                        Connection.getInstance().sendPacket(updateClientLobbyPacket, cli);
                    }
                }
            } else {
                // if the client isn't logged in: Send a failed to login packet.
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(-1), packet.getSession());
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler06PlayerWon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}