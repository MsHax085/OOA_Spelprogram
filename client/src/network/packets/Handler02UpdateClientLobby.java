package src.network.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.Changes;
import src.Core;
import src.network.ImplPacketHandler;
import src.resourceManager.Database;
import src.resourceManager.client.ServerClient;

/**
 * Handles the packet that contains all clients in this clients lobby. (including it self)
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-02
 */
public class Handler02UpdateClientLobby implements ImplPacketHandler {
    
    private DataInputStream dis = null;
	
    @Override
    public void handlePacket() {
        if (dis == null) return;
        try {
            //Clears the current list of clients
            final Iterator itr = Database.getInstance().getClients();
            while (itr.hasNext()) {
        	itr.next();
        	itr.remove();
            }
            
            //Adds the clients to the list
            int numberOfClientsInLobby = dis.readInt();
            for (int i = 0; i < numberOfClientsInLobby; i++) {
        	ServerClient client = new ServerClient(dis.readInt(), dis.readUTF(), dis.readBoolean());
        	Database.getInstance().addClient(client);
                System.out.println(client.getClientInfoAsString());
            }
            Core.getInstance().signalObservers(Changes.CLIENTLIST_CHANGE.getValue());
        } catch (IOException ex) {
            Logger.getLogger(Handler02UpdateClientLobby.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setDataInputStream(DataInputStream dis) {
        this.dis = dis;
    }
}