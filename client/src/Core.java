package src;

import java.util.Observable;
import java.util.Observer;
import src.frame.DefaultFrameState;
import src.game.Game;
import src.network.Network;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Core extends Observable {
	
    private static Core instance = null;
    private DefaultFrameState frameState;
    private final Network network;
    
    public Core() {
        this.setStateObserver(new Game());
        this.network = new Network();
        this.network.start();
    }
    
    public static Core getInstance() {
        if (instance == null) instance = new Core();
        return instance;
    }
    
    private void setupFrameState() {
        this.frameState.setup();
    }
    
    private void viewFrameState() {
        this.frameState.view();
    }
    
    private void hideFrameState() {
        this.frameState.hide();
    }
    
    private void disposeFrameState() {
        this.frameState.dispose();
    }
    
    public final void setStateObserver(DefaultFrameState frameState) {
        if (this.frameState != null) {
            this.deleteObserver((Observer) this.frameState);
            this.frameState.hide();
            this.frameState.dispose();
        }
        
        this.frameState = frameState;
        this.addObserver((Observer) frameState);
        this.setupFrameState();
        this.viewFrameState();
    }
    
    public void signalObservers(int value) {
        this.setChanged();
        this.notifyObservers(value);
    }
    
    public void stop() {
        this.network.stop();
    }
}
