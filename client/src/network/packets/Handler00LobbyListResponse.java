package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Changes;
import src.Core;
import src.network.ImplPacketHandler;
import src.resourceManager.Database;
import src.resourceManager.client.Lobby;

/**
 * Reads info about all the lobbies in the server and saves it on this client.
 * TODO: Do something funny with lobbyInfo
 * @author BögErik
 */
public class Handler00LobbyListResponse implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Clears the current list.
            final Iterator itr = Database.getInstance().getLobbies();
            while (itr.hasNext()) {
        	itr.next();
            	itr.remove();
            }
            // Opcode (first short) already read
            int numberOfLobbies = dis.readInt();
            
            // Adds the new list.
            for (int i = 0; i < numberOfLobbies; i++) {
        	Lobby lobby = new Lobby(dis.readUTF(), dis.readBoolean(), dis.readInt());
                Database.getInstance().addLobby(lobby);
                System.out.println(lobby.getLobbyInfoAsString());
            }
            Core.getInstance().signalObservers(Changes.LOBBYLIST_CHANGE.getValue());
        } catch (IOException ex) {
            Logger.getLogger(Handler00LobbyListResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}