package src.network.packets;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;
import src.resourceManager.config.ConfigHandler;

/**
 * Handles a highscore request, responds with a Highscore response packet based on the requested map.
 * 
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-03
 */
public class Handler04HighscoreRequest implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            int mapId = packet.getPacket().readInt();
            if (mapId >= 1 && mapId <= ConfigHandler.getInstance().getNumberOfMaps()) {
        	Connection.getInstance().sendPacket(PacketBuilder.getInstance().create05HighscoreResponcePacket(mapId), packet.getSession());
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler04HighscoreRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}