package src.client;

import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Manages all clients that connect to the server
 * @author Richard
 * @version 2016-02-27
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
    
    /**
     * Removes the specified client based on the given Ip and port.
     * @param ip The client IP as an InetAddress
     * @param port The clients port as an int
     */
    public void deregisterClientSessionByIpAndPort(InetAddress ip, int port) {
        clientSessions.remove(getClientSessionByIpAndPort(ip, port));
    }
    
    /**
     * Removes the specified client based on the given ID number.
     * @param id The clients ID
     */
    public void deregisterClientSessionById(int id) {
        clientSessions.remove(getClientById(id));
    }
    
    /**
     * Removes the specified client based on the given client session.
     * @param cs The clients session
     */
    public void deregisterClientSession(ClientSession cs) {
        clientSessions.remove(cs);
    }
    
    /**
     * Removes the specified logged in client based on the given ID number.
     * @param id The clients ID
     */
    public void deregisterClientAsLoggedIn(int id) {
        deregisterClientAsLoggedIn((ClientLoggedIn) getClientById(id));
    }
    
    /**
     * Removes the specified logged in client based on the given username.
     * @param username The clients username
     */
    public void deregisterClientAsLoggedIn(String username) {
        ClientLoggedIn cli = getClientByUsername(username);
        if (cli != null) deregisterClientAsLoggedIn(cli);
    }
    
    /**
     * Removes the specified logged in client based on the given client session.
     * @param cs The clients session
     */
    public void deregisterClientAsLoggedIn(ClientLoggedIn cs) {
        clientLogIns.remove(cs);
    }
    
    /**
     * Returns a client based on the given id, null if it doesn't exist.
     * @param id The client ID
     * @return The client session of a logged in client.
     */
    public ClientSession getClientById(int id) {
        for (ClientSession cli : clientLogIns) {
            if (cli.getId() == id) return cli;
        }
        return null;
    }
    
    /**
     * Returns a client based on the given username, null if it doesn't exist.
     * @param username The clients username
     * @return The client session of a logged in client.
     */
    public ClientLoggedIn getClientByUsername(String username) {
        for (ClientLoggedIn cli : clientLogIns) {
            if (cli.getUsername().equals(username)) return cli;
        }
        return null;
    }
    
    /**
     * Returns a client session based on the given 
     * @param ip
     * @param port
     * @return
     */
    public ClientSession getClientSessionByIpAndPort(InetAddress ip, int port) {
        for (ClientSession cs : clientSessions) {
            if (cs.getAddress().getHostAddress().equals(ip.getHostAddress()) &&
                cs.getPort() == port) {
                return cs;
            }
        }
        return null;
    }
    
    /**
     * Register a new client on the server and return the created client session.
     * @param ip The clients ip as an InetAddress
     * @param port The clients port as an int
     * @return
     */
    public ClientSession getRegistredClientSession(InetAddress ip, int port) {
        ClientSession cs = getClientSessionByIpAndPort(ip, port);
        if (cs != null) return cs;
        
        clientSessionIdIncreament++;
        cs = new ClientSession(clientSessionIdIncreament, ip, port);
        clientSessions.add(cs);
        return cs;
    }
    
    /**
     * Adds the given client session to the list of logged in clients with the given username.
     * @param cs The Clients session
     * @param username The clients username
     */
    public void registerClientAsLoggedIn(ClientSession cs, String username) {
        ClientLoggedIn cli = new ClientLoggedIn(cs.getId(), username, cs.getAddress(), cs.getPort());
        clientLogIns.add(cli);
    }
}
