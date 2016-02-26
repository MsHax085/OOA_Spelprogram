package src.network.packets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.lobbyManager.Manager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * NOT COMPLETED
 * @author BögErik
 */
public class Handler00LobbyListRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        System.out.println("00LobbyListRequest recived from client");

        try {
            byte[] newpacket = PacketBuilder.getInstance().create00LobbyListResponsePacket(Manager.getInstance().getAmount(), Manager.getInstance().getList());
            Connection.getInstance().sendPacket(newpacket, packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler00LobbyListRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}