package src.network.packets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;
import src.network.Packet;

/**
 *
 * @author Richard, BögErik
 */
public class HelloHandler implements ImplPacketHandler {

    @Override
    public void handlePacket(Packet packet) {
        try {
            // Opcode (first short) already read
            packet.getPacket().readInt();
        } catch (IOException ex) {
            Logger.getLogger(HelloHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
