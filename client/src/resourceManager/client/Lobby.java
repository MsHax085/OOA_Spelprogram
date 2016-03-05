package src.resourceManager.client;
/**
 * Local data of a server lobby. This is used to access information about all lobbies on the server.
 * @author Erik Thorsson Högfeldt
 * @version 2016-03-05
 */
public class Lobby {
    private String lobbyName;
    private boolean lobbyHasPassword;
    private int lobbyMapId;
    private int lobbyNumberOfClients;
    private int maxNumberOfClients;
    
    public Lobby(String lobbyName, boolean lobbyHasPassword, int lobbyMapId, int lobbyNumberOfClients, int maxNumberOfClients) {
	this.lobbyName = lobbyName;
	this.lobbyHasPassword = lobbyHasPassword;
	this.lobbyMapId = lobbyMapId;
	this.lobbyNumberOfClients = lobbyNumberOfClients;
	this.maxNumberOfClients = maxNumberOfClients;
    }

    public String getLobbyName() {
	return lobbyName;
    }

    public boolean isLobbyHasPassword() {
	return lobbyHasPassword;
    }
    
    public String getLobbyInfoAsString() {
	String lobbyInfo = lobbyName + (lobbyHasPassword ? ":pass:" : ":    :") + lobbyNumberOfClients + "/" + maxNumberOfClients + (isLobbyPlayingGame() ? ":GAME IN PROGRES" : "");
	return lobbyInfo;
	
    }

    public boolean isLobbyPlayingGame() {
	if (lobbyMapId == 0) {
	    return false;
	}
	return true;
    }
}
