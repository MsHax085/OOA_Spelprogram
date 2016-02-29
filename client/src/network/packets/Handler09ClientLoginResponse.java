package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.gui.UserInterface;
import src.network.ImplPacketHandler;

/**
 * This handles the packet that tells if the server could successfully login the client.
 * @author BögErik
 */
public class Handler09ClientLoginResponse implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int clientId = dis.readInt();
            if (clientId >= 0) {
                System.out.println("Login Successfull with id: " + clientId);
                UserInterface.changeCard("menupanel");
            } else {
                System.out.println("Login failed: username is taken");
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler09ClientLoginResponse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}