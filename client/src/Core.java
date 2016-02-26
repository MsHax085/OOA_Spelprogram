package src;

import java.util.Observable;
import java.util.Observer;
import src.frame.DefaultFrameState;
import src.game.Game;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Core extends Observable {
	
    private static Core instance = null;
    private DefaultFrameState frameState;
    
    private String username = "NAME";
    
    public Core() {
        this.setStateObserver(new Game());
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
            this.frameState.hide();
            this.frameState.dispose();
            this.deleteObserver((Observer) this.frameState);
        }
        
        this.frameState = frameState;
        this.addObserver((Observer) frameState);
        this.setupFrameState();
        this.viewFrameState();
    }
    
    public void changeUsername(String newName) {
        this.username = newName;
        this.setChanged();
        this.notifyObservers((int) 0);
    }
    
    public String getUsername() {
        return username;
    }
}
