package src.gui.panels.event;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import src.gui.UserInterface;
import src.gui.panels.ServerCreationPanel;
import src.gui.panels.ServerLobbyPanel;
import src.network.Connection;
import src.network.PacketBuilder;

public class ControllerServerLobbyPanel implements MouseListener {
	
    private ServerLobbyPanel panel;
    
    public ControllerServerLobbyPanel(ServerLobbyPanel panel) {
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
    	if (e.getSource().equals(panel.getQuitButton())) {
            UserInterface.changeCard("menupanel");
        } else if (e.getSource().equals(panel.getStartButton())) {
        	try {
                Connection.getInstance().sendPacket(PacketBuilder.getInstance().create02ReadyRequest());
            } catch (IOException ex) {
                Logger.getLogger(ServerLobbyPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Core.getInstance().setStateObserver(new Game());
        }
    }
}
