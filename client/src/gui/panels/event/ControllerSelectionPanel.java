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

public class ControllerSelectionPanel implements MouseListener {
	
    private ServerSelectionPanel panel;
    private JTable serverList;
    private String password;
    
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
            	}
            	else {
            		password = panel.getPasswordPane();
            		UserInterface.changeCard("serverlobbypanel");
            		try {
            			Connection.getInstance().sendPacket(PacketBuilder.getInstance().create01JoinLobbyPacket("",password));
                    } catch (IOException ex) {
                    	Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
            		}
            		else {
            			UserInterface.changeCard("serverSelectionpanel");
            		}
            	}
            }
            
    	else if (e.getSource().equals(panel.getPrevButton())) {
            UserInterface.changeCard("menupanel");
    	}
    }
}
}
