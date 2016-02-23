package src.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import src.gui.panels.event.ControllerStartPanel;

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
        
        this.setLayout(null);
        
        JLabel text = new JLabel("Options");
		text.setSize(300,10);
		text.setLocation(50,50);
		this.add(text);
		 
		JLabel username = new JLabel("Username");
		username.setSize(300,30);
		username.setLocation(50, 100);
		this.add(username);
		 
		JTextField usernameField = new JTextField(20);
		usernameField.setSize(300,30);
		usernameField.setLocation(100, 150);
		this.add(usernameField);
		
		JLabel serverIp = new JLabel("Server IP Port");
		serverIp.setSize(300,50);
		serverIp.setLocation(50,200);
		this.add(serverIp);
		 
		JTextField serverIpField = new JTextField(20);
		serverIpField.setSize(300,30);
		serverIpField.setLocation(100, 250);
		this.add(serverIpField);
		 
		JButton prev = new JButton("Prev");
		prev.setSize(125,50);
		prev.setLocation(175,350);
		this.add(prev);
		 
		JButton save = new JButton("Save");
		save.setSize(125,50);
		save.setLocation(325,350);
		this.add(save);
    }

}
