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

    private final ArrayList<ClientSession> clientSessions = new ArrayList<>();// Connected clients
    private final ArrayList<ClientLoggedIn> clientLogIns = new ArrayList<>();// Clients logged in
    
    public static ClientManager getInstance() {
        if (instance == null) instance = new ClientManager();
        return instance;
    }
    
    public void deregisterClientSessionByIpAndPort(InetAddress ip, int port) {
        clientSessions.remove(getClientSessionByIpAndPort(ip, port));
    }
    
    public void deregisterClientSessionById(int id) {
        clientSessions.remove(getClientById(id));
    }
    
    public void deregisterClientSession(ClientSession cs) {
        clientSessions.remove(cs);
    }
    
    public void deregisterClientAsLoggedIn(int id) {
        deregisterClientAsLoggedIn((ClientLoggedIn) getClientById(id));
    }
    
    public void deregisterClientAsLoggedIn(String username) {
        ClientLoggedIn cli = getClientByUsername(username);
        if (cli != null) deregisterClientAsLoggedIn(cli);
    }
    
    public void deregisterClientAsLoggedIn(ClientLoggedIn cs) {
        clientLogIns.remove(cs);
    }
    
    public ClientSession getClientById(int id) {
        for (ClientSession cli : clientLogIns) {
            if (cli.getId() == id) return cli;
        }
        return null;
    }
    
    public ClientLoggedIn getClientByUsername(String username) {
        for (ClientLoggedIn cli : clientLogIns) {
            if (cli.getUsername().equals(username)) return cli;
        }
        return null;
    }
    
    public ClientSession getClientSessionByIpAndPort(InetAddress ip, int port) {
        for (ClientSession cs : clientSessions) {
            if (cs.getAddress().getHostAddress().equals(ip.getHostAddress()) &&
                cs.getPort() == port) {
                return cs;
            }
        }
        return null;
    }
    
    public ClientSession getRegistredClientSession(InetAddress ip, int port) {
        ClientSession cs = getClientSessionByIpAndPort(ip, port);
        if (cs != null) return cs;
        
        clientSessionIdIncreament++;
        cs = new ClientSession(clientSessionIdIncreament, ip, port);
        clientSessions.add(cs);
        return cs;
    }
    
    public void registerClientAsLoggedIn(ClientSession cs, String username) {
        ClientLoggedIn cli = new ClientLoggedIn(cs.getId(), username, cs.getAddress(), cs.getPort());
        //ClientLoggedIn cli = (ClientLoggedIn) cs;
        //cli.setUsername(username);
        clientLogIns.add(cli);
    }
}
