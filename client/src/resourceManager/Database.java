package src.resourceManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import src.resourceManager.client.Lobby;
import src.resourceManager.client.ServerClient;

/**
 * Handles thread secure read and write to lists and variables used
 * by several treads.
 *
 * @author Richard
 * @version 2016-2-24
 */
public class Database {

    private static Database database = null;
    
    private String username = "NAME";
    private int id = -1;
    private ArrayList<String> highscoreList = new ArrayList<>();
    
    private ArrayList<Lobby> lobbyList = new ArrayList<>();
    private ArrayList<ServerClient> clientList = new ArrayList<>();

    private final ReentrantReadWriteLock rrwl_username = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_id = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_highscorelist = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_lobbylist = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_serverclientlist = new ReentrantReadWriteLock(true);
    
    public static Database getInstance() {
        if (database == null) database = new Database();
        return database;
    }
    
    /**
     * Adds the lobby to the client side lobby list.
     * @param lobby
     */
    public void addLobby(Lobby lobby) {
        rrwl_lobbylist.writeLock().lock();
        try {
            lobbyList.add(lobby);
        } finally {
            rrwl_lobbylist.writeLock().unlock();
        }
    }
    
    /**
     * Returns an iterator object of the client side lobby list.
     * @return Iterator<Lobby>
     */
    public Iterator getLobbies() {
        rrwl_lobbylist.readLock().lock();
        try {
            return lobbyList.iterator();
        } finally {
            rrwl_lobbylist.readLock().unlock();
        }
    }
    
    /**
     * Adds the client to the client side client list.
     * @param client
     */
    public void addClient(ServerClient client) {
        rrwl_serverclientlist.writeLock().lock();
        try {
            clientList.add(client);
        } finally {
            rrwl_serverclientlist.writeLock().unlock();
        }
    }
    
    /**
     * Returns an iterator object of the client side client list.
     * @return Iterator<ServerClient>
     */
    public Iterator getClients() {
        rrwl_serverclientlist.readLock().lock();
        try {
            return clientList.iterator();
        } finally {
            rrwl_serverclientlist.readLock().unlock();
        }
    }
    
    public Iterator getHighscore() {
        rrwl_highscorelist.readLock().lock();
        try {
            return highscoreList.iterator();
        } finally {
            rrwl_highscorelist.readLock().unlock();
        }
    }
    
    public void addhighscoreString(String highscoreString) {
	rrwl_highscorelist.writeLock().lock();
        try {
            highscoreList.add(highscoreString);
        } finally {
            rrwl_highscorelist.writeLock().unlock();
        }
    }

    public String getUsername() {
        rrwl_username.readLock().lock();
        try {
            return username;
        } finally {
            rrwl_username.readLock().unlock();
        }
    }
    
    public void setUsername(String username) {
        rrwl_username.writeLock().lock();
        try {
            this.username = username;
        } finally {
            rrwl_username.writeLock().unlock();
        }
    }

    public int getId() {
        rrwl_id.readLock().lock();
        try {
            return id;
        } finally {
            rrwl_id.readLock().unlock();
        }
    }
    
    public void setId(int id) {
        rrwl_id.writeLock().lock();
        try {
            this.id = id;
        } finally {
            rrwl_id.writeLock().unlock();
        }
    }
}
