package src.client;

import java.net.InetAddress;

/**
 *
 * @author Richard
 */
public class ClientLoggedIn extends ClientSession {
    
    private String username = "";
    private boolean readyToStart = false;
    private int timeOfCompletion = 0;

    public ClientLoggedIn(int id, String username, InetAddress ip, int port) {
        super(id, ip, port);
        this.username = username;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setReadyToStart(boolean readyToStart) {
        this.readyToStart = readyToStart;
    }
    
    public boolean isReadyToStart() {
        return readyToStart;
    }

    public int getTimeOfCompletion() {
        return timeOfCompletion;
    }

    public void setTimeOfCompletion(int timeOfCompletion) {
        this.timeOfCompletion = timeOfCompletion;
    }
}
