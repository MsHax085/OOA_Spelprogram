package src.gui.panels;

import java.awt.Color;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.LayoutStyle;
import javax.swing.JPanel;

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
    private JPanel container;
	
    public ServerLobbyPanel(int width, int height) {
    	super(width, height);
    	
    	this.controller = new ControllerServerLobbyPanel(this);
    	container = new JPanel();
        text = new JLabel("Joined players");
        lobbyList = new JTable(14,1);
        quit = new JButton("Quit");
        start = new JButton("Start");
        
        container.setBackground(new Color(0, 0, 0, 0));
        
        lobbyList.setFocusable(false);
        
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
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                    //MÅSTE FIXAS!!!
                    .addComponent(quit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                    .addComponent(start, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))// Add gap after
                	.addGap(98, 98, 98)
     	);
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(80, 80, 80))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(container, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    
    public JButton getQuitButton() {
        return quit;
    }
    
    public JButton getStartButton() {
        return start;
    }
}
