package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.gui.UserInterface;
import src.gui.panels.MenuPanel;
import src.network.Connection;
import src.network.PacketBuilder;

/*
 * @author Gustav
 */
public class ControllerMenuPanel implements MouseListener {
	
    private MenuPanel panel;
    
    public ControllerMenuPanel(MenuPanel panel) {
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
            try {
		Connection.getInstance().sendPacket(PacketBuilder.getInstance().create00RequestLobbyListPacket());
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }
            UserInterface.changeCard("serverselectionpanel");
        } else if (e.getSource().equals(panel.getHostButton())) {
            UserInterface.changeCard("servercreationpanel");
        } else if (e.getSource().equals(panel.getHighscoreButton())) {
        	try {
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create04RequestHighscorePacket(1)); // ERIK HAS HARDCODED A MAP-ID HERE.
            } catch (IOException ex) {
                Logger.getLogger(MenuPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            UserInterface.changeCard("highscorepanel");
        } else if (e.getSource().equals(panel.getOptionsButton())) {
            UserInterface.changeCard("optionspanel");
        }
    }
}
    
    
