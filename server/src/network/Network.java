package src.network;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
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
                final InetAddress fromIpAddress = Connection.getInstance().getFromIpAddress();
                final int fromPort = Connection.getInstance().getFromPort();
                PacketProcessor.getInstance().getHandler(opCode).handlePacket(dis, fromIpAddress, fromPort);
            } catch (IOException ex) {
                Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
            }
    	}
    }
}
