package src.resourceManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Richard
 */
public class Database {

    private static Database database = null;
    
    private String username = "NAME";
    private ArrayList<String> lobbyNameList = new ArrayList<>();
    private ArrayList<String> highscoreList = new ArrayList<>();
    private ArrayList<String> clientLobbyList = new ArrayList<>();
    
    private final ReentrantReadWriteLock rrwl_username = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_lobbynamelist = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_highscorelist = new ReentrantReadWriteLock(true);
    private final ReentrantReadWriteLock rrwl_clientlobbylist = new ReentrantReadWriteLock(true);
    
    public static Database getInstance() {
        if (database == null) database = new Database();
        return database;
    }
    
    public void addLobby(String lobbyName) {
        rrwl_lobbynamelist.writeLock().lock();
        try {
            lobbyNameList.add(lobbyName);
        } finally {
            rrwl_lobbynamelist.writeLock().unlock();
        }
    }
    
    public Iterator getLobbyNames() {
        rrwl_lobbynamelist.readLock().lock();
        try {
            return lobbyNameList.iterator();
        } finally {
            rrwl_lobbynamelist.readLock().unlock();
        }
    }
    
    public void addPlayer(String playerName) {
        rrwl_clientlobbylist.writeLock().lock();
        try {
            clientLobbyList.add(playerName);
        } finally {
            rrwl_clientlobbylist.writeLock().unlock();
        }
    }
    
    public Iterator getPlayerNames() {
        rrwl_clientlobbylist.readLock().lock();
        try {
            return clientLobbyList.iterator();
        } finally {
            rrwl_clientlobbylist.readLock().unlock();
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
        rrwl_lobbynamelist.writeLock().lock();
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
}
