package src.network;

import java.io.DataInputStream;
import java.net.InetAddress;

/**
 * 
 * @author Richard Dahlgren, B�gErik
 *
 */
public interface ImplPacketHandler {

    public void handlePacket(DataInputStream dis, InetAddress fromIpAddress, int fromPort);
}
