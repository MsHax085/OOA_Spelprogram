package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * This handles the packet which tells this client if it was able to logout.
 * @author Erik Thorsson Högfeldt
 */
public class Handler0AClientLogoutResponse implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int clientLogoutStatus = dis.readByte();
            if (clientLogoutStatus == 0){
                System.out.println("This client successfully logged out");
            } else if (clientLogoutStatus == 1) {
                System.out.println("Could not logout: this client isn't logged in");
            } else {
                System.out.println("Other logout error");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler0AClientLogoutResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}