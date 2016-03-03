package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.gui.UserInterface;
import src.network.ImplPacketHandler;

/**
 * This handles the packet that tells if the server could successfully create the lobby
 * @author Erik Thorsson Högfeldt
 */
public class Handler03CreateLobbyStatus implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int createLobbyStatus = dis.readByte();
            if (createLobbyStatus == 0) {
                System.out.println("Lobby successfully created");
                UserInterface.changeCard("lobbypanel");
            } else if (createLobbyStatus == 1) {
                System.out.println("Lobby not created: The lobby name is allready in use");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler03CreateLobbyStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}