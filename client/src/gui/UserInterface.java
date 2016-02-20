package src.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JLabel;
import src.Core;
import src.frame.DefaultFrameState;
import src.gui.panels.event.Controller;

/**
 *
 * @author Richard
 */
public class UserInterface implements DefaultFrameState, Observer {

    private JFrame frame;
    private Controller controller;
    private JLabel label;// Test

    @Override
    public void setup() {
        frame = new JFrame();
        frame.setTitle("Spelprogram");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.getContentPane().addMouseListener((controller = new Controller()));
        frame.getContentPane().add((label = new JLabel("NAME")));// Test
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
        final byte val = (byte) arg;
        switch (val) {
            case 0:
                label.setText(((Core) o).getUsername());
                break;
            case 1:
                break;
            default:
                break;
        }
    }
}
