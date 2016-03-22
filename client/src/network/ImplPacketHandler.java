package src.network;

import java.io.DataInputStream;

/**
 * Defines the structure of a packet handler so it can be used by the PacketProccessor.
 * @author Richard Dahlgren
 * @version 2016-03-02
 */
public interface ImplPacketHandler {

    public void handlePacket();
    public void setDataInputStream(DataInputStream dis);
}
