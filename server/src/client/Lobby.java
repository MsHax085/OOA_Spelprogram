package src.client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Richard
 */
public class Lobby {

    private final String lobbyName;
    private final String lobbyPassword;
    private final ArrayList<ClientLoggedIn> clientLogIns = new ArrayList<>();// Logged in clients and registred in lobby
    
    public Lobby(String lobbyName, String lobbyPassword) {
        this.lobbyName = lobbyName;
        this.lobbyPassword = lobbyPassword;
    }
    
    public boolean isClientRegistredInLobby(ClientLoggedIn cli) {
        return clientLogIns.contains(cli);
    }
    
    public void addClientToLobby(ClientLoggedIn cli) {
        clientLogIns.add(cli);
    }
    
    public Iterator getClientsInLobby() {
        return clientLogIns.iterator();
    }
    
    public String getLobbyName() {
        return lobbyName;
    }
    
    public String getLobbyPassword() {
        return lobbyPassword;
    }
    
    public void removeClientFromLobby(ClientLoggedIn cli) {
        clientLogIns.remove(cli);
    }

    public int getNumberOfClients() {
        return clientLogIns.size();
    }
}
