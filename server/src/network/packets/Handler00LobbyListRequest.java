package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.lobbyManager.Manager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.PacketBuilder;

/**
 * NOT COMPLETED
 * @author BögErik
 */
public class Handler00LobbyListRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(DataInputStream dis, InetAddress fromIpAddress, int fromPort) {
        System.out.println("00LobbyListRequest recived from client");

        try {
            byte[] packet = PacketBuilder.getInstance().create00LobbyListResponsePacket(Manager.getInstance().getAmount(), Manager.getInstance().getList());
            Connection.getInstance().sendPacket(packet, fromIpAddress, fromPort);
        } catch (IOException ex) {
            Logger.getLogger(Handler00LobbyListRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}