package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.game.Game;
import src.network.ImplPacketHandler;

/**
 * This handles the packet that tells this client that another client has resets its map.
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-02
 */
public class Handler08ClientMapReset implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int clientId = dis.readInt();
            System.out.println("Client with ID: " + clientId + " has reseted its map");
            Game.getCurrentInstance().ResetMultiplayer(clientId);
        } catch (IOException ex) {
            Logger.getLogger(Handler08ClientMapReset.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}