package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.Changes;
import src.Core;
import src.network.ImplPacketHandler;
import src.resourceManager.Database;

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
            // Opcode (first short) already read
            int numberOfLobbies = dis.readInt();
            for (int i = 0; i < numberOfLobbies; i++) {
                String lobbyName = dis.readUTF();
                String lobbyInfo = lobbyName + ":" + ((dis.readBoolean()) ? "Pass:" : "    :") + dis.readInt() + "/5";
                Database.getInstance().addLobby(lobbyName);
                System.out.println(lobbyInfo);
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