package src.network.packets;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientManager;
import src.client.ClientSession;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;

/**
 * Handles a client login, adds the client to the list of logged in clients. Responds with the login status.
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-03
 */
public class Handler08ClientLogin implements ImplPacketHandler {
	
    @Override
    public void handlePacket(Packet packet) {
        try {
            // Opcode (first short) already read
            String username = packet.getPacket().readUTF();
            ClientSession cs = ClientManager.getInstance().getClientSessionByIpAndPort(packet.getSession().getAddress(), packet.getSession().getPort());
            int clientId = -1;
            if (ClientManager.getInstance().getClientByUsername(username) == null) {
                ClientManager.getInstance().registerClientAsLoggedIn(cs, username);
                System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >Client   : " + username + " has logged in");
                clientId = cs.getId();
            } else {
                System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >Client   : " + username + " tried to login with a taken username");
            }
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(clientId), packet.getSession());
        } catch (IOException ex) {
            Logger.getLogger(Handler08ClientLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
