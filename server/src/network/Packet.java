package src.network;

import java.io.DataInputStream;
import src.client.ClientSession;

/**
 * Stores the client session and message of a received packet.
 * @author Richard
 * @version 2016-02-27
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
