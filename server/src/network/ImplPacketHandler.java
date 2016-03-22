package src.network;

/**
 * Defines the structure of a packet handler so it can be used by the PacketProcessor.
 * @author Richard Dahlgren
 * @version 2016-03-02
 */
public interface ImplPacketHandler {

    public void handlePacket(Packet packet);
}
