package src.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Richard
 *
 */

public class Network {

    private boolean run = true;
    
    public void update() {
        while (run) {
            try {
                // TODO: Check for array out of bounds
                final DataInputStream dis = Connection.getInstance().receivePacket();
                final short opCode = dis.readShort();
                PacketProcessor.getInstance().getHandler(opCode).handlePacket(dis);
            } catch (IOException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
