package src.game;

import javax.swing.JFrame;
import src.frame.DefaultFrameState;

/**
 * 
 * @author ludwigfriborg
 *
 */
public class Game implements DefaultFrameState {
    
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
}
