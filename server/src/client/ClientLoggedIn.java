package src.client;

import java.net.InetAddress;

/**
 *
 * @author Richard
 */
public class ClientLoggedIn extends ClientSession {
    
    private String username = "";

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
}
