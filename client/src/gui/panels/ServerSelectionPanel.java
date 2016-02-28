package src.gui.panels;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.LayoutStyle;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import src.gui.panels.event.ControllerSelectionPanel;
import src.network.Connection;
import src.network.PacketBuilder;
import src.resourceManager.Database;

/**
 *
 * @author Richard
 */
public class ServerSelectionPanel extends SuperPanel {
    
    private JLabel text;
    private JTable serverList;
    private JButton join;
    private JButton prev;
    private JPanel container;
	
    public ServerSelectionPanel(int width, int height) {
        super(width, height);
        
        this.controller = new ControllerSelectionPanel(this);
    	container = new JPanel();
        text = new JLabel("Choose a server");
        serverList = new JTable(0,1);
        prev = new JButton("Prev");
        join = new JButton("Join");
        
        container.setBackground(new Color(0, 0, 0, 0));
        
        serverList.setFocusable(false);
        
        prev.setFocusable(false);
        prev.addMouseListener((MouseListener) controller);
        
        join.setFocusable(false);
        join.addMouseListener((MouseListener) controller);
        
        GroupLayout panel1Layout = new GroupLayout(container);
        container.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    	.addComponent(text, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    	.addComponent(serverList, GroupLayout.PREFERRED_SIZE, 360, GroupLayout.PREFERRED_SIZE)
                    	.addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                            	.addComponent(prev, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            	.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                            	.addComponent(join, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        );
        
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panel1Layout.createSequentialGroup()
                    .addGap(51, 51, 51)// Add gap before
                    .addComponent(text, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 10, 10)
                    .addComponent(serverList, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                    //MÅSTE FIXAS!!!
                    .addComponent(prev, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 20, 20)
                    .addComponent(join, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))// Add gap after
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
        
        try {
            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create00RequestLobbyListPacket());
        } catch (IOException ex) {
            Logger.getLogger(ServerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateServerList() {
        final Iterator itr = Database.getInstance().getLobbyNames();
        while (itr.hasNext()) {
            addRow((String) itr.next());
        }
    }
    
    private void addRow(String str) {
        DefaultTableModel model = (DefaultTableModel) serverList.getModel();
        model.addRow(new Object[]{str});
    }
    
    public String getPasswordPane() {
    	return JOptionPane.showInputDialog("Enter password");
    }
    
    public JButton getJoinButton() {
        return join;
    }
    
    public JButton getPrevButton() {
        return prev;
    }
    
    public JTable getServerList() {
        return serverList;
    }
}

	
