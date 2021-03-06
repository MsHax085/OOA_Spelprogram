package src.network.packets;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.client.ClientLoggedIn;
import src.client.ClientManager;
import src.client.Lobby;
import src.client.LobbyManager;
import src.network.Connection;
import src.network.ImplPacketHandler;
import src.network.Packet;
import src.network.PacketBuilder;
import src.resourceManager.Score;

/**
 * Handles a player won packet, if everyone is done save the score and end the game. Send the information to all other clients in the lobby.
 * 
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-03
 */
public class Handler06PlayerWon implements ImplPacketHandler {
    
    @Override
    public void handlePacket(Packet packet) {
        try {
            ClientLoggedIn senderClient = (ClientLoggedIn) ClientManager.getInstance().getClientById(packet.getSession().getId());
            if (senderClient != null) {
                int time = packet.getPacket().readInt();
                senderClient.setTimeOfCompletion(time);
                boolean hasFinished = true;
                Lobby lobby = LobbyManager.getInstance().getLobbyByClient(senderClient);
                if (lobby == null) return;
                Iterator clientsInLobby = lobby.getClientsInLobby();
                while (clientsInLobby.hasNext()) {
                    byte[] playerWonPacket = PacketBuilder.getInstance().create07PlayerWonPacket(senderClient.getId(), time);
                    ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                    if (!cli.equals(senderClient)) {
                        Connection.getInstance().sendPacket(playerWonPacket, cli);
                    }
                    if (cli.getTimeOfCompletion() <= 0) {
                        hasFinished = false;
                    }
                }
                if (hasFinished) {
                    TreeMap<Integer, String> scoreList = Score.getInstance().getScore(lobby.getLobbyCurrentMap());
                    TreeMap<Integer, String> tempScoreList = new TreeMap<Integer, String>(scoreList);
                    clientsInLobby = lobby.getClientsInLobby();
                    while(clientsInLobby.hasNext()) {
                	ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                	boolean isClientInScore = false;
                	for (Map.Entry<Integer, String> entry : scoreList.entrySet()) {
                	    Integer scoreTime = entry.getKey();
                	    String scoreUsername = entry.getValue();
                	    if (cli.getUsername().equals(scoreUsername)) {
                		isClientInScore = true;
                		if (cli.getTimeOfCompletion() <= scoreTime) {
                		    tempScoreList.remove(scoreTime, scoreUsername);
                		    tempScoreList.put(cli.getTimeOfCompletion(), cli.getUsername());
                	    	}
                	    }
                	}
                	if (!isClientInScore) {
                	    tempScoreList.put(cli.getTimeOfCompletion(), cli.getUsername());
                	    System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) + " >Client   : " + cli.getUsername() + " was added to the highscore list");
                	}
                    }
                    Score.getInstance().setScore(tempScoreList, lobby.getLobbyCurrentMap());
                    
                    
                    lobby.setLobbyCurrentMap(0);
                    byte[] updateClientLobbyPacket = PacketBuilder.getInstance().create02UpdateClientLobbyPacket(lobby.getNumberOfClients(), lobby.getClientsInLobby());
                    clientsInLobby = lobby.getClientsInLobby();
                    while (clientsInLobby.hasNext()) {
                        ClientLoggedIn cli = (ClientLoggedIn) clientsInLobby.next();
                        cli.setTimeOfCompletion(0);
                        Connection.getInstance().sendPacket(updateClientLobbyPacket, cli);
                    }
                }
            } else {
                // if the client isn't logged in: Send a failed to login packet.
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create09ClientLoginResponsePacket(-1), packet.getSession());
            }
        } catch (IOException ex) {
            Logger.getLogger(Handler06PlayerWon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}