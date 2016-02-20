package src.network;

import java.io.DataInputStream;

/**
 * 
 * @author Richard Dahlgren
 *
 */
public interface ImplPacketHandler {

    public void handlePacket(DataInputStream dis);
}
