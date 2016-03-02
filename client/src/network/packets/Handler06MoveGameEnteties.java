package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.game.Game;
import src.network.ImplPacketHandler;

/**
 * @author BögErik
 */
public class Handler06MoveGameEnteties implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        System.out.println("06MoveGameEnteties recived from server");
        if (dis == null) return;
        try {
            int clieantId = dis.readInt();
            int playerPositionX = dis.readByte();
            int playerPositionY = dis.readByte();
            int boxPositionX = dis.readByte();
            int boxPositionY = dis.readByte();
            
            Game.getCurrentInstance().UpdateMultiplayer(clieantId, playerPositionX, playerPositionY, boxPositionX, boxPositionY);
        } catch (IOException ex) {
            Logger.getLogger(Handler06MoveGameEnteties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}