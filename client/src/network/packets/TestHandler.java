package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * Handles the test packet from the server and prints the information send in the packet.
 * @author Erik Thorsson Högfeldt
 * @version 2016-02-26
 */
public class TestHandler implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            System.out.println("Test packet recived with message: " + dis.readInt() + " from server");
        } catch (IOException ex) {
            Logger.getLogger(TestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}