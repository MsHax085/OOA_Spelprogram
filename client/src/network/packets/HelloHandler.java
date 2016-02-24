package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;

/**
 *
 * @author Richard
 */
public class HelloHandler implements ImplPacketHandler {
    
    private DataInputStream dis = null;

    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            dis.readInt();
        } catch (IOException ex) {
            Logger.getLogger(HelloHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}
