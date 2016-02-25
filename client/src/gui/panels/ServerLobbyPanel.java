package src.gui.panels;

import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;

import src.gui.panels.event.ControllerServerLobbyPanel;

/**
 *
 * @author Richard
 */
public class ServerLobbyPanel extends SuperPanel {
    
	private JLabel text;
	private JTable lobbyList;
	private JButton start;
	private JButton quit;
	
    public ServerLobbyPanel(int width, int height) {
    	super(width, height);
    	
    	this.controller = new ControllerServerLobbyPanel(this);
    	
    	this.setLayout(null);
        
        text = new JLabel("Joined players");
		text.setSize(300,50);
		text.setLocation(50,50);
		this.add(text);
		 
		lobbyList = new JTable(14,1);
		lobbyList.setSize(400,225);
		lobbyList.setLocation(50, 100);
		this.add(lobbyList);
		
		quit = new JButton("Quit");
		quit.setSize(125,50);
		quit.setLocation(175,350);
		quit.addMouseListener((MouseListener) controller);
		this.add(quit);
		
		start = new JButton("Start");
		start.setSize(125,50);
		start.setLocation(325,350);
		start.addMouseListener((MouseListener) controller);
		this.add(start);
    }
    
    public JButton getQuitButton() {
        return quit;
    }
    
    public JButton getStartButton() {
        return start;
    }
}
