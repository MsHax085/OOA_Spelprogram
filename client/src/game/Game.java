package src.game;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import src.frame.DefaultFrameState;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Game implements DefaultFrameState, Observer {
    
    private JFrame frame;

    @Override
    public void setup() {
        frame = new JFrame();
        frame.setTitle("Spelprogram");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
    }

    @Override
    public void view() {
        frame.setVisible(true);
    }

    @Override
    public void hide() {
        frame.setVisible(false);
    }

    @Override
    public void dispose() {
        // Clear all
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
