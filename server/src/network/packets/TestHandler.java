package src.network.packets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 *
 * @author Erik Thorsson Högfeldt
 */
public class TestHandler implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            // Opcode (first short) already read
            System.out.println("Test packet recived with message: " + packet.getPacket().readInt() + " from: [" + packet.getSession().getAddress().getHostAddress() + ":" + packet.getSession().getPort() + "]");
            System.out.println("Sending back a test packet.");
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().createTestPacket(), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(TestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
