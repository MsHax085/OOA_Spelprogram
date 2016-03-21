package src.gui.panels;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

import src.gui.panels.event.ControllerLobbyPanel;
import src.resourceManager.Database;
import src.resourceManager.client.ServerClient;

/**
 * Makes a lobbypanel. Shows the players that are connected to the lobby.
 * Also shows if the players are ready for a new game.
 * 
 * @author Richard, Gustav
 * @version 2016-03-04
 */
public class LobbyPanel extends SuperPanel {
    
    private JLabel text;
    private JTable lobbyList;
    private JButton start;
    private JButton quit;
    private JPanel container;
	
    public LobbyPanel(int width, int height) {
    	super(width, height);
    	
    	this.controller = new ControllerLobbyPanel(this);
    	container = new JPanel();
        text = new JLabel("Players joined");
        lobbyList = new JTable(14,1);
        quit = new JButton("Quit");
        start = new JButton("Start");
        
        container.setBackground(new Color(0, 0, 0, 0));
        
        lobbyList.setFocusable(false);
        updateLobbyList();
        
        quit.setFocusable(false);
        quit.addMouseListener((MouseListener) controller);
        
        start.setFocusable(false);
        start.addMouseListener((MouseListener) controller);
        
        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    	.addComponent(text, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(lobbyList, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    	.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            	.addComponent(quit, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                            	.addComponent(start, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        );
        
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)// Add gap before
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(lobbyList, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 25, 25)
                    .addGroup(panel1Layout.createParallelGroup()
                    .addComponent(quit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addComponent(start, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))// Add gap after
                	.addGap(98, 98, 98)
     	));
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
    }
    
    /**
     * Clears the JTable, adds information about all clients in the lobby.
     */
    public void updateLobbyList() {
	clearRows();
        final Iterator itr = Database.getInstance().getClients();
        while (itr.hasNext()) {
            ServerClient client = (ServerClient) itr.next();
            addRow(client.getClientInfoAsString());
        }
    }
    
    /**
     * Removes all things in the JTable.
     */
    private void clearRows() {
        DefaultTableModel model = (DefaultTableModel) lobbyList.getModel();
        model.setRowCount(0);
    }
    
    private void addRow(String str) {
        DefaultTableModel model = (DefaultTableModel) lobbyList.getModel();
        model.addRow(new Object[]{str});
    }
    
    public JButton getQuitButton() {
        return quit;
    }
    
    public JButton getStartButton() {
        return start;
    }
}
