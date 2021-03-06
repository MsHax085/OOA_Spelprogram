package src.client;

import java.net.InetAddress;

/**
 * Information about a connected client with a unique ID.
 * @author Richard
 * @version 2016-02-27
 */
public class ClientSession {

    private final int id;
    private final InetAddress ip;
    private final int port;

    public ClientSession(int id, InetAddress ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

    public InetAddress getAddress(){
        return ip;
    }
    
    public int getId() {
        return id;
    }

    public int getPort(){
        return port;
    }
}
