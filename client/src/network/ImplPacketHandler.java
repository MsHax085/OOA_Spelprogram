package src.network;

import java.io.DataInputStream;

/**
 * 
 * @author Richard Dahlgren, Erik Thorsson Högfeldt
 *
 */
public interface ImplPacketHandler {

    public void handlePacket();
    public void setDataInputStream(DataInputStream dis);
}
