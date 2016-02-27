package src.client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Richard
 */
public class Lobby {

    private final String lobbyName;
    private final ArrayList<ClientLoggedIn> clientLogIns = new ArrayList<>();// Logged in clients and registred in lobby
    
    public Lobby(String lobbyName) {
        this.lobbyName = lobbyName;
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
    
    public void removeClientFromLobby(ClientLoggedIn cli) {
        clientLogIns.remove(cli);
    }
}
