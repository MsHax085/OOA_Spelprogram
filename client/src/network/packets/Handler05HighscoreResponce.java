package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import src.network.ImplPacketHandler;
import src.resourceManager.Database;
import src.resourceManager.client.Lobby;

/**
 * Reads info about the top 20 highscore of a map. Adds the scores to Database as strings.
 * @author Erik Thorsson Högfeldt
 */
public class Handler05HighscoreResponce implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        System.out.println("05HighscoreResponce recived from server");
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int numberOfHighscores = dis.readInt();
            
            // Adds the new list.
            for (int i = 0; i < numberOfHighscores; i++) {
        	String highscoreElementAsString = Float.toString(((float)dis.readInt()/100)) + " | " + dis.readUTF();
                Database.getInstance().addhighscoreString(highscoreElementAsString);
                System.out.println(highscoreElementAsString);
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler05HighscoreResponce.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}