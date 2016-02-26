package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 * NOT COMPLETED
 * @author BögErik
 */
public class Handler00LobbyListResponce implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        System.out.println("00LobbyListResponce recived from server");
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            dis.readInt();
        } catch (IOException ex) {
            Logger.getLogger(Handler00LobbyListResponce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}