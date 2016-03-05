package src.client;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Manages all the lobbies on the server.
 * @author Richard
 * @version 2016-03-02
 */
public class LobbyManager {
    
    private static LobbyManager instance = null;
    
    private final ArrayList<Lobby> lobbies = new ArrayList<>();// Registred lobbies
    
    public static LobbyManager getInstance() {
        if (instance == null) instance = new LobbyManager();
        return instance;
    }
    
    /**
     * Checks if a lobby exist based on the given name.
     * @param lobbyName As a string.
     * @return true, if the lobby did exist. false if it didn't.
     */
    public boolean isRegistredLobby(String lobbyName) {
        final Iterator itr = getLobbies();
        while (itr.hasNext()) {
            final Lobby lobby = (Lobby) itr.next();
            if (lobby.getLobbyName().equals(lobbyName)) return true;
        }
        return false;
    }
    
    /**
     * Creates a new lobby with the given parameters and adds it to the list of lobbies.
     * @param lobbyName As a string.
     * @param password Lobby password as a string.
     */
    public void addLobby(String lobbyName, String password) {
        addLobby(new Lobby(lobbyName, password));
    }
    
    /**
     * Adds a created Lobby object to the list of lobbies.
     * @param lobby
     */
    public void addLobby(Lobby lobby) {
        lobbies.add(lobby);
    }
    
    /**
     * Return lobby list.
     * @return The list of lobbies as an Iterator objects.
     */
    public Iterator getLobbies() {
        return lobbies.iterator();
    }
    
    public int getNumberOfLobbies() {
        return lobbies.size();
    }
    
    public Lobby getLobby(String lobbyName) {
        final Iterator itr = getLobbies();
        while (itr.hasNext()) {
            final Lobby lobby = (Lobby) itr.next();
            if (lobby.getLobbyName().equals(lobbyName)) return lobby;
        }
        return null;
    }
    
    public Lobby getLobbyByClient(ClientLoggedIn cli) {
        final Iterator itr = getLobbies();
        while (itr.hasNext()) {
            final Lobby lobby = (Lobby) itr.next();
            if (lobby.isClientRegistredInLobby(cli)) return lobby;
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
