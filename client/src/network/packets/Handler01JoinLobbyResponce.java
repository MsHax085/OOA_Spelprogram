package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * This handles the packet which tells this client if it was able to enter a lobby.
 * @author BögErik
 */
public class Handler01JoinLobbyResponce implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int joinLobbyStatus = dis.readByte();
            if (joinLobbyStatus == 0){
                System.out.println("This client successfully entered the lobby");
            } else if (joinLobbyStatus == 1) {
                System.out.println("Could not enter lobby: Incorrect password");
            } else if (joinLobbyStatus == 2) {
                System.out.println("Could not enter lobby: Lobby is full");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler01JoinLobbyResponce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}