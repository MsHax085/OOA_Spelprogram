package src.network;

import java.io.DataInputStream;

/**
 * 
 * @author Richard Dahlgren
 * @version 2016-03-02
 */
public interface ImplPacketHandler {

    public void handlePacket();
    public void setDataInputStream(DataInputStream dis);
}
