package src.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import src.Core;
import src.frame.DefaultFrameState;
import src.gui.panels.StartPanel;
import src.gui.panels.event.ControllerStartPanel;

/**
 *
 * @author Richard
 */
public class UserInterface implements DefaultFrameState, Observer {

    private JFrame frame;
    private JPanel visiblePanel;

    @Override
    public void setup() {
        frame = new JFrame();
        frame.setTitle("Spelprogram");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        
        this.visiblePanel = new StartPanel(500, 500);
        frame.add(this.visiblePanel);
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
        System.out.println("UserInterface notified!");
        final int val = (int) arg;
        switch (val) {
            case 0:
                // Do something, ex. update username?
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
