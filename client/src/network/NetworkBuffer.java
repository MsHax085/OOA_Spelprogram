package src.network;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import src.Changes;

/**
 *
 * @author Richard
 */
public class NetworkBuffer extends Observable {

    private static NetworkBuffer networkBuffer = null;
    private final LinkedList<ImplPacketHandler> handlerBuffer = new LinkedList<>();
    private final static ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock(true);
    
    public static NetworkBuffer getInstance() {
        rrwl.readLock().lock();
        try {
            if (networkBuffer == null) networkBuffer = new NetworkBuffer();
            return networkBuffer;
        } finally {
            rrwl.readLock().unlock();
        }
    }
    
    public void addHandler(ImplPacketHandler handler) {
        rrwl.writeLock().lock();
        try {
            handlerBuffer.addLast(handler);
            setChanged();
            notifyObservers(Changes.PACKET_RECEIVED.getValue());
        } finally {
            rrwl.writeLock().unlock();
        }
    }
    
    public ImplPacketHandler getNext() {
        rrwl.writeLock().lock();
        try {
            if (!hasNext()) return null;
            return handlerBuffer.pollFirst();
        } finally {
            rrwl.writeLock().unlock();
        }
    }
    
    public boolean hasNext() {
        rrwl.readLock().lock();
        try {
            return !handlerBuffer.isEmpty();
        } finally {
            rrwl.readLock().unlock();
        }
    }
    
    public void addNewObserver(Observer observer) {
        rrwl.writeLock().lock();
        try {
            addObserver(observer);
        } finally {
            rrwl.writeLock().unlock();
        }
    }
    
    public void removeOldObserver(Observer observer) {
        rrwl.writeLock().lock();
        try {
            deleteObserver(observer);
        } finally {
            rrwl.writeLock().unlock();
        }
    }
}
