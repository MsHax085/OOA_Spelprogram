package src.network;

import java.io.DataInputStream;
import src.client.ClientSession;

/**
 *
 * @author Richard
 */
public class Packet {

    private final ClientSession session;
    private final DataInputStream packet;
    
    public Packet(ClientSession session, DataInputStream packet) {
        this.session = session;
        this.packet = packet;
    }

    /**
     * @return the packet
     */
    public DataInputStream getPacket() {
        return packet;
    }

    /**
     * @return the session
     */
    public ClientSession getSession() {
        return session;
    }
}
