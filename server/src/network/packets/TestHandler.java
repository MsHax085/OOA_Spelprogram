package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.PacketBuilder;

/**
 *
 * @author BögErik
 */
public class TestHandler implements ImplPacketHandler {
	
    @Override
    public void handlePacket(DataInputStream dis, InetAddress fromIpAddress, int fromPort) {
        try {
            // Opcode (first short) already read
            System.out.println("Test packet recived with message: " + dis.readInt() + " from: [" + fromIpAddress.getHostAddress() + ":" + fromPort + "]");
            System.out.println("Sending back a test packet.");
            final PacketBuilder packetBuilder = new PacketBuilder(); // I might make this a singleton, because no more than one packet could be sent at a time anyways.
            Connection.getInstance().sendPacket(packetBuilder.createTestPacket(), fromIpAddress, fromPort);
        } catch (IOException ex) {
            Logger.getLogger(TestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
