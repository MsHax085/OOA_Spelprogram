package src.network.packets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientManager;
import src.client.ClientSession;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Adds the client session to the servers client list. Logs the client in with the username given by the client
 * The client is notified if the login was successful or not.
 * @author BögErik
 */
public class Handler08ClientLogin implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            // Opcode (first short) already read
            String username = packet.getPacket().readUTF();
            ClientSession cs = ClientManager.getInstance().getClientSessionByIpAndPort(packet.getSession().getAddress(), packet.getSession().getPort());
            int clientLoginStatus = 1;
            if (ClientManager.getInstance().getClientByUsername(username) == null) {
                ClientManager.getInstance().registerClientAsLoggedIn(cs, username);
                System.out.println(">" + username + " has logged in");
                clientLoginStatus = 0;
            } else {
                System.out.println(">A client tried to login with a taken username");
            }
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginStatusPacket(clientLoginStatus), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler08ClientLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
