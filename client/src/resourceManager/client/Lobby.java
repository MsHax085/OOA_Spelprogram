package src.resourceManager.client;
/**
 * Local instance of a server lobby. Is used by the GUI to: read lobby name, check if lobby has a password and return lobby info as a string.
 * @author Erik Thorsson Högfeldt
 *
 */
public class Lobby {
    private String lobbyName;
    private boolean lobbyHasPassword;
    private int lobbyNumberOfClients;
    
    public Lobby(String lobbyName, boolean lobbyHasPassword, int lobbyNumberOfClients) {
	this.lobbyName = lobbyName;
	this.lobbyHasPassword = lobbyHasPassword;
	this.lobbyNumberOfClients = lobbyNumberOfClients;
    }

    public String getLobbyName() {
	return lobbyName;
    }

    public boolean isLobbyHasPassword() {
	return lobbyHasPassword;
    }
    
    public String getLobbyInfoAsString() {
	String lobbyInfo = lobbyName + (lobbyHasPassword ? ":pass:" : ":    :") + lobbyNumberOfClients;
	return lobbyInfo;
	
    }
}
