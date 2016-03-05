package src.client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Contains a list of clients that are currently in the lobby and lobby specific variables.
 * @author Richard
 * @version 2016-03-01
 */
public class Lobby {

    private final String lobbyName;
    private final String lobbyPassword;
    private final ArrayList<ClientLoggedIn> clientLogIns = new ArrayList<>();// Logged in clients and registred in lobby
    
    private int lobbyCurrentMap = 0;
    
    public Lobby(String lobbyName, String lobbyPassword) {
        this.lobbyName = lobbyName;
        this.lobbyPassword = lobbyPassword;
    }
    
    /**
     * Checks if the client is in the lobby.
     * @param cli As a ClientLoggedIn
     * @return true, if the client is in the lobby. false, if it isn't.
     */
    public boolean isClientRegistredInLobby(ClientLoggedIn cli) {
        return clientLogIns.contains(cli);
    }
    
    /**
     * Adds a client to the lobby
     * @param cli As a ClientLoggedIn
     */
    public void addClientToLobby(ClientLoggedIn cli) {
        clientLogIns.add(cli);
    }
    
    /**
     * Returns client list.
     * @return The list of clients as an Iterator object
     */
    public Iterator getClientsInLobby() {
        return clientLogIns.iterator();
    }
    
    public String getLobbyName() {
        return lobbyName;
    }
    
    public String getLobbyPassword() {
        return lobbyPassword;
    }
    
    public int getLobbyCurrentMap() {
        return lobbyCurrentMap;
    }
    
    public void setLobbyCurrentMap(int lobbyCurrentMap) {
        this.lobbyCurrentMap = lobbyCurrentMap;
    }
    
    /**
     * Removes a logged in client from the client list in theis lobby.
     * @param cli As a ClientLoggedIn
     */
    public void removeClientFromLobby(ClientLoggedIn cli) {
        clientLogIns.remove(cli);
    }

    public int getNumberOfClients() {
        return clientLogIns.size();
    }
}
