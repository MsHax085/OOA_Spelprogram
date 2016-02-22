package src.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

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
        
        JLabel text = new JLabel("Choose a server");
		text.setSize(300,10);
	    text.setLocation(50,50);
		this.add(text);
		 
		JLabel serverName = new JLabel("Server name");
		serverName.setSize(300,50);
		serverName.setLocation(50, 100);
		this.add(serverName);
		 
		JTextField name = new JTextField(20);
		name.setSize(300,30);
		name.setLocation(100, 150);
		this.add(serverName);
		 
		JLabel serverPassword = new JLabel("Server password");
		serverPassword.setSize(300,50);
		serverPassword.setLocation(50,200);
		this.add(serverPassword);
		 
		JTextField password = new JTextField(20);
		password.setSize(300,30);
		password.setLocation(100, 250);
		this.add(password);
		 
		JButton prev = new JButton("Prev");
		prev.setSize(125,50);
		prev.setLocation(175,350);
		this.add(prev);
		 
		JButton create = new JButton("Create");
		create.setSize(125,50);
		create.setLocation(325,350);
		this.add(create);
    }
}
