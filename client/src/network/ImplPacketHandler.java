package src.network;

import java.io.DataInputStream;

/**
 * 
 * @author Richard Dahlgren, B�gErik
 *
 */
public interface ImplPacketHandler {

    public void handlePacket(DataInputStream dis);
}
