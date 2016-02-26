package src.client;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class ClientManager {
    
    private static ClientManager instance = null;
    
    private int clientSessionIdIncreament = -1;

    private final ArrayList<ClientSession> clientSessions = new ArrayList<>();
    private final ArrayList<ClientLoggedIn> clientLogIns = new ArrayList<>();
    
    public static ClientManager getInstance() {
        if (instance == null) instance = new ClientManager();
        return instance;
    }
    
    public ClientSession getRegistredClientSession(InetAddress ip, int port) {
        for (ClientSession cs : clientSessions) {
            if (cs.getAddress().getHostAddress().equals(ip.getHostAddress()) &&
                cs.getPort() == port) {
                return cs;
            }
        }
        clientSessionIdIncreament++;
        final ClientSession cs = new ClientSession(clientSessionIdIncreament, ip, port);
        clientSessions.add(cs);
        return cs;
    }
}
