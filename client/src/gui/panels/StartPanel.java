package src.gui.panels;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

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
        //this.setBackground(Color.BLACK);
        this.setLayout(null);
        
        this.startButton = new JButton("Ok");
        startButton.setSize(125,50);
        startButton.setLocation(250,300);
		
        this.startButton.addMouseListener(controller);
        this.add(startButton);
        
        JTextField usernameField = new JTextField(20);
        usernameField.setSize(300,30);
        usernameField.setLocation(100, 225);
        this.add(usernameField);
    }
    
    public JButton getStartButton() {
        return startButton;
    }
}
