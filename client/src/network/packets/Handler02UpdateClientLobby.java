package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * NOT COMPLETED
 * Retrieves all clients in this clients lobby. (including it self)
 * TODO: Save the clients so they can be accessed when needed.
 * @author BögErik
 */
public class Handler02UpdateClientLobby implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        System.out.println("02UpdateClientLobby recived from server");
        if (dis == null) return;
        try {
            int numberOfClientsInLobby = dis.readInt();
            for (int i = 0; i < numberOfClientsInLobby; i++) {
                String username = dis.readUTF();
                int clientId = dis.readInt();
                int clientReadyToStart = dis.readByte();
                System.out.println("Client: " + username + " with ID: " + clientId + " is ready: " + clientReadyToStart);
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler02UpdateClientLobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}