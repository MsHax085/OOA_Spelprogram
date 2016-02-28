package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.gui.UserInterface;
import src.gui.panels.ServerCreationPanel;
import src.gui.panels.ServerSelectionPanel;
import src.network.Connection;
import src.network.PacketBuilder;

public class ControllerServerCreationPanel implements MouseListener {
	
    private ServerCreationPanel panel;
    
    public ControllerServerCreationPanel(ServerCreationPanel panel) {
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
    	if (e.getSource().equals(panel.getPrevButton())) {
            UserInterface.changeCard("menupanel");
        } else if (e.getSource().equals(panel.getCreateButton())) {
        	if(!panel.getServerNameInput().getText().equals("") ) {
        		try {
                    Connection.getInstance().sendPacket(PacketBuilder.getInstance().create03CreateLobbyPacket(panel.getServerNameInput().getText(),panel.getServerPasswordInput().getText()));
                } catch (IOException ex) {
                    Logger.getLogger(ServerCreationPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                UserInterface.changeCard("serverlobbypanel");
        	}

        }
    }
}

