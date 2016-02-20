package src.gui.panels;

import java.awt.Color;
import javax.swing.JButton;
import src.gui.panels.event.ControllerStartPanel;

/**
 *
 * @author Richard
 */
public class StartPanel extends SuperPanel {
    
    private final JButton startButton;
    
    public StartPanel(int width, int height) {
        super(width, height);
        this.controller = new ControllerStartPanel(this);
        this.setBackground(Color.BLACK);
        
        this.startButton = new JButton("Start Game");
        this.startButton.addMouseListener(controller);
        this.add(startButton);
    }
    
    public JButton getStartButton() {
        return startButton;
    }
}
