package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * This handles the packet which tells this client if it was able to leave a lobby.
 * @author BögErik
 */
public class Handler0BLeaveLobbyResponse implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int clientLeaveLobbyStatus = dis.readByte();
            if (clientLeaveLobbyStatus == 0){
                System.out.println("This client successfully leaved the lobby");
            } else if (clientLeaveLobbyStatus == 1) {
                System.out.println("Could not leave lobby: lobby didn't exist");
            } else {
                System.out.println("Other clientLeaveLobbyError");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler0BLeaveLobbyResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}