package src.gui.panels;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Richard
 */
public class ServerLobbyPanel extends SuperPanel {
    
	private JLabel text;
	private JTable lobbyList;
	private JButton join;
	private JButton quit;
	
    public ServerLobbyPanel(int width, int height) {
    	super(width, height);
    	
    	this.setLayout(null);
        
        JLabel text = new JLabel("Joined players");
		text.setSize(300,50);
		text.setLocation(50,50);
		this.add(text);
		 
		JTable lobbyList = new JTable(14,1);
		lobbyList.setSize(400,225);
		lobbyList.setLocation(50, 100);
		this.add(lobbyList);
		
		JButton quit = new JButton("Quit");
		quit.setSize(125,50);
		quit.setLocation(175,350);
		this.add(quit);
		
		JButton start = new JButton("Start");
		start.setSize(125,50);
		start.setLocation(325,350);
		this.add(start);
    }
}
