package src.gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;

/**
 *
 * @author Richard
 */
public class UserInterface extends Observable implements Observer {

    private final JFrame frame = new JFrame();
    
    public UserInterface() {
        frame.setTitle("Spelprogram");
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("UserInterface notified!");
    }
}
