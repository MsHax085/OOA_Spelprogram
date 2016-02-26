package src.client;

import java.net.InetAddress;

/**
 *
 * @author Richard
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
