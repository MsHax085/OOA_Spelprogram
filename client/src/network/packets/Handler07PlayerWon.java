package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.game.Game;
import src.network.ImplPacketHandler;

/**
 * This handles the packet that tells the client if and when another client cleared the map.
 * @author BögErik
 */
public class Handler07PlayerWon implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int clientId = dis.readInt();
            int timeOfCompletion = dis.readInt();
            System.out.println("Client with ID: " + clientId + " cleared the map at time: " + timeOfCompletion);
            Game.getCurrentInstance().SetDoneMultiplayer(clientId, timeOfCompletion);
        } catch (IOException ex) {
            Logger.getLogger(Handler07PlayerWon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}