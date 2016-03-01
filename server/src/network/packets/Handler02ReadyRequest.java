package src.network.packets;

import java.io.IOException;
import java.util.Iterator;
import java.util.Random;
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
import src.resourceManager.config.ConfigHandler;

/**
 * Changes the client who sent the packet, readyToStart to true. Sends out the 
 * current client status of all clients to all clients.
 * If everyone is ready: send startGamePacket to all clients.
 * @author BögErik
 */
public class Handler02ReadyRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            if (senderClient != null) { // if the client is logged in
                senderClient.setReadyToStart(true);
                Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
                boolean isLobbyReadyToStart = updateAllClientsInLobby(lobby);
                
                if (isLobbyReadyToStart) {
                    Random rand = new Random();
                    int mapId = rand.nextInt(ConfigHandler.getInstance().getNumberOfMaps()) + 1;
                    lobby.setLobbyCurrentMap(mapId);
                    byte[] startGamePacket = PacketBuilder.getInstance().create04StartGamePacket(mapId);
                    Iterator clientsInLobby = lobby.getClientsInLobby();
                    while (clientsInLobby.hasNext()) {
                	ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                	Connection.getInstance().sendPacket(startGamePacket, cli);
                	cli.setReadyToStart(false);
                    }
                    updateAllClientsInLobby(lobby);
                }
            } else {
                // if the client isn't logged in: Send a failed to login packet.
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(-1), packet.getSession());
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler02ReadyRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * sends a update lobby packet to all clients in the lobby
     * @param lobby
     * @return isLobbyReadyToStart
     * @throws IOException
     */
    private  boolean updateAllClientsInLobby(Lobby lobby) throws IOException{
	boolean isLobbyReadyToStart = true;
	byte[] updateClientLobbyPacket = PacketBuilder.getInstance().create02UpdateClientLobbyPacket(lobby.getNumberOfClients(), lobby.getClientsInLobby());
        Iterator clientsInLobby = lobby.getClientsInLobby();
        while (clientsInLobby.hasNext()) {
            ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
            Connection.getInstance().sendPacket(updateClientLobbyPacket, cli);
            if (!cli.isReadyToStart()) isLobbyReadyToStart = false;
        }
        return isLobbyReadyToStart;
    }
}