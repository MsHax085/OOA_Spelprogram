package src.gui.panels;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.gui.panels.event.ControllerOptionsPanel;

/**
 *
 * @author Richard
 */
public class OptionsPanel extends SuperPanel {

	private JLabel text;
	private JLabel username;
	private JLabel serverIp;
	private JTextField usernameField;
	private JTextField serverIpField;
	private JButton save;
	private JButton prev;
	
    public OptionsPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerOptionsPanel(this);
        
        this.setLayout(null);
        
        text = new JLabel("Options");
		text.setSize(300,10);
		text.setLocation(50,50);
		this.add(text);
		 
		username = new JLabel("Username");
		username.setSize(300,30);
		username.setLocation(50, 100);
		this.add(username);
		 
		usernameField = new JTextField(20);
		usernameField.setSize(300,30);
		usernameField.setLocation(100, 150);
		this.add(usernameField);
		
		serverIp = new JLabel("Server IP Port");
		serverIp.setSize(300,50);
		serverIp.setLocation(50,200);
		this.add(serverIp);
		 
		serverIpField = new JTextField(20);
		serverIpField.setSize(300,30);
		serverIpField.setLocation(100, 250);
		this.add(serverIpField);
		 
		prev = new JButton("Prev");
		prev.setSize(125,50);
		prev.setLocation(175,350);
		prev.addMouseListener((MouseListener) controller);
		this.add(prev);
		 
		save = new JButton("Save");
		save.setSize(125,50);
		save.setLocation(325,350);
		save.addMouseListener((MouseListener) controller);
		this.add(save);
    }

    public JButton getPrevButton() {
        return prev;
    }
    
    public JButton getSaveButton() {
        return save;
    }
}
