package src.game;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import src.Core;
import src.gui.UserInterface;

/**
 *
 * @author Richard
 */
public class GameWindowListener implements WindowListener {
    
    private final Game game;
    
    public GameWindowListener(Game game) {
        this.game = game;
    }
    
    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowClosed(WindowEvent e) {
        game.stopGame();
        Core.getInstance().setStateObserver(new UserInterface());
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }
}