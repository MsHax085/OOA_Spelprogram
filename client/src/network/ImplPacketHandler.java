package src.network;

import java.io.DataInputStream;

/**
 * 
 * @author Richard Dahlgren, BögErik
 *
 */
public interface ImplPacketHandler {

    public void handlePacket(DataInputStream dis);
}
