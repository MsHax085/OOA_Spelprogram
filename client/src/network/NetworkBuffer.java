package src.network;

import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Richard
 */
public class NetworkBuffer extends Observable {

    private static NetworkBuffer networkBuffer = null;
    private final LinkedList<ImplPacketHandler> handlerBuffer = new LinkedList<>();
    
    public static synchronized NetworkBuffer getInstance() {
        if (networkBuffer == null) networkBuffer = new NetworkBuffer();
        return networkBuffer;
    }
    
    public void addHandler(ImplPacketHandler handler) {
        handlerBuffer.addLast(handler);
        setChanged();
        notifyObservers(0);
    }
    
    public ImplPacketHandler getNext() {
        if (!hasNext()) return null;
        return handlerBuffer.pollFirst();
    }
    
    public boolean hasNext() {
        return !handlerBuffer.isEmpty();
    }
    
    public void addNewObserver(Observer observer) {
        addObserver(observer);
    }
    
    public void removeOldObserver(Observer observer) {
        deleteObserver(observer);
    }
}
