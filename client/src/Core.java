package src;

import src.frame.DefaultFrameState;
import src.game.Game;
import src.gui.UserInterface;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Core {
	
    private static Core instance = null;
    private DefaultFrameState frameState;
    
    public Core() {
        this.setState(new Game());
        this.setupFrameState();
        this.viewFrameState();
    }
    
    public static Core getInstance() {
        if (instance == null) instance = new Core();
        return instance;
    }
    
    private void setState(DefaultFrameState frameState) {
        this.frameState = frameState;
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
}
