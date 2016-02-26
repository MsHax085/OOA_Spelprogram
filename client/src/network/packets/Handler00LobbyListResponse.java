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
 * NOT COMPLETED
 * @author BögErik
 */
public class Handler00LobbyListResponse implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        System.out.println("00LobbyListResponse recived from server");
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int lobbys = dis.readInt();
            for (int i = 0; i < lobbys; i++) {
                Database.getInstance().addLobby(dis.readUTF());
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