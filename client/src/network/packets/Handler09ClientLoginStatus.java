package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * This handles the packet that tells if the server could successfully create the lobby
 * @author BögErik
 */
public class Handler09ClientLoginStatus implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int createLobbyStatus = dis.readByte();
            if (createLobbyStatus == 0) {
                System.out.println("Login Successfull");
            } else if (createLobbyStatus == 1) {
                System.out.println("Login failed: username is taken");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler09ClientLoginStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}