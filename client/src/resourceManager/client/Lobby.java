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
    
    public Lobby(String lobbyName, boolean lobbyHasPassword, int lobbyMapId, int lobbyNumberOfClients) {
	this.lobbyName = lobbyName;
	this.lobbyHasPassword = lobbyHasPassword;
	this.lobbyMapId = lobbyMapId;
	this.lobbyNumberOfClients = lobbyNumberOfClients;
    }

    public String getLobbyName() {
	return lobbyName;
    }

    public boolean isLobbyHasPassword() {
	return lobbyHasPassword;
    }
    
    public String getLobbyInfoAsString() {
	String lobbyInfo = lobbyName + (lobbyHasPassword ? ":pass:" : ":    :") + lobbyNumberOfClients + "/5 " + (isLobbyPlayingGame() ? ":GAME IN PROGGRESS" : "");
	return lobbyInfo;
	
    }

    public boolean isLobbyPlayingGame() {
	if (lobbyMapId == 0) {
	    return false;
	}
	return true;
    }
}
