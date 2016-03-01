package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.Core;
import src.game.Game;
import src.gui.UserInterface;
import src.network.ImplPacketHandler;
import src.resourceManager.Database;
import src.resourceManager.client.ServerClient;

/**
 *
 * @author BögErik
 */
public class Handler04StartGame implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            // Opcode (first short) already read
            int mapId = dis.readInt();
            System.out.println("Server commands the client to start the game with mapID: " + mapId);
            
            ArrayList<ServerClient> lobbyList = new ArrayList<ServerClient>();
            Iterator<ServerClient> iterator = Database.getInstance().getClients();
            while(iterator.hasNext()){
            	ServerClient temp = iterator.next();
            	if(Database.getInstance().getId() != temp.getClientId())
            		lobbyList.add(temp);
            }
            Core.getInstance().setStateObserver(new Game(mapId, lobbyList));
            
        } catch (IOException ex) {
            Logger.getLogger(Handler04StartGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}