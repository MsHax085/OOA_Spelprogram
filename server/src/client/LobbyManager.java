package src.client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Richard
 */
public class LobbyManager {
    
    private static LobbyManager instance = null;
    
    private final ArrayList<Lobby> lobbies = new ArrayList<>();// Registred lobbies
    
    public static LobbyManager getInstance() {
        if (instance == null) instance = new LobbyManager();
        return instance;
    }
    
    public boolean isRegistredLobby(String lobbyName) {
        final Iterator itr = getLobbies();
        while (itr.hasNext()) {
            if (((Lobby) itr.next()).getLobbyName().equals(lobbyName)) return true;
        }
        return false;
    }
    
    public void addLobby(String lobbyName) {
        addLobby(new Lobby(lobbyName));
    }
    
    public void addLobby(Lobby lobby) {
        lobbies.add(lobby);
    }
    
    public Iterator getLobbies() {
        return lobbies.iterator();
    }
    
    public Lobby getLobby(String lobbyName) {
        final Iterator itr = getLobbies();
        while (itr.hasNext()) {
            final Lobby lobby = (Lobby) itr.next();
            if (lobby.getLobbyName().equals(lobbyName)) return lobby;
        }
        return null;
    }
    
    public void removeLobby(String lobbyName) {
        final Lobby lobby = getLobby(lobbyName);
        if (lobby != null) removeLobby(lobby);
    }
    
    public void removeLobby(Lobby lobby) {
        lobbies.remove(lobby);
    }
}
