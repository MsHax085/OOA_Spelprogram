package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.gui.UserInterface;
import src.gui.panels.ServerSelectionPanel;
import src.network.Connection;
import src.network.PacketBuilder;
import src.network.packets.Handler01JoinLobbyResponce;
import src.resourceManager.Database;
import src.resourceManager.client.Lobby;

/*
 * @author Gustav
 */
public class ControllerSelectionPanel implements MouseListener {
	
    private ServerSelectionPanel panel;
    private String password;
    private Handler01JoinLobbyResponce h1jlb;
    
    public ControllerSelectionPanel(ServerSelectionPanel panel) {
        this.panel = panel;
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
            if(panel.getServerList().getSelectedRow() != -1) {
            	// Ska egentligen kolla om det finns något lösenord och om det är "" så ska inte en JOptionPane komma upp! 
        	
        	// (Eriks lilla Kod) This gets the Lobby name/hasPassword of the selected row number.
        	String selectedLobbyName = null;
        	boolean selectedLobbyHasPassword = false;
        	int lobbyIndex=0;
        	final Iterator itr = Database.getInstance().getLobbies();
                while (itr.hasNext()) {
                    Lobby lobby = (Lobby) itr.next();
                    if(lobbyIndex == panel.getServerList().getSelectedRow()) {
                	selectedLobbyName = lobby.getLobbyName();
                	selectedLobbyHasPassword = lobby.isLobbyHasPassword();
                    }
                    lobbyIndex++;
                }
        	
            	if(!selectedLobbyHasPassword) { // Checks if the selected lobby doesn't have a password.
            		UserInterface.changeCard("serverlobbypanel");
            		try {
                        Connection.getInstance().sendPacket(PacketBuilder.getInstance().create01JoinLobbyPacket(selectedLobbyName,""));
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            		int i = h1jlb.getJoinLobbyStatus(); 
            		if( i == 0) {
            			try {
                            Connection.getInstance().sendPacket(PacketBuilder.getInstance().create02ReadyRequest());
                        } catch (IOException ex) {
                            Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
            			UserInterface.changeCard("serverlobbypanel");
            		}
            		else {
            			panel.getCouldNotJoinPane(i);
            		} 
            	} 
            	else {
            		password = panel.getPasswordPane();
            		
            		try {
            			Connection.getInstance().sendPacket(PacketBuilder.getInstance().create01JoinLobbyPacket(selectedLobbyName,password));
                    } catch (IOException ex) {
                    	Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                        }
            		}
            	int i = h1jlb.getJoinLobbyStatus(); 
            	if( i == 0) {
            		try {
                        Connection.getInstance().sendPacket(PacketBuilder.getInstance().create02ReadyRequest());
                    } catch (IOException ex) {
                        Logger.getLogger(ControllerSelectionPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
            		UserInterface.changeCard("serverlobbypanel");
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

