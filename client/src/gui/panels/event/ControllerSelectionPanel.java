package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTable;
import src.gui.UserInterface;
import src.gui.panels.ServerSelectionPanel;
import src.network.Connection;
import src.network.PacketBuilder;
import src.network.packets.Handler01JoinLobbyResponce;

public class ControllerSelectionPanel implements MouseListener {
	
    private ServerSelectionPanel panel;
    private JTable serverList;
    private String password;
    private Handler01JoinLobbyResponce h1jlb;
    
    public ControllerSelectionPanel(ServerSelectionPanel panel) {
        this.panel = panel;
        this.serverList = panel.getServerList();
    }
	
    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    	if (e.getSource().equals(panel.getJoinButton())) {
            if(serverList.getSelectedRow() != -1) {
            	// Ska egentligen kolla om det finns något lösenord och om det är "" så ska inte en JOptionPane komma upp! 
            	if(serverList.getValueAt(serverList.getSelectedRow(), 1) == "") {
            		UserInterface.changeCard("serverlobbypanel");
            		try {
                        Connection.getInstance().sendPacket(PacketBuilder.getInstance().create01JoinLobbyPacket("",""));
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            		int i = h1jlb.getJoinLobbyStatus(); 
            		if( i == 0) {
            			UserInterface.changeCard("serverlobbypanel");
            		}
            		else {
            			panel.getCouldNotJoinPane(i);
            		} 
            	} 
            	else {
            		password = panel.getPasswordPane();
            		
            		try {
            			Connection.getInstance().sendPacket(PacketBuilder.getInstance().create01JoinLobbyPacket("",password));
                    } catch (IOException ex) {
                    	Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
            		}
            	int i = h1jlb.getJoinLobbyStatus(); 
            	if( i == 0) {
            		UserInterface.changeCard("serverlobbypanel");
            	}
            	else if(i == 1) {
            		panel.getCouldNotJoinPane(i);
            	} 
            	else {
            		panel.getCouldNotJoinPane(i);
            	}
            }
     	}
            
    	else if (e.getSource().equals(panel.getPrevButton())) {
            UserInterface.changeCard("menupanel");
    	}
    }
}

