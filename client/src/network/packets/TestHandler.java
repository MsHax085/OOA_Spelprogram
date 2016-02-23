package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 *
 * @author BögErik
 */
public class TestHandler implements ImplPacketHandler {
	
    @Override
    public void handlePacket(DataInputStream dis) {
        try {
            // Opcode (first short) already read
            System.out.println("Test packet recived with message: " + dis.readInt() + " from server");
        } catch (IOException ex) {
            Logger.getLogger(TestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
