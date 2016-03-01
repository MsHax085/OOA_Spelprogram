package src.resourceManager.client;
/**
 * Local instance of client in the same server lobby. This is used to access information about all clients in the currently joined lobby.
 * 
 * @author Erik Thorsson Högfeldt
 *
 */
public class ServerClient {
    private int clientId;
    private String username;
    private boolean readyToStart;
    private int timeOfCompletion = 0;
    
    public ServerClient(int clientId, String username, boolean readyToStart) {
	this.clientId = clientId;
	this.username = username;
	this.readyToStart = readyToStart;
    }

    public int getClientId() {
	return clientId;
    }

    public String getUsername() {
	return username;
    }
    
    public boolean isReadyToStart() {
	return readyToStart;
    }

    public void setReadyToStart(boolean readyToStart) {
	this.readyToStart = readyToStart;
    }

    public int getTimeOfCompletion() {
	return timeOfCompletion;
    }

    public void setTimeOfCompletion(int timeOfCompletion) {
	this.timeOfCompletion = timeOfCompletion;
    }
    
    public String getClientInfoAsString() {
	String clientInfo = username + " ID:" + clientId + (readyToStart ? " : Ready" : " : Not Ready");
	return clientInfo;
    }
}
