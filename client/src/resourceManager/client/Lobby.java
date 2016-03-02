package src.resourceManager.client;
/**
 * Local instance of a server lobby. Is used by the GUI to: read lobby name, check if lobby has a password and return lobby info as a string.
 * @author Erik Thorsson Högfeldt
 *
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
	String lobbyInfo = lobbyName + (lobbyHasPassword ? ":pass:" : ":    :") + lobbyNumberOfClients + "/" + maxNumberOfClients + (isLobbyPlayingGame() ? ":GAME IN PROGGRESS" : "");
	return lobbyInfo;
	
    }

    public boolean isLobbyPlayingGame() {
	if (lobbyMapId == 0) {
	    return false;
	}
	return true;
    }
}
