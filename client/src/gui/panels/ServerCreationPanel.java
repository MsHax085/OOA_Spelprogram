package src.gui.panels;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.gui.panels.event.ControllerServerCreationPanel;

/**
 *
 * @author Richard
 */
public class ServerCreationPanel extends SuperPanel {

	private JLabel text;
	private JLabel serverName;
	private JLabel serverPassword;
	private JTextField name;
	private JPasswordField password;
	private JButton create;
	private JButton prev;
	
    public ServerCreationPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerServerCreationPanel(this);
        
        this.setLayout(null);
        
        text = new JLabel("Choose a server");
		text.setSize(300,10);
	    text.setLocation(50,50);
		this.add(text);
		 
		serverName = new JLabel("Server name");
		serverName.setSize(300,50);
		serverName.setLocation(50, 100);
		this.add(serverName);
		 
		name = new JTextField(20);
		name.setSize(300,30);
		name.setLocation(100, 150);
		this.add(name);
		 
		serverPassword = new JLabel("Server password");
		serverPassword.setSize(300,50);
		serverPassword.setLocation(50,200);
		this.add(serverPassword);
		 
		password = new JPasswordField(20);
		password.setSize(300,30);
		password.setLocation(100, 250);
		this.add(password);
		 
		prev = new JButton("Prev");
		prev.setSize(125,50);
		prev.setLocation(175,350);
		prev.addMouseListener((MouseListener) controller);
		this.add(prev);
		 
		create = new JButton("Create");
		create.setSize(125,50);
		create.setLocation(325,350);
		create.addMouseListener((MouseListener) controller);
		this.add(create);
    }
    
    public JButton getPrevButton() {
        return prev;
    }
    
    public JButton getCreateButton() {
        return create;
    }
}

	
